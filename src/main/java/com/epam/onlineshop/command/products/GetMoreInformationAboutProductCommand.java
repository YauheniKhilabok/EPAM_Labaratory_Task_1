package com.epam.onlineshop.command.products;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.rerouting.ProductsRerouting;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The GetMoreInformationAboutProductCommand class responsible
 * for show detail information about product from database by productId.
 * This command is available to the user.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class GetMoreInformationAboutProductCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(GetMoreInformationAboutProductCommand.class);

    /**
     * Transfers control further down the hierarchy to the command
     * to print information about product for user.
     * Parameter productId obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter(ParameterConstants.ID_PRODUCT_PARAMETER);
        try {
            ProductsRerouting.redirectAfterGetFullProductData(request, response, productId);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
