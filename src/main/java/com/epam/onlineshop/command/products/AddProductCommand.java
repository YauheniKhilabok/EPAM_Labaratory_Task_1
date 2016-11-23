package com.epam.onlineshop.command.products;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.rerouting.ProductsRerouting;
import com.epam.onlineshop.services.ProductsService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The AddProductCommand class responsible
 * for adding new product to the database.
 * This command is available to the admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class AddProductCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(AddProductCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to add new product to the database.
     * Parameters obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> productParameters = new HashMap<>();
        productParameters.put(ParameterConstants.DATE_OF_DELIVERY_PRODUCT_PARAMETER, request.getParameter(ParameterConstants.DATE_OF_DELIVERY_PRODUCT_PARAMETER));
        productParameters.put(ParameterConstants.SHELF_LIFE_PRODUCT_PARAMETER, request.getParameter(ParameterConstants.SHELF_LIFE_PRODUCT_PARAMETER));
        productParameters.put(ParameterConstants.NAME_PRODUCT_PARAMETER, request.getParameter(ParameterConstants.NAME_PRODUCT_PARAMETER));
        productParameters.put(ParameterConstants.TYPE_PRODUCT_PARAMETER, request.getParameter(ParameterConstants.TYPE_PRODUCT_PARAMETER));
        productParameters.put(ParameterConstants.BRAND_PRODUCT_PARAMETER, request.getParameter(ParameterConstants.BRAND_PRODUCT_PARAMETER));
        productParameters.put(ParameterConstants.NUMBER_OF_PACKAGES_PRODUCT_PARAMETER, request.getParameter(ParameterConstants.NUMBER_OF_PACKAGES_PRODUCT_PARAMETER));
        productParameters.put(ParameterConstants.UNIT_PRODUCT_PARAMETER, request.getParameter(ParameterConstants.UNIT_PRODUCT_PARAMETER));
        productParameters.put(ParameterConstants.NUMBER_PER_UNIT_PRODUCT_PARAMETER, request.getParameter(ParameterConstants.NUMBER_PER_UNIT_PRODUCT_PARAMETER));
        productParameters.put(ParameterConstants.TASTE_PRODUCT_PARAMETER, request.getParameter(ParameterConstants.TASTE_PRODUCT_PARAMETER));
        productParameters.put(ParameterConstants.DISCOUNTS_PRODUCT_PARAMETER, request.getParameter(ParameterConstants.DISCOUNTS_PRODUCT_PARAMETER));
        productParameters.put(ParameterConstants.PURCHASE_PRICE_PRODUCT_PARAMETER, request.getParameter(ParameterConstants.PURCHASE_PRICE_PRODUCT_PARAMETER));
        productParameters.put(ParameterConstants.DESCRIPTION_PRODUCT_PARAMETER, request.getParameter(ParameterConstants.DESCRIPTION_PRODUCT_PARAMETER));
        productParameters.put(ParameterConstants.PATH_TO_IMAGE_PRODUCT_PARAMETER, request.getParameter(ParameterConstants.PATH_TO_IMAGE_PRODUCT_PARAMETER));
        Map<String, String> productParametersCopy = Collections.synchronizedMap(productParameters);
        int flag = ProductsService.validateParameters(productParametersCopy);
        try {
            ProductsRerouting.redirectAfterAddProduct(productParametersCopy, request, response, flag);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
