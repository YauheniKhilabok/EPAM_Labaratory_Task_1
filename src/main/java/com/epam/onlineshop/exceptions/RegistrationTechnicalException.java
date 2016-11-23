package com.epam.onlineshop.exceptions;

/**
 * The RegistrationTechnicalException extends Exception and this class
 * responsible for exceptions arise when filling in the form of registration for user.
 * It may occur when incorrect data entry.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class RegistrationTechnicalException extends Exception {
    public RegistrationTechnicalException() {
        super();
    }

    public RegistrationTechnicalException(String message) {
        super(message);
    }

    public RegistrationTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationTechnicalException(Throwable cause) {
        super(cause);
    }
}
