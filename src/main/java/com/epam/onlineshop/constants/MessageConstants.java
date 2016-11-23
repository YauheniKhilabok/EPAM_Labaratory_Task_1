package com.epam.onlineshop.constants;
/**
 * The MessageConstants class declares a number of constants
 * that are used to transmit the message to the jsp, as well as logging code.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class MessageConstants {
    public static final String SUCCESS_REGISTRATION_MESSAGE = "Вы успешно зарегистрированы!";
    public static final String ERROR_PARAMETER_REGISTRATION_MESSAGE = "Ошибка регистрации! В одно из полей введено некорректное значение.";
    public static final String ERROR_DUPLICATE_EMAIL_MESSAGE = "Ошибка регистрации! Пользователь с таким email уже существует.";
    public static final String ERROR_DUPLICATE_LOGIN_MESSAGE = "Ошибка регистрации! Пользователь с таким логином уже существует.";
    public static final String ERROR_NOT_IDENTITY_PASSWORDS_MESSAGE = "Ошибка регистрации! Введенные пароли не совпадают.";
    public static final String ERROR_AUTHORIZED_MESSAGE = "Ошибка авторизации! Неправильный логин или пароль.";
    public static final String BAN_ERROR_AUTHORIZED_MESSAGE = "За нарушение правил сайта Вы были заблокированы.";
    public static final String SUCCESS_COMMENT_MESSAGE = "Запрос выполнен успешно!";
    public static final String SUCCESS_ADD_PRODUCT_TO_LIST_MESSAGE = "Товар успешно добавлен в список!";
    public static final String ERROR_PARAMETER_CHANGE_DATA_USER_MESSAGE = "Ошибка изменения! В одно из полей введено некорректное значение.";
    public static final String WARNING_SHOW_TEMP_PRODUCTS_ORDER_MESSAGE = "Для того чтобы оформить заказ, необходимо добавить как минимум один продукт в список товаров.";
    public static final String SUCCESS_CHECKOUT_MESSAGE = "Заказ успешно оформлен!";
    public static final String WARNING_PRINT_ORDERS_MESSAGE = "Корзина с заказами пуста.";
    public static final String ERROR_CHANGE_ORDER_MESSAGE = "Ошибка изменения! Неправильный id заказа.";
    public static final String ERROR_PROCESSING_PRODUCER_MESSAGE = "Ошибка операции! В дочерней таблице товаров существует спортивное питание, поставленное производителем, " +
            "которого Вы пытаетесь удалить/изменить. Вернитесь к данной операции после того, как в таблице товаров не останется спортивного питания данного производителя.";
    public static final String ERROR_ADD_PRODUCER_MESSAGE = "Ошибка добавления! Данные с таким брендом и страной-производителем уже существуют.";
    public static final String ERROR_CHANGE_PRODUCER_MESSAGE = "Ошибка изменения! Введенный Вами id производителя не существует.";
    public static final String ERROR_PROCESSING_TYPE_MESSAGE = "Ошибка операции! В дочерней таблице товаров существует спортивное питание с типом спортивного питания, " +
            "которое Вы пытаетесь удалить/изменить. Вернитесь к данной операции после того, как в таблице товаров не останется спортивного питания данного типа.";
    public static final String ERROR_ADD_TYPE_MESSAGE = "Ошибка добавления! Данные с таким типом спортивного питания уже существуют.";
    public static final String ERROR_CHANGE_TYPE_MESSAGE = "Ошибка изменения! Введенный Вами id типа спортивного питания не существует.";
    public static final String WARNING_SEARCH_USERS_MESSAGE = "Данные не найдены.";
    public static final String ERROR_DELETE_TYPE_MESSAGE = "Ошибка удаления! В дочерней таблице заказов есть необработанный заказ для пользователя, " +
            "которое Вы пытаетесь удалить. Вернитесь к данной операции после того, как в таблице заказов не останется необработанных заказов для данного пользователя.";
    public static final String ERROR_SET_DISCOUNT_MESSAGE = "Ошибка операции. Введенный id тоовара не существует.";
    public static final String ERROR_DUPLICATE_PRODUCT_MESSAGE = "Ошибка добавления! Товар с такими данными уже существует.";
    public static final String ERROR_PRODUCT_PARAMETERS_MESSAGE = "Ошибка операции! Один из параметров введен некорректно.";
    public static final String ERROR_PROCESSING_PRODUCT_MESSAGE = "Ошибка операции! В дочерней таблице заказов есть запрос на товар, " +
            "который Вы пытаетесь удалить/изменить. Вернитесь к данной операции после того, как в таблице заказов не останется заказов на данный товар.";
    public static final String SUCCESS_PROCESSING_PRODUCT_MESSAGE = "Операция выполнена успешно!";
    public static final String FIRST_PART_WELCOME_MESSAGE = "Добро пожаловать, ";
    public static final String SECOND_PART_WELCOME_MESSAGE = "Мы рады преветствовать Вас на нашем сайте.";
    public static final String FIRST_ADMIN_WELCOME_MESSAGE = "Здравствуйте, ";
    public static final String SECOND_ADMIN_WELCOME_MESSAGE = "Добро пожаловать в кабинет управления системой.";
    public static final String LOG_INPUT_ERROR = "Error! An incorrect value.";
    public static final String LOG_SUCCESS_REGISTRATION_MESSAGE = "Registration completed successfully!";
    public static final String LOG_ERROR_PARAMETER_REGISTRATION_MESSAGE = "Registration error! One of the parameters is not valid.";
    public static final String LOG_ERROR_DUPLICATE_EMAIL_MESSAGE = "Registration error! User with this email already exists.";
    public static final String LOG_ERROR_DUPLICATE_LOGIN_MESSAGE = "Registration error! User with this login already exists.";
    public static final String LOG_ERROR_NOT_IDENTITY_PASSWORDS_MESSAGE = "Registration error! The entered passwords do not match.";
    public static final String LOG_WRITE_IN_FILE_SUCCESS = "Successful entry in the file!";
    public static final String LOG_ADMIN_AUTHORIZED_MESSAGE = "Administrator is logged on.";
    public static final String LOG_ID = "User with ID = ";
    public static final String LOG_AUTHORIZED_SUCCESS_MESSAGE = " successfully authorized!";
    public static final String LOG_AUTHORIZED_ERROR_MESSAGE = "Authorization Error! User with such password or username does not exist.";
    public static final String LOG_AUTHORIZED_BAN_ERROR_MESSAGE = "Authorization Error! User with such password and username banned.";
    public static final String LOG_ADMIN_EXIT_MESSAGE = " Administrator log out.";
    public static final String LOG_USER_EXIT_MESSAGE = " log out.";
    public static final String LOG_ERROR_PARAMETER_CHANGE_DATA_USER_MESSAGE = "Change user's error! One of the parameters is not valid.";
    public static final String LOG_ERROR_CHANGE_ORDER_MESSAGE = "Change order error. Order with such data does not exist.";
    public static final String LOG_ERROR_SHOW_ORDERS_MESSAGE = "Show orders error. Order list is empty.";
    public static final String LOG_ERROR_DELETE_TEMP_ORDER_MESSAGE = "Delete temp order error. Temp order list is empty.";
    public static final String LOG_WARNING_SHOW_TEMP_ORDER_MESSAGE = "Show temp orders warning. Temp order list is empty";
    public static final String LOG_ERROR_ADD_PRODUCER_MESSAGE = "Add producer error. Data from this brand, and producing country already exist.";
    public static final String LOG_ERROR_DELETE_PRODUCER_MESSAGE = "Delete producer error. The child table there is a sports nutrition products, supplied by the manufacturer, you are trying to remove.";
    public static final String LOG_ERROR_CHANGE_PRODUCER_MESSAGE = "Change producer error. The child table there is a sports nutrition products, supplied by the manufacturer, you are trying to change.";
    public static final String LOG_ERROR_ADD_TYPE_MESSAGE = "Add type error. Data from this type already exist.";
    public static final String LOG_ERROR_DELETE_TYPE_MESSAGE = "Delete type error. The child table there is a sports nutrition products with the type of sports nutrition that you are trying to remove.";
    public static final String LOG_ERROR_CHANGE_TYPE_MESSAGE = "Change type error. The child table there is a sports nutrition products with the type of sports nutrition that you are trying to change.";
    public static final String LOG_ERROR_DELETE_USER_MESSAGE = "Delete user error. The child table there is a order with the id user that you are trying to remove.";
    public static final String LOG_ERROR_SET_DISCOUNT_MESSAGE = "Error operation. Introduced Product id does not exist.";
    public static final String LOG_ERROR_DUPLICATE_PRODUCT_MESSAGE = "Add error! Product with this parameters already exists.";
    public static final String LOG_ERROR_PRODUCT_PARAMETERS_MESSAGE = "Processing error! One of the parameters is invalid.";
    public static final String LOG_ERROR_PROCESSING_PRODUCT_MESSAGE = "Processing product error. In the child orders table there is a request for a product that you are trying to remove/change. Return to the operation after the orders in the table there will be demands for this product.";
}
