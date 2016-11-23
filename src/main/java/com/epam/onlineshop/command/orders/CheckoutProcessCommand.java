package com.epam.onlineshop.command.orders;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.rerouting.OrdersRerouting;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The CheckoutProcessCommand class responsible
 * for order processing.
 * Order can take four states: treated, not treated, processing and cancel.
 * When the order has the status treated, it will be removed.
 * This command is available to the admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class CheckoutProcessCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(CheckoutProcessCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to order processing.
     * Parameter orderId obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter(ParameterConstants.ID_ORDER_PARAMETER));
        try {
            OrdersRerouting.redirectAfterCheckoutProcess(request, response, orderId);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
