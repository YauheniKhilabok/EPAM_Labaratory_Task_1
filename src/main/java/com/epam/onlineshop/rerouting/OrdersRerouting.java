package com.epam.onlineshop.rerouting;

import com.epam.onlineshop.command.orders.PrintOrdersForUserCommand;
import com.epam.onlineshop.command.orders.ShowOrdersListCommand;
import com.epam.onlineshop.command.orders.ShowTempProductsOrderCommand;
import com.epam.onlineshop.command.products.GetMoreInformationAboutProductCommand;
import com.epam.onlineshop.constants.CommandConstants;
import com.epam.onlineshop.constants.MessageConstants;
import com.epam.onlineshop.constants.PathConstants;
import com.epam.onlineshop.constants.WebConstants;
import com.epam.onlineshop.entity.orders.Order;
import com.epam.onlineshop.entity.ordersproducts.OrderProduct;
import com.epam.onlineshop.entity.ordersproducts.OrdersProducts;
import com.epam.onlineshop.pagination.OrdersPagination;
import com.epam.onlineshop.pagination.SortingOrdersPagination;
import com.epam.onlineshop.services.OrdersService;
import com.epam.onlineshop.servlets.ControlServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * The OrdersRerouting class responsible for redirection after operations associated with orders.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class OrdersRerouting {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(OrdersRerouting.class);
    /**
     * Property - object of OrdersProducts
     */
    private static OrdersProducts ordersProducts = null;

    /**
     * Redirect after add temp order for user
     *
     * @param request          parameter to the required to obtain data from the JSP,
     *                         to redirect data to the JSP and session management
     * @param response         parameter necessary for response to the client
     * @param productId        id of product which ordered
     * @param numberOfPackages number of packages of product
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterAddTempProductsOrder(HttpServletRequest request, HttpServletResponse response,
                                                         int productId, int numberOfPackages)
            throws ServletException, IOException {
        if (ordersProducts == null) {
            ordersProducts = new OrdersProducts();
        }
        HttpSession session = request.getSession(false);
        int userId = Integer.parseInt((String) session.getAttribute(WebConstants.USER_ID_ATTRIBUTE));
        OrdersService.fillPartOfObjectForTempOrder(ordersProducts, userId, productId, numberOfPackages);
        session.setAttribute(WebConstants.IS_EMPTY_ATTRIBUTE, false);
        request.setAttribute(WebConstants.SUCCESS_ADD_PRODUCT_TO_LIST_ATTRIBUTE, MessageConstants.SUCCESS_ADD_PRODUCT_TO_LIST_MESSAGE);
        GetMoreInformationAboutProductCommand command = new GetMoreInformationAboutProductCommand();
        command.execute(request, response);
    }

    /**
     * Redirect after show tables with temp orders for user
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterShowTempProductsOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Boolean isEmpty = (Boolean) session.getAttribute(WebConstants.IS_EMPTY_ATTRIBUTE);
        if ((ordersProducts == null && isEmpty == null) || isEmpty) {
            log.info(MessageConstants.LOG_WARNING_SHOW_TEMP_ORDER_MESSAGE);
            request.setAttribute(WebConstants.WARNING_SHOW_TEMP_PRODUCTS_ORDER_ATTRIBUTE, MessageConstants.WARNING_SHOW_TEMP_PRODUCTS_ORDER_MESSAGE);
            session.setAttribute(WebConstants.IS_EMPTY_ATTRIBUTE, true);
        } else if (ordersProducts != null) {
            if (ordersProducts.getOrdersProducts().size() == 0) {
                log.info(MessageConstants.LOG_WARNING_SHOW_TEMP_ORDER_MESSAGE);
                session.setAttribute(WebConstants.IS_EMPTY_ATTRIBUTE, true);
                request.setAttribute(WebConstants.WARNING_SHOW_TEMP_PRODUCTS_ORDER_ATTRIBUTE, MessageConstants.WARNING_SHOW_TEMP_PRODUCTS_ORDER_MESSAGE);
            } else {
                request.setAttribute(WebConstants.TEMP_PRODUCTS_LIST, ordersProducts.getOrdersProducts());
            }
        }
        request.getRequestDispatcher(PathConstants.USER_CHECKOUT_PAGE).forward(request, response);
    }

    /**
     * Redirect after delete temp order for user
     *
     * @param request   parameter to the required to obtain data from the JSP,
     *                  to redirect data to the JSP and session management
     * @param response  parameter necessary for response to the client
     * @param productId if of product which will deleted
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterDeleteTempProductsOrder(HttpServletRequest request, HttpServletResponse response,
                                                            int productId)
            throws ServletException, IOException {
        OrdersService.deleteProductFromTempOrder(ordersProducts, productId);
        HttpSession session = request.getSession(false);
        if (ordersProducts.getOrdersProducts().size() == 0) {
            log.info(MessageConstants.LOG_ERROR_DELETE_TEMP_ORDER_MESSAGE);
            request.setAttribute(WebConstants.WARNING_SHOW_TEMP_PRODUCTS_ORDER_ATTRIBUTE, MessageConstants.WARNING_SHOW_TEMP_PRODUCTS_ORDER_MESSAGE);
            session.setAttribute(WebConstants.IS_EMPTY_ATTRIBUTE, true);
        } else {
            request.setAttribute(WebConstants.TEMP_PRODUCTS_LIST, ordersProducts.getOrdersProducts());
        }
        request.getRequestDispatcher(PathConstants.USER_CHECKOUT_PAGE).forward(request, response);
    }

    /**
     * Redirect after checkout for user
     *
     * @param request           parameter to the required to obtain data from the JSP,
     *                          to redirect data to the JSP and session management
     * @param response          parameter necessary for response to the client
     * @param deliveryCondition delivery condition for order
     * @param typeOfPayment     tape of payment for order
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterCheckout(HttpServletRequest request, HttpServletResponse response,
                                             String deliveryCondition, String typeOfPayment)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int userId = Integer.parseInt((String) session.getAttribute(WebConstants.USER_ID_ATTRIBUTE));
        OrdersService.fillOrder(ordersProducts, userId, deliveryCondition, typeOfPayment);
        ordersProducts.getOrdersProducts().removeAll(ordersProducts.getOrdersProducts());
        session.setAttribute(WebConstants.IS_SUCCESS_ATTRIBUTE, true);
        request.setAttribute(WebConstants.SUCCESS_CHECKOUT_ATTRIBUTE, MessageConstants.SUCCESS_CHECKOUT_MESSAGE);
        ShowTempProductsOrderCommand command = new ShowTempProductsOrderCommand();
        command.execute(request, response);
    }

    /**
     * Redirect after print orders for user
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterPrintOrdersForUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int userId = Integer.parseInt((String) session.getAttribute(WebConstants.USER_ID_ATTRIBUTE));
        List<Order> orders = OrdersService.getOrdersListForUser(userId);
        if (orders.size() == 0) {
            log.info(MessageConstants.LOG_ERROR_SHOW_ORDERS_MESSAGE);
            request.setAttribute(WebConstants.WARNING_PRINT_ORDERS_ATTRIBUTE, MessageConstants.WARNING_PRINT_ORDERS_MESSAGE);
            session.setAttribute(WebConstants.IS_EMPTY_LIST_ATTRIBUTE, true);
        } else {
            request.setAttribute(WebConstants.ORDERS_LIST, orders);
            session.setAttribute(WebConstants.IS_EMPTY_LIST_ATTRIBUTE, false);
        }
        request.getRequestDispatcher(PathConstants.USER_BASKET_PAGE).forward(request, response);
    }

    /**
     * Redirect after delete order for user
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @param orderId  id of order which will deleted
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterDeleteOrder(HttpServletRequest request, HttpServletResponse response,
                                                int orderId) throws ServletException, IOException {
        OrdersService.deleteOrder(orderId);
        PrintOrdersForUserCommand command = new PrintOrdersForUserCommand();
        command.execute(request, response);
    }

    /**
     * Redirect after change order for user
     *
     * @param request           parameter to the required to obtain data from the JSP,
     *                          to redirect data to the JSP and session management
     * @param response          parameter necessary for response to the client
     * @param orderId           id of order which will changed
     * @param deliveryCondition new delivery condition for order
     * @param typeOfPayment     new type of payment for order
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterChangeOrder(HttpServletRequest request, HttpServletResponse response,
                                                int orderId, String deliveryCondition, String typeOfPayment)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int userId = Integer.parseInt((String) session.getAttribute(WebConstants.USER_ID_ATTRIBUTE));
        if (!OrdersService.changeOrder(userId, orderId, deliveryCondition, typeOfPayment)) {
            log.info(MessageConstants.LOG_ERROR_CHANGE_ORDER_MESSAGE);
            request.setAttribute(WebConstants.ERROR_CHANGE_ORDER_ATTRIBUTE, MessageConstants.ERROR_CHANGE_ORDER_MESSAGE);
        }
        PrintOrdersForUserCommand command = new PrintOrdersForUserCommand();
        command.execute(request, response);
    }

    /**
     * Redirect after show orders for admin
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterShowOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrdersPagination pagination = new OrdersPagination();
        pagination.makePagination(request);
        request.getRequestDispatcher(PathConstants.ORDER_MANAGEMENT_PAGE).forward(request, response);
    }

    /**
     * Redirect after search orders for admin
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterSearchOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<OrderProduct> orderProductList = OrdersService.searchOrders();
        request.setAttribute(WebConstants.ORDER_PRODUCT_LIST, orderProductList);
        request.getRequestDispatcher(PathConstants.ORDER_MANAGEMENT_PAGE).forward(request, response);
    }

    /**
     * Redirect after sort orders for admin
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterSortOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SortingOrdersPagination pagination = new SortingOrdersPagination();
        pagination.makePagination(request);
        request.getRequestDispatcher(PathConstants.ORDER_MANAGEMENT_PAGE).forward(request, response);
    }

    /**
     * Redirect after checkout process for admin
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @param orderId  id of order which will processed
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterCheckoutProcess(HttpServletRequest request, HttpServletResponse response, int orderId)
            throws ServletException, IOException {
        OrdersService.checkoutProcessOrder(orderId);
        ControlServlet obj = new ControlServlet();
        obj.setPaginationValue(CommandConstants.SHOW_ORDERS_LIST_COMMAND);
        ShowOrdersListCommand command = new ShowOrdersListCommand();
        command.execute(request, response);
    }
}
