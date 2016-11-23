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
 * The DeleteTempProductsOrderCommand class responsible
 * for delete from temp collection(list).
 * This command is available to the user.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class DeleteTempProductsOrderCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(DeleteTempProductsOrderCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to delete order from temp list.
     * Parameter productId obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int productId = Integer.parseInt(request.getParameter(ParameterConstants.ID_PRODUCT_PARAMETER));
        try {
            OrdersRerouting.redirectAfterDeleteTempProductsOrder(request, response, productId);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
