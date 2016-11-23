package com.epam.onlineshop.exceptions;

/**
 * The ProductTechnicalException extends Exception and this class
 * responsible for exceptions arise when filling in the form of adding and changing goods.
 * It may occur when incorrect data entry.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ProductTechnicalException extends Exception {
    public ProductTechnicalException() {
        super();
    }

    public ProductTechnicalException(String message) {
        super(message);
    }

    public ProductTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductTechnicalException(Throwable cause) {
        super(cause);
    }
}
