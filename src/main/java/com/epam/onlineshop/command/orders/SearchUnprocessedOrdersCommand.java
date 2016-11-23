package com.epam.onlineshop.command.orders;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.rerouting.OrdersRerouting;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The SearchUnprocessedOrdersCommand class responsible
 * for search only unprocessed orders and show it in table.
 * This command is available to the admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class SearchUnprocessedOrdersCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(SearchUnprocessedOrdersCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to search unprocessed orders.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            OrdersRerouting.redirectAfterSearchOrders(request, response);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
