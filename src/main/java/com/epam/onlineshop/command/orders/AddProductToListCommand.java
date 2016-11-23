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
 * The AddProductToListCommand class responsible
 * for add product that the customer has decided to buy
 * and and place it in a temporary collection.
 * This command is available to the user.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class AddProductToListCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(AddProductToListCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to add product into temp list.
     * Parameters obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int productId = Integer.parseInt(request.getParameter(ParameterConstants.ID_PRODUCT_PARAMETER));
        int numberOfPackages = Integer.parseInt(request.getParameter(ParameterConstants.NUMBER_OF_PACKAGES_PRODUCT_PARAMETER));
        try {
            OrdersRerouting.redirectAfterAddTempProductsOrder(request, response, productId, numberOfPackages);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
