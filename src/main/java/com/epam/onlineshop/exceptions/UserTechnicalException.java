package com.epam.onlineshop.exceptions;

/**
 * The UserTechnicalException extends Exception and this class
 * responsible for exceptions arise when filling in the form of updating user's data.
 * It may occur when incorrect data entry.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class UserTechnicalException extends Exception {
    public UserTechnicalException() {
        super();
    }

    public UserTechnicalException(String message) {
        super(message);
    }

    public UserTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserTechnicalException(Throwable cause) {
        super(cause);
    }
}
