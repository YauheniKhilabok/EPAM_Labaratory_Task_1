package com.epam.onlineshop.constants;

/**
 * The SymbolConstants class. The main objective of this class is classified attributes,
 * which is uniquely determined by the value that will be either transferred to the JSP or JSP transferred to.
 * Also, using the attribute to get and set values in the sessions.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class WebConstants {
    public static final String CONTROL_SERVLET = "/controlServlet";
    public static final String SERIAL = "serial";
    public static final String NAME_WEB_INIT_PARAM = "INDEX_PATH";
    public static final String USER_SECURITY_PATTERN = "/jsp/admin/*";
    public static final String ADMIN_SECURITY_PATTERN = "/jsp/user/*";
    public static final String ENCODING_PATTERN = "/*";
    public static final String ENCODING_NAME = "encoding";
    public static final String ENCODING_DESCRIPTION = "Encoding Param";
    public static final String DATABASE_NAME = "jdbc/onlineshopdb";
    public static final String CONTEXT = "java:comp/env";
    public static final String CONTENT_DISPOSITION = "content-disposition";
    public static final String FILENAME = "filename";
    public static final String ENCODING = "UTF-8";
    public static final String COMMAND_VALUE = "cmd";
    public static final String SUCCESS_REGISTRATION_ATTRIBUTE = "successRegistrationMessage";
    public static final String ERROR_REGISTRATION_ATTRIBUTE = "errorRegistrationMessage";
    public static final String ERROR_AUTHORIZATION_ATTRIBUTE = "errorAuthorizationMessage";
    public static final String SUCCESS_COMMENT_ATTRIBUTE = "successCommentMessage";
    public static final String SUCCESS_ADD_PRODUCT_TO_LIST_ATTRIBUTE = "successAddProductToListMessage";
    public static final String SUCCESS_CHECKOUT_ATTRIBUTE = "successCheckoutMessage";
    public static final String ERROR_CHANGE_USER_DATA_ATTRIBUTE = "errorChangeUserData";
    public static final String ERROR_CHANGE_ORDER_ATTRIBUTE = "errorChangeOrderMessage";
    public static final String WARNING_SHOW_TEMP_PRODUCTS_ORDER_ATTRIBUTE = "warningShowTempProductsOrderMessage";
    public static final String WARNING_PRINT_ORDERS_ATTRIBUTE = "warningPrintOrdersMessage";
    public static final String ERROR_PROCESSING_PRODUCER_ATTRIBUTE = "errorProcessingProducer";
    public static final String ERROR_ADD_PRODUCER_ATTRIBUTE = "errorAddProducer";
    public static final String ERROR_PROCESSING_TYPE_ATTRIBUTE = "errorProcessingType";
    public static final String ERROR_ADD_TYPE_ATTRIBUTE = "errorAddType";
    public static final String WARNING_SEARCH_USERS_ATTRIBUTE = "warningSearchUsers";
    public static final String ERROR_DELETE_TYPE_ATTRIBUTE = "errorDeleteUser";
    public static final String ERROR_PRODUCT_ATTRIBUTE = "productError";
    public static final String SUCCESS_PRODUCT_ATTRIBUTE = "productSuccess";
    public static final String NUMBER_OF_REPLIES = "numberOfReplies";
    public static final String REVIEWS_LIST = "reviewsList";
    public static final String PRODUCTS_LIST = "productsList";
    public static final String ORDERS_LIST = "ordersList";
    public static final String PRODUCERS_LIST = "producersList";
    public static final String TYPES_LIST = "typesList";
    public static final String USERS_LIST = "usersList";
    public static final String ORDER_PRODUCT_LIST = "orderProductList";
    public static final String STATISTICS_LIST = "statisticsList";
    public static final String USER_CHART = "userChart";
    public static final String ADMIN_CHART = "adminChart";
    public static final String NUMBER_PAGES = "numberPages";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String USER_DATA = "userData";
    public static final String PRODUCT_DATA = "productData";
    public static final String MAX_PRODUCER_ID = "maxProducerId";
    public static final String MAX_TYPE_ID = "maxTypeId";
    public static final String TEMP_PRODUCTS_LIST = "tempOrderProductList";
    public static final String AUTHORIZED_ATTRIBUTE = "authorized";
    public static final String IS_EMPTY_ATTRIBUTE = "isEmptyTempList";
    public static final String IS_EMPTY_LIST_ATTRIBUTE = "isEmptyList";
    public static final String IS_SUCCESS_ATTRIBUTE = "isSuccessAdd";
    public static final String ROLE_ATTRIBUTE = "role";
    public static final String USER_ID_ATTRIBUTE = "user_id";
    public static final String NAME_USER_ATTRIBUTE = "nameUser";
    public static final String PRODUCT_ID_ATTRIBUTE = "productId";
    public static final boolean AUTHORIZED_TRUE_VALUE = true;
    public static final boolean AUTHORIZED_FALSE_VALUE = false;
    public static final String ADMIN_ROLE_VALUE = "admin";
    public static final String USER_ROLE_VALUE = "user";
    public static final String GUEST_ROLE_VALUE = "guest";
    public static final String USER_ID_DEFAULT_VALUE = null;
    public static final String SORTING_ATTRIBUTE = "sorting";
    public static final String LOCALE_ATTRIBUTE = "locale";
    public static final String PAGE_URL_ATTRIBUTE = "pageURL";
}
