package com.epam.onlineshop.validation;

import com.epam.onlineshop.constants.DefaultValueConstants;
import com.epam.onlineshop.constants.ExceptionConstants;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.dao.ProductDAO;
import com.epam.onlineshop.exceptions.DaoException;
import com.epam.onlineshop.exceptions.ProductTechnicalException;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * The ProductsValidator class is responsible
 * for validating the data when add new product.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ProductsValidator {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(ProductsValidator.class);

    /**
     * Checks for identical goods in the database
     *
     * @param productParameters map containing the product parameters
     * @return It returns true if the database is a product with the same data
     * and false otherwise
     */
    public static boolean checkDuplicateProduct(Map<String, String> productParameters) {
        String nameProduct = productParameters.get(ParameterConstants.NAME_PRODUCT_PARAMETER);
        String type = productParameters.get(ParameterConstants.TYPE_PRODUCT_PARAMETER);
        String brand = productParameters.get(ParameterConstants.BRAND_PRODUCT_PARAMETER);
        String taste = productParameters.get(ParameterConstants.TASTE_PRODUCT_PARAMETER);
        boolean flag = false;
        try {
            flag = ProductDAO.getInstance().isProductExists(nameProduct, type, brand, taste);
        } catch (DaoException e) {
            log.error(e);
        }
        return flag;
    }

    /**
     * Validate path to image
     *
     * @param pathToImage path to image
     * @throws ProductTechnicalException bug with incorrect data entry
     */
    public static void checkPathToImage(String pathToImage) throws ProductTechnicalException {
        if (!pathToImage.contains(DefaultValueConstants.JPG_FORMAT) &&
                !pathToImage.contains(DefaultValueConstants.PNG_FORMAT) &&
                !pathToImage.contains(DefaultValueConstants.GIF_FORMAT)) {
            throw new ProductTechnicalException(ExceptionConstants.PATH_TO_IMAGE_TECHNICAL_EXCEPTION);
        }
    }
}
