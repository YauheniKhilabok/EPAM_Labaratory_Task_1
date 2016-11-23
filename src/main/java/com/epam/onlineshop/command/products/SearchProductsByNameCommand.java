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
 * The SearchProductsByNameCommand class responsible
 * for search products by name and show detail information about product which was find.
 * This command is available to the user.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class SearchProductsByNameCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(SearchProductsByNameCommand.class);

    /**
     * Transfers control further down the hierarchy to the command
     * to search products by name and get information about product for user.
     * Parameter nameProduct obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String nameProduct = request.getParameter(ParameterConstants.NAME_PRODUCT_PARAMETER);
        try {
            ProductsRerouting.redirectAfterSearchProducts(request, response, nameProduct);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
