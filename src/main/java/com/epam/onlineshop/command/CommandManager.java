package com.epam.onlineshop.command;

import com.epam.onlineshop.command.authentication.AuthorizationCommand;
import com.epam.onlineshop.command.authentication.ExitCommand;
import com.epam.onlineshop.command.authentication.RegistrationCommand;
import com.epam.onlineshop.command.locale.ChangeLocaleCommand;
import com.epam.onlineshop.command.orders.*;
import com.epam.onlineshop.command.producers.AddProducerCommand;
import com.epam.onlineshop.command.producers.ChangeProducerCommand;
import com.epam.onlineshop.command.producers.DeleteProducerCommand;
import com.epam.onlineshop.command.producers.ShowProducersListCommand;
import com.epam.onlineshop.command.products.*;
import com.epam.onlineshop.command.reviews.AddCommentCommand;
import com.epam.onlineshop.command.reviews.ChangeCommentCommand;
import com.epam.onlineshop.command.reviews.DeleteCommentCommand;
import com.epam.onlineshop.command.reviews.PrintReviewsCommand;
import com.epam.onlineshop.command.statistics.AddPeriodCommand;
import com.epam.onlineshop.command.statistics.ShowAdminStatisticsCommand;
import com.epam.onlineshop.command.types.AddTypeCommand;
import com.epam.onlineshop.command.types.ChangeTypeCommand;
import com.epam.onlineshop.command.types.DeleteTypeCommand;
import com.epam.onlineshop.command.types.ShowTypesListCommand;
import com.epam.onlineshop.command.users.*;
import com.epam.onlineshop.constants.CommandConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * The CommandManager class is required to determine
 * which command should be called.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class CommandManager {
    /**
     * Property - commands,
     * the key is the constant value of the command name,
     * and the value - this particular command, which implements ICommand interface.
     *
     * @see Map
     */
    private Map<String, ICommand> commands;

    /**
     * Creates a new object of CommandManager,
     * initializes the class field commands and fills it with values.
     *
     * @see HashMap
     * @see CommandConstants
     */
    public CommandManager() {
        commands = new HashMap<>();
        commands.put(CommandConstants.REGISTRATION_COMMAND, new RegistrationCommand());
        commands.put(CommandConstants.AUTHORIZATION_COMMAND, new AuthorizationCommand());
        commands.put(CommandConstants.EXIT_COMMAND, new ExitCommand());
        commands.put(CommandConstants.ADD_COMMENT_COMMAND, new AddCommentCommand());
        commands.put(CommandConstants.DELETE_COMMENT_COMMAND, new DeleteCommentCommand());
        commands.put(CommandConstants.CHANGE_COMMENT_COMMAND, new ChangeCommentCommand());
        commands.put(CommandConstants.PRINT_REVIEWS_COMMAND, new PrintReviewsCommand());
        commands.put(CommandConstants.SHOW_CATALOG_FOR_GUESTS_COMMAND, new ShowCatalogForGuestsCommand());
        commands.put(CommandConstants.PRINT_PRODUCTS_FOR_USER_COMMAND, new PrintProductsForUserCommand());
        commands.put(CommandConstants.SEARCH_PRODUCTS_BY_NAME_COMMAND, new SearchProductsByNameCommand());
        commands.put(CommandConstants.SORT_PRODUCTS_BY_PRICE_COMMAND, new SortProductsByPriceCommand());
        commands.put(CommandConstants.LOAD_USER_INFORMATION_COMMAND, new LoadUserInformationCommand());
        commands.put(CommandConstants.CHANGE_USER_DATA_COMMAND, new ChangeUserDataCommand());
        commands.put(CommandConstants.GET_MORE_INFORMATION_ABOUT_PRODUCT_COMMAND, new GetMoreInformationAboutProductCommand());
        commands.put(CommandConstants.SHOW_STATISTIC_FOR_USER_COMMAND, new ShowStatisticForUserCommand());
        commands.put(CommandConstants.ADD_PRODUCT_TO_LIST_COMMAND, new AddProductToListCommand());
        commands.put(CommandConstants.SHOW_TEMP_PRODUCTS_ORDER_COMMAND, new ShowTempProductsOrderCommand());
        commands.put(CommandConstants.DELETE_TEMP_PRODUCTS_ORDER_COMMAND, new DeleteTempProductsOrderCommand());
        commands.put(CommandConstants.CHECKOUT_COMMAND, new CheckoutCommand());
        commands.put(CommandConstants.PRINT_ORDERS_FOR_USER_COMMAND, new PrintOrdersForUserCommand());
        commands.put(CommandConstants.DELETE_ORDER_COMMAND, new DeleteOrderCommand());
        commands.put(CommandConstants.CHANGE_ORDER_COMMAND, new ChangeOrderCommand());
        commands.put(CommandConstants.SHOW_PRODUCERS_LIST_COMMAND, new ShowProducersListCommand());
        commands.put(CommandConstants.ADD_PRODUCER_COMMAND, new AddProducerCommand());
        commands.put(CommandConstants.DELETE_PRODUCER_COMMAND, new DeleteProducerCommand());
        commands.put(CommandConstants.CHANGE_PRODUCER_COMMAND, new ChangeProducerCommand());
        commands.put(CommandConstants.SHOW_TYPES_LIST_COMMAND, new ShowTypesListCommand());
        commands.put(CommandConstants.ADD_TYPE_COMMAND, new AddTypeCommand());
        commands.put(CommandConstants.DELETE_TYPE_COMMAND, new DeleteTypeCommand());
        commands.put(CommandConstants.CHANGE_TYPE_COMMAND, new ChangeTypeCommand());
        commands.put(CommandConstants.SHOW_USERS_LIST_COMMAND, new ShowUsersListCommand());
        commands.put(CommandConstants.SORT_USERS_BY_NAME_COMMAND, new SortUsersByNameCommand());
        commands.put(CommandConstants.SEARCH_USERS_BY_NAME_COMMAND, new SearchUsersByNameCommand());
        commands.put(CommandConstants.DELETE_USER_COMMAND, new DeleteUserCommand());
        commands.put(CommandConstants.CHANGE_USER_ROLE_COMMAND, new ChangeUserRoleCommand());
        commands.put(CommandConstants.CHANGE_USER_STATUS_COMMAND, new ChangeUserStatusCommand());
        commands.put(CommandConstants.SHOW_PRODUCTS_LIST_COMMAND, new ShowProductsListCommand());
        commands.put(CommandConstants.SEARCH_PRODUCTS_FOR_ADMIN_COMMAND, new SearchProductForAdminCommand());
        commands.put(CommandConstants.SORT_PRODUCTS_BY_SHELF_LIFE_COMMAND, new SortProductsByShelfLifeCommand());
        commands.put(CommandConstants.SET_DISCOUNT_COMMAND, new SetDiscountCommand());
        commands.put(CommandConstants.ADD_PRODUCT_COMMAND, new AddProductCommand());
        commands.put(CommandConstants.DELETE_PRODUCT_COMMAND, new DeleteProductCommand());
        commands.put(CommandConstants.LOAD_PRODUCT_DATA_COMMAND, new LoadProductDataCommand());
        commands.put(CommandConstants.CHANGE_PRODUCT_COMMAND, new ChangeProductCommand());
        commands.put(CommandConstants.SHOW_ORDERS_LIST_COMMAND, new ShowOrdersListCommand());
        commands.put(CommandConstants.SEARCH_UNPROCESSED_ORDERS_COMMAND, new SearchUnprocessedOrdersCommand());
        commands.put(CommandConstants.SORT_ORDERS_COMMAND, new SortOrdersCommand());
        commands.put(CommandConstants.CHECKOUT_PROCESS_COMMAND, new CheckoutProcessCommand());
        commands.put(CommandConstants.SHOW_ADMIN_STATISTICS_COMMAND, new ShowAdminStatisticsCommand());
        commands.put(CommandConstants.ADD_PERIOD_COMMAND, new AddPeriodCommand());
        commands.put(CommandConstants.CHANGE_LOCALE_COMMAND, new ChangeLocaleCommand());
        commands.put(CommandConstants.ADD_USER_AVATAR_COMMAND, new AddUserAvatarCommand());
    }

    /**
     * This method is used to perform specific commands.
     *
     * @param commandString This is the parameter to getCommand method,
     *                      command name, which is created with the help of a certain class
     *                      that implements ICommand interface.
     * @return ICommand This returns concrete command using a built-in method get().
     * @see Map#get(Object)
     */
    public ICommand getCommand(String commandString) {
        return commands.get(commandString);
    }
}
