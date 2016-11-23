package com.epam.onlineshop.dao;

import com.epam.onlineshop.connection.ConnectionPool;
import com.epam.onlineshop.constants.ExceptionConstants;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.constants.SymbolConstants;
import com.epam.onlineshop.entity.ordersproducts.OrderProduct;
import com.epam.onlineshop.entity.ordersproducts.OrdersProducts;
import com.epam.onlineshop.entity.statistics.AdminChart;
import com.epam.onlineshop.exceptions.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The OrderProductDAO class responsible for base and
 * necessary methods to the table orders in the database.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class OrderProductDAO extends AbstractDAO<OrderProduct> {
    /**
     * Property - numberRecords, attribute that contains
     * the number of records that meet a specific request.
     */
    private int numberRecords;
    private static final String SQL_SELECT_PART_OF_ORDERS;
    private static final String SQL_INSERT_ORDER_PRODUCT;
    private static final String SQL_SEARCH_UNPROCESSED_ORDERS;
    private static final String SQL_SORT_ORDERS_BY_LEAD_TIME_UP;
    private static final String SQL_SORT_ORDERS_BY_LEAD_TIME_DOWN;
    private static final String SQL_GET_PRODUCT_ID;
    private static final String SQL_GET_NUMBER_OF_PACKAGES;
    private static final String SQL_GET_ALL_ORDERS_BY_PRODUCT_ID;
    private static final String SQL_SELECT_DATA_FOR_ADMIN_CHART;
    private static final String SQL_SELECT_FOUND_ROWS;
    /**
     * Property - instance need to implement the singleton.
     */
    private static OrderProductDAO instance = null;
    /**
     * Property - lock responsible for thread-safe.
     *
     * @see ReentrantLock
     */
    private static ReentrantLock lock = new ReentrantLock();

    static {
        SQL_SELECT_PART_OF_ORDERS = "SELECT SQL_CALC_FOUND_ROWS `orders`.`id_order`, `users`.`name_user`, `products`.`name_product`, " +
                "`order_product`.`number_of_packages`, `order_product`.`cost`, `orders`.`lead_time`, `orders`.`delivery_condition`, " +
                "`orders`.`type_of_payment`, `orders`.`status` FROM `orders` " +
                "INNER JOIN `users` ON `orders`.`id_user` = `users`.`id_user` " +
                "INNER JOIN `order_product` ON `orders`.`id_order` = `order_product`.`id_order` " +
                "INNER JOIN `products` ON `order_product`.`id_product` = `products`.`id_product` " +
                "ORDER BY `orders`.`id_order` LIMIT ?, ?";
    }

    static {
        SQL_SORT_ORDERS_BY_LEAD_TIME_UP = "SELECT SQL_CALC_FOUND_ROWS `orders`.`id_order`, `users`.`name_user`, `products`.`name_product`, " +
                "`order_product`.`number_of_packages`, `order_product`.`cost`, `orders`.`lead_time`, `orders`.`delivery_condition`, " +
                "`orders`.`type_of_payment`, `orders`.`status` FROM `orders` " +
                "INNER JOIN `users` ON `orders`.`id_user` = `users`.`id_user` " +
                "INNER JOIN `order_product` ON `orders`.`id_order` = `order_product`.`id_order` " +
                "INNER JOIN `products` ON `order_product`.`id_product` = `products`.`id_product` " +
                "ORDER BY `orders`.`lead_time` LIMIT ?, ?";
    }

    static {
        SQL_SORT_ORDERS_BY_LEAD_TIME_DOWN = "SELECT SQL_CALC_FOUND_ROWS `orders`.`id_order`, `users`.`name_user`, `products`.`name_product`, " +
                "`order_product`.`number_of_packages`, `order_product`.`cost`, `orders`.`lead_time`, `orders`.`delivery_condition`, " +
                "`orders`.`type_of_payment`, `orders`.`status` FROM `orders` " +
                "INNER JOIN `users` ON `orders`.`id_user` = `users`.`id_user` " +
                "INNER JOIN `order_product` ON `orders`.`id_order` = `order_product`.`id_order` " +
                "INNER JOIN `products` ON `order_product`.`id_product` = `products`.`id_product` " +
                "ORDER BY `orders`.`lead_time` DESC LIMIT ?, ?";
    }

    static {
        SQL_SEARCH_UNPROCESSED_ORDERS = "SELECT `orders`.`id_order`, `users`.`name_user`, `products`.`name_product`, " +
                "`order_product`.`number_of_packages`, `order_product`.`cost`, `orders`.`lead_time`, `orders`.`delivery_condition`, " +
                "`orders`.`type_of_payment`, `orders`.`status` FROM `orders` " +
                "INNER JOIN `users` ON `orders`.`id_user` = `users`.`id_user` " +
                "INNER JOIN `order_product` ON `orders`.`id_order` = `order_product`.`id_order` " +
                "INNER JOIN `products` ON `order_product`.`id_product` = `products`.`id_product` " +
                "WHERE `orders`.`status` = ? ORDER BY `orders`.`id_order`";
    }

    static {
        SQL_INSERT_ORDER_PRODUCT = "INSERT INTO order_product (id_order, id_product, number_of_packages, cost)" +
                " VALUES(?, ?, ?, ?)";
    }

    static {
        SQL_GET_PRODUCT_ID = "SELECT `order_product`.`id_product` FROM `order_product` " +
                "INNER JOIN `orders` ON `order_product`.`id_order` = `orders`.`id_order` " +
                "WHERE `orders`.`id_order` = ?";
    }

    static {
        SQL_GET_NUMBER_OF_PACKAGES = "SELECT number_of_packages FROM order_product WHERE id_product = ?";
    }

    static {
        SQL_GET_ALL_ORDERS_BY_PRODUCT_ID = "SELECT COUNT(id_order) AS numberOfOrders FROM order_product WHERE id_product = ?";
    }

    static {
        SQL_SELECT_DATA_FOR_ADMIN_CHART = "SELECT `types`.`type`, COUNT(`types`.`type`) AS numberOfTypes " +
                "FROM `order_product` INNER JOIN `products` ON `order_product`.`id_product` = `products`.`id_product` " +
                "INNER JOIN `types` ON `products`.`id_type` = `types`.`id_type` GROUP BY `types`.`type`";
    }

    static {
        SQL_SELECT_FOUND_ROWS = "SELECT FOUND_ROWS()";
    }

    /**
     * Creates a new object of OrderProductDAO.
     */
    private OrderProductDAO() {
    }

    /**
     * This method is used to get class field instance
     * by which is called the class methods.
     *
     * @return instance - object of OrderProductDAO.
     * @see ReentrantLock#lock()
     * @see ReentrantLock#unlock()
     */
    public static OrderProductDAO getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new OrderProductDAO();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    /**
     * @param key value that will be searched.
     * @return returns a list of objects that was sorted and contain records from a table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    @Override
    public List<OrderProduct> search(String key) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        List<OrderProduct> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_SEARCH_UNPROCESSED_ORDERS);
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderProduct orderProduct = new OrderProduct();
                int orderId = resultSet.getInt(ParameterConstants.ID_ORDER_PARAMETER);
                orderProduct.getOrder().setId(orderId);
                orderProduct.getOrder().getUser().setNameUser(resultSet.getString(ParameterConstants.NAME_USER_PARAMETER));
                orderProduct.getProduct().setNameProduct(resultSet.getString(ParameterConstants.NAME_PRODUCT_PARAMETER));
                orderProduct.setNumberOfPackages(resultSet.getInt(ParameterConstants.NUMBER_OF_PACKAGES_PRODUCT_PARAMETER));
                orderProduct.setCost(resultSet.getDouble(ParameterConstants.COST_ORDER_PARAMETER));
                orderProduct.getOrder().setLeadTime(resultSet.getString(ParameterConstants.LEAD_TIME_ORDER_PARAMETER));
                orderProduct.getOrder().setDeliveryCondition(resultSet.getString(ParameterConstants.DELIVERY_CONDITION_PARAMETER));
                orderProduct.getOrder().setTypeOfPayment(resultSet.getString(ParameterConstants.TYPE_OF_PAYMENT_PARAMETER));
                orderProduct.getOrder().setStatus(resultSet.getString(ParameterConstants.STATUS_ORDER_PARAMETER));
                list.add(orderProduct);
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return list;
    }

    /**
     * @param offset        index from which will accrue to the values from the table.
     * @param numberRecords the number of records that will be reflected.
     * @param sortingType   sorting type, which can be either ascending or descending order
     * @return returns a list of objects that was sorted and contain records from a table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    @Override
    public List<OrderProduct> sort(int offset, int numberRecords, String sortingType) throws DaoException {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        Connection connection = null;
        List<OrderProduct> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            if (sortingType.equals(ParameterConstants.UP_SORTING_PARAMETER)) {
                preparedStatement = connection.prepareStatement(SQL_SORT_ORDERS_BY_LEAD_TIME_UP);
            } else {
                preparedStatement = connection.prepareStatement(SQL_SORT_ORDERS_BY_LEAD_TIME_DOWN);
            }
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, numberRecords);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderProduct orderProduct = new OrderProduct();
                int orderId = resultSet.getInt(ParameterConstants.ID_ORDER_PARAMETER);
                orderProduct.getOrder().setId(orderId);
                String nameUser = resultSet.getString(ParameterConstants.NAME_USER_PARAMETER);
                orderProduct.getOrder().getUser().setNameUser(nameUser);
                orderProduct.getProduct().setNameProduct(resultSet.getString(ParameterConstants.NAME_PRODUCT_PARAMETER));
                orderProduct.setNumberOfPackages(resultSet.getInt(ParameterConstants.NUMBER_OF_PACKAGES_PRODUCT_PARAMETER));
                orderProduct.setCost(resultSet.getDouble(ParameterConstants.COST_ORDER_PARAMETER));
                orderProduct.getOrder().setLeadTime(resultSet.getString(ParameterConstants.LEAD_TIME_ORDER_PARAMETER));
                orderProduct.getOrder().setDeliveryCondition(resultSet.getString(ParameterConstants.DELIVERY_CONDITION_PARAMETER));
                orderProduct.getOrder().setTypeOfPayment(resultSet.getString(ParameterConstants.TYPE_OF_PAYMENT_PARAMETER));
                orderProduct.getOrder().setStatus(resultSet.getString(ParameterConstants.STATUS_ORDER_PARAMETER));
                list.add(orderProduct);
            }
            resultSet.close();
            resultSet = statement.executeQuery(SQL_SELECT_FOUND_ROWS);
            if (resultSet.next()) {
                this.numberRecords = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return list;
    }

    /**
     * @param offset        index from which will accrue to the values from the table.
     * @param numberRecords the number of records that will be reflected.
     * @return returns a list of objects that contain
     * sought records from a table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    @Override
    public List<OrderProduct> getPartForPagination(int offset, int numberRecords) throws DaoException {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        Connection connection = null;
        List<OrderProduct> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(SQL_SELECT_PART_OF_ORDERS);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, numberRecords);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.getOrder().setId(resultSet.getInt(ParameterConstants.ID_ORDER_PARAMETER));
                orderProduct.getOrder().getUser().setNameUser(resultSet.getString(ParameterConstants.NAME_USER_PARAMETER));
                orderProduct.getProduct().setNameProduct(resultSet.getString(ParameterConstants.NAME_PRODUCT_PARAMETER));
                orderProduct.setNumberOfPackages(resultSet.getInt(ParameterConstants.NUMBER_OF_PACKAGES_PRODUCT_PARAMETER));
                orderProduct.setCost(resultSet.getDouble(ParameterConstants.COST_ORDER_PARAMETER));
                orderProduct.getOrder().setLeadTime(resultSet.getString(ParameterConstants.LEAD_TIME_ORDER_PARAMETER));
                orderProduct.getOrder().setDeliveryCondition(resultSet.getString(ParameterConstants.DELIVERY_CONDITION_PARAMETER));
                orderProduct.getOrder().setTypeOfPayment(resultSet.getString(ParameterConstants.TYPE_OF_PAYMENT_PARAMETER));
                orderProduct.getOrder().setStatus(resultSet.getString(ParameterConstants.STATUS_ORDER_PARAMETER));
                list.add(orderProduct);
            }
            resultSet.close();
            resultSet = statement.executeQuery(SQL_SELECT_FOUND_ROWS);
            if (resultSet.next()) {
                this.numberRecords = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return list;
    }

    /**
     * @param entity  This is the an object that contains
     *                a set of parameters that will be added to the table.
     * @param orderId the key to which the data will be added.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public void insert(OrdersProducts entity, int orderId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_ORDER_PRODUCT);
            for (int i = 0; i < entity.getOrdersProducts().size(); i++) {
                preparedStatement.setInt(1, orderId);
                preparedStatement.setInt(2, entity.getOrdersProducts().get(i).getProduct().getId());
                preparedStatement.setInt(3, entity.getOrdersProducts().get(i).getNumberOfPackages());
                preparedStatement.setDouble(4, entity.getOrdersProducts().get(i).getCost());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.INSERT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * @param orderId key that will be received data.
     * @return productId.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public List<Integer> getProductIdByOrderId(int orderId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        List<Integer> productIdList = new LinkedList<>();
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_PRODUCT_ID);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productIdList.add(resultSet.getInt(ParameterConstants.ID_PRODUCT_PARAMETER));
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return productIdList;
    }

    /**
     * @param productId key that will be received data.
     * @return number of packages.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public int getNumberOfPackagesByProductId(int productId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        int numberOfPackages = 0;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_NUMBER_OF_PACKAGES);
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                numberOfPackages = resultSet.getInt(ParameterConstants.NUMBER_OF_PACKAGES_PRODUCT_PARAMETER);
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return numberOfPackages;
    }

    /**
     * @param productId key that will be received data.
     * @return true if in the table there are orders by product id and false of otherwise.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public boolean isThereOrdersByProductId(int productId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean flag = false;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_ALL_ORDERS_BY_PRODUCT_ID);
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int numberOfOrders = resultSet.getInt(ParameterConstants.NUMBER_OF_ORDERS_PARAMETER);
                if (numberOfOrders > 0) {
                    flag = true;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return flag;
    }

    /**
     * @return Data statistics in a list.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public List<AdminChart> getDataForAdminChart() throws DaoException {
        Statement statement = null;
        Connection connection = null;
        List<AdminChart> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_DATA_FOR_ADMIN_CHART);
            while (resultSet.next()) {
                AdminChart data = new AdminChart();
                data.setType(resultSet.getString(ParameterConstants.TYPE_PRODUCT_PARAMETER));
                data.setCount(resultSet.getInt(ParameterConstants.NUMBER_OF_TYPES_PARAMETER));
                list.add(data);
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return list;
    }

    /**
     * This method is unsupported in this class.
     *
     * @throws UnsupportedOperationException Thrown to indicate that the requested operation is not supported.
     */
    @Override
    public void insert(OrderProduct entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * This method is unsupported in this class.
     *
     * @throws UnsupportedOperationException Thrown to indicate that the requested operation is not supported.
     */
    @Override
    public void delete(int key) throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * This method is unsupported in this class.
     *
     * @throws UnsupportedOperationException Thrown to indicate that the requested operation is not supported.
     */
    @Override
    public boolean change(int key, OrderProduct entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * This method is unsupported in this class.
     *
     * @throws UnsupportedOperationException Thrown to indicate that the requested operation is not supported.
     */
    @Override
    public List<OrderProduct> getAll() throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * @return number of records from table which satisfy the condition.
     */
    public int getNumberRecords() {
        return numberRecords;
    }
}
