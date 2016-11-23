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
 * The SetDiscountCommand class responsible
 * for set discount on product.
 * This command is available to the admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class SetDiscountCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(SetDiscountCommand.class);

    /**
     * Transfers control further down the hierarchy to the command
     * to set discount on product by productId.
     * Parameter productId and discount obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int productId = Integer.parseInt(request.getParameter(ParameterConstants.ID_PRODUCT_PARAMETER));
        int discount = Integer.parseInt(request.getParameter(ParameterConstants.DISCOUNTS_PRODUCT_PARAMETER));
        try {
            ProductsRerouting.redirectAfterSetDiscount(request, response, productId, discount);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
