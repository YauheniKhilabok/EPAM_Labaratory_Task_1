package com.epam.onlineshop.rerouting;

import com.epam.onlineshop.command.products.LoadProductDataCommand;
import com.epam.onlineshop.command.products.ShowProductsListCommand;
import com.epam.onlineshop.constants.*;
import com.epam.onlineshop.entity.producers.Producer;
import com.epam.onlineshop.entity.products.Product;
import com.epam.onlineshop.entity.statistics.UserChart;
import com.epam.onlineshop.entity.types.Type;
import com.epam.onlineshop.pagination.*;
import com.epam.onlineshop.services.ProducersService;
import com.epam.onlineshop.services.ProductsService;
import com.epam.onlineshop.services.TypesService;
import com.epam.onlineshop.servlets.ControlServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The ProductsRerouting class responsible for redirection after operations associated with products.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ProductsRerouting {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(ProductsRerouting.class);

    /**
     * Redirect after print products for user and guest
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterPrintProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String role = (String) session.getAttribute(WebConstants.ROLE_ATTRIBUTE);
        if (role == null) {
            role = WebConstants.GUEST_ROLE_VALUE;
        }
        switch (role) {
            case WebConstants.GUEST_ROLE_VALUE: {
                GuestProductsPagination pagination = new GuestProductsPagination();
                pagination.makePagination(request);
                request.getRequestDispatcher(PathConstants.MAIN_PRODUCTS_PAGE).forward(request, response);
                break;
            }
            case WebConstants.USER_ROLE_VALUE: {
                UserProductsPagination pagination = new UserProductsPagination();
                pagination.makePagination(request);
                request.getRequestDispatcher(PathConstants.USER_INDEX_PAGE).forward(request, response);
                break;
            }
            case WebConstants.ADMIN_ROLE_VALUE: {
                break;
            }
            default: {
                log.info(MessageConstants.LOG_INPUT_ERROR);
            }
        }
    }

    /**
     * Redirect after search products for user
     *
     * @param request     parameter to the required to obtain data from the JSP,
     *                    to redirect data to the JSP and session management
     * @param response    parameter necessary for response to the client
     * @param nameProduct name of product which is sought
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterSearchProducts(HttpServletRequest request, HttpServletResponse response, String nameProduct)
            throws IOException, ServletException {
        List<Product> products = ProductsService.searchProductsByName(nameProduct);
        request.setAttribute(WebConstants.PRODUCTS_LIST, products);
        request.getRequestDispatcher(PathConstants.USER_INDEX_PAGE).forward(request, response);
    }

    /**
     * Redirect after sorting products for user
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterSortingProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SortingProductsPaginationForUser pagination = new SortingProductsPaginationForUser();
        pagination.makePagination(request);
        request.getRequestDispatcher(PathConstants.USER_INDEX_PAGE).forward(request, response);
    }

    /**
     * Redirect after get full product data for user
     *
     * @param request   parameter to the required to obtain data from the JSP,
     *                  to redirect data to the JSP and session management
     * @param response  parameter necessary for response to the client
     * @param productId id of product which will printed
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterGetFullProductData(HttpServletRequest request, HttpServletResponse response,
                                                       String productId) throws ServletException, IOException {
        Product product = ProductsService.getFullDataProduct(productId);
        request.setAttribute(WebConstants.PRODUCT_ID_ATTRIBUTE, productId);
        request.setAttribute(WebConstants.PRODUCT_DATA, product);
        request.getRequestDispatcher(PathConstants.PRODUCT_DESCRIPTION_PAGE).forward(request, response);
    }

    /**
     * Redirect after show statistics for user
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterShowStatisticForUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UserChart> list = ProductsService.getDataForUserChart();
        request.setAttribute(WebConstants.USER_CHART, list);
        request.getRequestDispatcher(PathConstants.USER_STATISTICS_PAGE).forward(request, response);
    }

    /**
     * Redirect after show products fir admin
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterShowProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductsPagination pagination = new ProductsPagination();
        pagination.makePagination(request);
        request.getRequestDispatcher(PathConstants.PRODUCT_MANAGEMENT_PAGE).forward(request, response);
    }

    /**
     * Redirect after search products for admin
     *
     * @param request     parameter to the required to obtain data from the JSP,
     *                    to redirect data to the JSP and session management
     * @param response    parameter necessary for response to the client
     * @param nameProduct name of product which is sought
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterSearchForAdmin(HttpServletRequest request, HttpServletResponse response,
                                                   String nameProduct)
            throws ServletException, IOException {
        List<Product> products = ProductsService.searchProductsByName(nameProduct);
        request.setAttribute(WebConstants.PRODUCTS_LIST, products);
        request.getRequestDispatcher(PathConstants.PRODUCT_MANAGEMENT_PAGE).forward(request, response);
    }

    /**
     * Redirect after sort products for admin
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterSortProductsForAdmin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SortingProductsPaginationForAdmin pagination = new SortingProductsPaginationForAdmin();
        pagination.makePagination(request);
        request.getRequestDispatcher(PathConstants.PRODUCT_MANAGEMENT_PAGE).forward(request, response);
    }

    /**
     * Redirect after set discount on product for admin
     *
     * @param request   parameter to the required to obtain data from the JSP,
     *                  to redirect data to the JSP and session management
     * @param response  parameter necessary for response to the client
     * @param productId id of product which will be set off
     * @param discount  new discount on product
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterSetDiscount(HttpServletRequest request, HttpServletResponse response,
                                                int productId, int discount)
            throws ServletException, IOException {
        boolean flag = ProductsService.setDiscount(productId, discount);
        if (!flag) {
            log.error(MessageConstants.LOG_ERROR_SET_DISCOUNT_MESSAGE);
            request.setAttribute(WebConstants.ERROR_PRODUCT_ATTRIBUTE, MessageConstants.ERROR_SET_DISCOUNT_MESSAGE);
        }
        ShowProductsListCommand command = new ShowProductsListCommand();
        ControlServlet obj = new ControlServlet();
        obj.setPaginationValue(CommandConstants.SHOW_PRODUCTS_LIST_COMMAND);
        command.execute(request, response);
    }

    /**
     * Redirect after add new product for admin
     *
     * @param productParameters map with products parameters which will add to database
     * @param request           parameter to the required to obtain data from the JSP,
     *                          to redirect data to the JSP and session management
     * @param response          parameter necessary for response to the client
     * @param flag              PS responsible operation on success and displays error otherwise
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterAddProduct(Map<String, String> productParameters, HttpServletRequest request,
                                               HttpServletResponse response, int flag)
            throws ServletException, IOException {
        switch (flag) {
            case LogicFlagConstants.SUCCESS_FLAG: {
                ProductsService.addProduct(productParameters);
                break;
            }
            case LogicFlagConstants.FIRST_ERROR_FLAG: {
                log.info(MessageConstants.LOG_ERROR_DUPLICATE_PRODUCT_MESSAGE);
                request.setAttribute(WebConstants.ERROR_PRODUCT_ATTRIBUTE, MessageConstants.ERROR_DUPLICATE_PRODUCT_MESSAGE);
                break;
            }
            case LogicFlagConstants.SECOND_ERROR_FLAG: {
                log.info(MessageConstants.LOG_ERROR_PRODUCT_PARAMETERS_MESSAGE);
                request.setAttribute(WebConstants.ERROR_PRODUCT_ATTRIBUTE, MessageConstants.ERROR_PRODUCT_PARAMETERS_MESSAGE);
                break;
            }
            default: {
                log.info(MessageConstants.LOG_INPUT_ERROR);
            }
        }
        ControlServlet obj = new ControlServlet();
        obj.setPaginationValue(CommandConstants.SHOW_PRODUCTS_LIST_COMMAND);
        ShowProductsListCommand command = new ShowProductsListCommand();
        command.execute(request, response);
    }

    /**
     * Redirect after delete product for admin
     *
     * @param request   parameter to the required to obtain data from the JSP,
     *                  to redirect data to the JSP and session management
     * @param response  parameter necessary for response to the client
     * @param productId id of product which will be deleted
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterDeleteProduct(HttpServletRequest request, HttpServletResponse response,
                                                  int productId) throws ServletException, IOException {
        boolean flag = ProductsService.deleteProduct(productId);
        if (flag) {
            log.info(MessageConstants.LOG_ERROR_PROCESSING_PRODUCT_MESSAGE);
            request.setAttribute(WebConstants.ERROR_PRODUCT_ATTRIBUTE, MessageConstants.ERROR_PROCESSING_PRODUCT_MESSAGE);
        }
        ControlServlet obj = new ControlServlet();
        obj.setPaginationValue(CommandConstants.SHOW_PRODUCTS_LIST_COMMAND);
        ShowProductsListCommand command = new ShowProductsListCommand();
        command.execute(request, response);
    }

    /**
     * Redirect after load product data for admin
     *
     * @param request   parameter to the required to obtain data from the JSP,
     *                  to redirect data to the JSP and session management
     * @param response  parameter necessary for response to the client
     * @param productId id of product which will be load
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterLoadProductData(HttpServletRequest request, HttpServletResponse response,
                                                    String productId) throws ServletException, IOException {
        Product product = ProductsService.getFullDataProduct(productId);
        List<Type> types = TypesService.getAllTypes();
        List<Producer> producers = ProducersService.getAllProducers();
        request.setAttribute(WebConstants.PRODUCT_ID_ATTRIBUTE, productId);
        request.setAttribute(WebConstants.TYPES_LIST, types);
        request.setAttribute(WebConstants.PRODUCERS_LIST, producers);
        request.setAttribute(WebConstants.PRODUCT_DATA, product);
        request.getRequestDispatcher(PathConstants.CHANGE_PRODUCT_PAGE).forward(request, response);
    }

    /**
     * Redirect after change product data for admin
     *
     * @param request           parameter to the required to obtain data from the JSP,
     *                          to redirect data to the JSP and session management
     * @param response          parameter necessary for response to the client
     * @param productId         id of product which will be updated
     * @param productParameters map with new parameters of product
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterChangeProduct(HttpServletRequest request, HttpServletResponse response,
                                                  int productId, Map<String, String> productParameters) throws ServletException, IOException {
        int flag = ProductsService.changeProduct(productId, productParameters);
        switch (flag) {
            case LogicFlagConstants.SUCCESS_FLAG: {
                request.setAttribute(WebConstants.SUCCESS_PRODUCT_ATTRIBUTE, MessageConstants.SUCCESS_PROCESSING_PRODUCT_MESSAGE);
                break;
            }
            case LogicFlagConstants.FIRST_ERROR_FLAG: {
                log.info(MessageConstants.LOG_ERROR_PROCESSING_PRODUCT_MESSAGE);
                request.setAttribute(WebConstants.ERROR_PRODUCT_ATTRIBUTE, MessageConstants.ERROR_PROCESSING_PRODUCT_MESSAGE);
                break;
            }
            case LogicFlagConstants.SECOND_ERROR_FLAG: {
                log.info(MessageConstants.LOG_ERROR_PRODUCT_PARAMETERS_MESSAGE);
                request.setAttribute(WebConstants.ERROR_PRODUCT_ATTRIBUTE, MessageConstants.ERROR_PRODUCT_PARAMETERS_MESSAGE);
                break;
            }
            default: {
                log.info(MessageConstants.LOG_INPUT_ERROR);
            }
        }
        LoadProductDataCommand command = new LoadProductDataCommand();
        ControlServlet obj = new ControlServlet();
        obj.setPaginationValue(CommandConstants.LOAD_PRODUCT_DATA_COMMAND);
        command.execute(request, response);
    }

}
