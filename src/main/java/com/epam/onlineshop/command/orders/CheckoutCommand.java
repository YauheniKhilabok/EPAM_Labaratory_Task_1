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
 * The CheckoutCommand class responsible
 * for checkout order.
 * After this command order place in user's basket,
 * where user can delete order and see total cost of order.
 * This command is available to the user.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class CheckoutCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(CheckoutCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to checkout order.
     * Parameters obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String deliveryCondition = request.getParameter(ParameterConstants.DELIVERY_CONDITION_PARAMETER);
        String typeOfPayment = request.getParameter(ParameterConstants.TYPE_OF_PAYMENT_PARAMETER);
        try {
            OrdersRerouting.redirectAfterCheckout(request, response, deliveryCondition, typeOfPayment);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
