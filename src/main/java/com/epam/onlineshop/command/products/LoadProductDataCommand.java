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
 * The LoadProductDataCommand class responsible
 * for load product information into change form by productId.
 * This command is available to the admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class LoadProductDataCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(LoadProductDataCommand.class);

    /**
     * Transfers control further down the hierarchy to the command
     * to load product information into change form for admin.
     * Parameter productId obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter(ParameterConstants.ID_PRODUCT_PARAMETER);
        try {
            ProductsRerouting.redirectAfterLoadProductData(request, response, productId);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
