package com.epam.onlineshop.command.products;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.rerouting.ProductsRerouting;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The ShowCatalogForGuestsCommand class responsible
 * for show detail information about product from database by type.
 * This command is available to the guest.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ShowCatalogForGuestsCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(ShowCatalogForGuestsCommand.class);

    /**
     * Transfers control further down the hierarchy to the command
     * to show information about product by type.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ProductsRerouting.redirectAfterPrintProducts(request, response);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
