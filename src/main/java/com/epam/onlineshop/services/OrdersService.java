package com.epam.onlineshop.services;

import com.epam.onlineshop.constants.*;
import com.epam.onlineshop.dao.OrderDAO;
import com.epam.onlineshop.dao.OrderProductDAO;
import com.epam.onlineshop.dao.ProductDAO;
import com.epam.onlineshop.dao.StatisticsDAO;
import com.epam.onlineshop.entity.orders.Order;
import com.epam.onlineshop.entity.ordersproducts.OrderProduct;
import com.epam.onlineshop.entity.ordersproducts.OrdersProducts;
import com.epam.onlineshop.exceptions.CalculationUnsupportedException;
import com.epam.onlineshop.exceptions.DaoException;
import com.epam.onlineshop.state.*;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

/**
 * The OrdersService class responsible for performing operations on data in the orders table.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class OrdersService {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(OrdersService.class);

    /**
     * @param ordersProducts   object of OrdersProducts with data for checkout
     * @param userId           id of user
     * @param productId        id of product
     * @param numberOfPackages number of packages in order
     */
    public static void fillPartOfObjectForTempOrder(OrdersProducts ordersProducts, int userId, int productId, int numberOfPackages) {
        try {
            ordersProducts.add(ProductDAO.getInstance().fillPartOfObjectForTempOrder(userId, productId, numberOfPackages));
        } catch (DaoException e) {
            log.error(e);
        }
    }

    /**
     * @param ordersProducts object which will be deleted
     * @param productId      id of product
     */
    public static void deleteProductFromTempOrder(OrdersProducts ordersProducts, int productId) {
        List<OrderProduct> list = ordersProducts.getOrdersProducts();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProduct().getId() == productId) {
                list.remove(i);
            }
        }
    }

    /**
     * @param ordersProducts    object with data which will be add
     * @param userId            id of user
     * @param deliveryCondition delivery condition for order
     * @param typeOfPayment     type of payment for order
     */
    public static void fillOrder(OrdersProducts ordersProducts, int userId, String deliveryCondition, String typeOfPayment) {
        double totalCost = 0.0;
        try {
            totalCost = calculationOfTotalCost(deliveryCondition, ordersProducts);
        } catch (CalculationUnsupportedException e) {
            log.error(e);
        }
        try {
            OrderDAO.getInstance().insert(createOrder(userId, deliveryCondition, typeOfPayment, totalCost));
        } catch (DaoException e) {
            log.error(e);
        }
        fillOrderProduct(ordersProducts, userId, totalCost);
        updateNumberOfPackagesForAdd(ordersProducts);
    }

    /**
     * @param deliveryCondition delivery condition for order
     * @param ordersProducts    object with orders data
     * @return total cost of order
     * @throws CalculationUnsupportedException
     */
    private static double calculationOfTotalCost(String deliveryCondition, OrdersProducts ordersProducts)
            throws CalculationUnsupportedException {
        double factor = 0.0;
        switch (deliveryCondition) {
            case DefaultValueConstants.WITHOUT_DELIVERY_TYPE:
                factor = 1.0;
                break;
            case DefaultValueConstants.REGULAR_DELIVERY_TYPE:
                factor = 1.05;
                break;
            case DefaultValueConstants.EXPRESS_DELIVERY_TYPE:
                factor = 1.3;
                break;
            default:
                log.info(MessageConstants.LOG_INPUT_ERROR);
        }
        double intermediateCost = 0.0;
        if (ordersProducts.getOrdersProducts().size() != 0) {
            for (int i = 0; i < ordersProducts.getOrdersProducts().size(); i++) {
                double unitPrice = ordersProducts.getOrdersProducts().get(i).getCost();
                double discountInt = ordersProducts.getOrdersProducts().get(i).getProduct().getDiscounts();
                double discountDouble = new BigDecimal(discountInt / 100.0).setScale(3, RoundingMode.HALF_UP).doubleValue();
                double discount = unitPrice * discountDouble;
                double intermediateCostWithDiscount = unitPrice - discount;
                intermediateCost += intermediateCostWithDiscount;
            }
        } else {
            throw new CalculationUnsupportedException(ExceptionConstants.CALCULATION_UNSUPPORTED_EXCEPTION);
        }
        return factor * intermediateCost;
    }

    /**
     * @param userId            id of user
     * @param deliveryCondition delivery condition for order
     * @param typeOfPayment     type of payment for order
     * @param totalCost         total cost of order
     * @return object Order with data
     */
    private static Order createOrder(int userId, String deliveryCondition, String typeOfPayment, double totalCost) {
        Order order = new Order();
        order.getUser().setId(userId);
        order.setDeliveryCondition(deliveryCondition);
        order.setTypeOfPayment(typeOfPayment);
        order.setTotalCost(totalCost);
        return order;
    }

    /**
     * @param ordersProducts object with data which need to operate
     * @param userId         id of user
     * @param totalCost      total cost of order
     */
    private static void fillOrderProduct(OrdersProducts ordersProducts, int userId, double totalCost) {
        int orderId = getOrderId(userId, totalCost);
        setNotTreatedStatus(orderId);
        try {
            OrderProductDAO.getInstance().insert(ordersProducts, orderId);
        } catch (DaoException e) {
            log.error(e);
        }
    }

    /**
     * @param ordersProducts object with data which responsible for updating number of packages
     */
    private static void updateNumberOfPackagesForAdd(OrdersProducts ordersProducts) {
        for (int i = 0; i < ordersProducts.getOrdersProducts().size(); i++) {
            int productId = ordersProducts.getOrdersProducts().get(i).getProduct().getId();
            try {
                int currentNumberOfPackages = ProductDAO.getInstance().getNumberOfPackages(productId);
                int numberOfPackagesInOrder = ordersProducts.getOrdersProducts().get(i).getNumberOfPackages();
                int newNumberOfPackages = currentNumberOfPackages - numberOfPackagesInOrder;
                ProductDAO.getInstance().setNewNumberOfPackages(productId, newNumberOfPackages);
                if (newNumberOfPackages == 0) {
                    ProductDAO.getInstance().setNewProductStatus(productId, DefaultValueConstants.EMPTY_PRODUCT_STATUS);
                }
            } catch (DaoException e) {
                log.error(e);
            }
        }
    }

    /**
     * @param orderId id of order which will be deleted
     */
    public static void deleteOrder(int orderId) {
        setCancellationStatus(orderId);
        try {
            List<Integer> productIdList = OrderProductDAO.getInstance().getProductIdByOrderId(orderId);
            for (Integer productIdInt : productIdList) {
                int productId = productIdInt;
                int numberOfPackages = OrderProductDAO.getInstance().getNumberOfPackagesByProductId(productId);
                updateNumberOfPackagesForDelete(productId, numberOfPackages);
            }
            OrderDAO.getInstance().delete(orderId);
        } catch (DaoException e) {
            log.error(e);
        }
    }

    /**
     * @param productId               id of product which need to update number of packages after delete
     * @param numberOfPackagesInOrder new number of packages
     */
    private static void updateNumberOfPackagesForDelete(int productId, int numberOfPackagesInOrder) {
        try {
            int currentNumberOfPackages = ProductDAO.getInstance().getNumberOfPackages(productId);
            int newNumberOfPackages = currentNumberOfPackages + numberOfPackagesInOrder;
            if (newNumberOfPackages > 0) {
                ProductDAO.getInstance().setNewProductStatus(productId, DefaultValueConstants.DEFAULT_PRODUCT_STATUS);
            }
            ProductDAO.getInstance().setNewNumberOfPackages(productId, newNumberOfPackages);
        } catch (DaoException e) {
            log.error(e);
        }
    }

    /**
     * @param userId            id of user who want to change
     * @param orderId           id of order which will be changed
     * @param deliveryCondition new delivery condition
     * @param typeOfPayment     new type of payment
     * @return true if operation is success and false otherwise
     */
    public static boolean changeOrder(int userId, int orderId, String deliveryCondition, String typeOfPayment) {
        Order order = new Order();
        order.setId(orderId);
        order.setDeliveryCondition(deliveryCondition);
        order.setTypeOfPayment(typeOfPayment);
        boolean flag = false;
        try {
            flag = OrderDAO.getInstance().change(userId, order);
        } catch (DaoException e) {
            log.error(e);
        }
        return flag;
    }

    /**
     * @param userId    id of user
     * @param totalCost total cost od order
     * @return order id
     */
    private static int getOrderId(int userId, double totalCost) {
        int orderId = 0;
        try {
            orderId = OrderDAO.getInstance().getOrderId(userId, totalCost);
        } catch (DaoException e) {
            log.error(e);
        }
        return orderId;
    }

    /**
     * @param userId id of user
     * @return list with all orders data
     */
    public static List<Order> getOrdersListForUser(int userId) {
        List<Order> orders = null;
        try {
            orders = OrderDAO.getInstance().getOrdersListForUser(userId);
        } catch (DaoException e) {
            log.error(e);
        }
        return orders;
    }

    /**
     * @param offset        the index in the table, from which the output begins
     * @param numberRecords the number of records that will be reflected
     * @return list with orders
     */
    public static List<OrderProduct> printOrdersForAdmin(int offset, int numberRecords) {
        List<OrderProduct> orders = null;
        try {
            orders = OrderProductDAO.getInstance().getPartForPagination(offset, numberRecords);
        } catch (DaoException e) {
            log.error(e);
        }
        return orders;
    }

    /**
     * @return list with orders which will be find
     */
    public static List<OrderProduct> searchOrders() {
        List<OrderProduct> orders = null;
        try {
            orders = OrderProductDAO.getInstance().search(DefaultValueConstants.DEFAULT_ORDER_STATUS);
        } catch (DaoException e) {
            log.error(e);
        }
        return orders;
    }

    /**
     * @param offset        the index in the table, from which the output begins
     * @param numberRecords the number of records that will be reflected
     * @param sortingType   type of sort. Can be ask and desc.
     * @return list with sorted orders
     */
    public static List<OrderProduct> sortOrdersForAdmin(int offset, int numberRecords, String sortingType) {
        List<OrderProduct> orderProductList = null;
        try {
            orderProductList = OrderProductDAO.getInstance().sort(offset, numberRecords, sortingType);
        } catch (DaoException e) {
            log.error(e);
        }
        return orderProductList;
    }

    /**
     * @param orderId id of order which will be processed
     */
    public static void checkoutProcessOrder(int orderId) {
        try {
            String currentStatus = OrderDAO.getInstance().getStatusById(orderId);
            switch (currentStatus) {
                case StateConstants.NOT_TREATED_STATE: {
                    setProcessingStatus(orderId);
                    break;
                }
                case StateConstants.PROCESSING_STATE: {
                    setTreatedStatus(orderId);
                    break;
                }
                case StateConstants.TREATED_STATE: {
                    setDataForStatistics(orderId);
                    OrderDAO.getInstance().delete(orderId);
                    break;
                }
                default: {
                    log.info(MessageConstants.LOG_INPUT_ERROR);
                }
            }
        } catch (DaoException e) {
            log.error(e);
        }
    }

    /**
     * @param orderId id of order
     */
    private static void setDataForStatistics(int orderId) {
        try {
            Map<String, String> statisticsData = StatisticsDAO.getInstance().getDataByStatus(DefaultValueConstants.CURRENT_STATISTICS_STATUS);
            double currentAmountSoldGoods = Double.parseDouble(statisticsData.get(ParameterConstants.AMOUNT_SOLD_GOODS));
            double consumption = Double.parseDouble(statisticsData.get(ParameterConstants.CONSUMPTION));
            double currentIncome = Double.parseDouble(statisticsData.get(ParameterConstants.INCOME));
            Map<String, String> ordersData = OrderDAO.getInstance().getDataById(orderId);
            double numberOfPackagesInOrder = Double.parseDouble(ordersData.get(ParameterConstants.NUMBER_OF_PACKAGES_PRODUCT_PARAMETER));
            double totalCost = Double.parseDouble(ordersData.get(ParameterConstants.TOTAL_COST_PARAMETER));
            double newAmountSoldGoods = currentAmountSoldGoods + numberOfPackagesInOrder;
            double newIncome = currentIncome + totalCost;
            double newProfit = newIncome - consumption;
            StatisticsDAO.getInstance().updatePeriodData(newAmountSoldGoods, newIncome, newProfit);
        } catch (DaoException e) {
            log.error(e);
        }
    }

    /**
     * @param orderId id of order, which will be updated status
     */
    private static void setCancellationStatus(int orderId) {
        OrderContext context = new OrderContext();
        CancellationState state = new CancellationState();
        state.doAction(context, orderId);
    }

    /**
     * @param orderId id of order, which will be updated status
     */
    private static void setNotTreatedStatus(int orderId) {
        OrderContext context = new OrderContext();
        NotTreatedState state = new NotTreatedState();
        state.doAction(context, orderId);
    }

    /**
     * @param orderId id of order, which will be updated status
     */
    private static void setProcessingStatus(int orderId) {
        OrderContext context = new OrderContext();
        ProcessingState state = new ProcessingState();
        state.doAction(context, orderId);
    }

    /**
     * @param orderId id of order, which will be updated status
     */
    private static void setTreatedStatus(int orderId) {
        OrderContext context = new OrderContext();
        TreatedState state = new TreatedState();
        state.doAction(context, orderId);
    }

    /**
     * @return number of records from table
     */
    public static int getNumberRecords() {
        return OrderProductDAO.getInstance().getNumberRecords();
    }
}
