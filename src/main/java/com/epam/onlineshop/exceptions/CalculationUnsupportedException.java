package com.epam.onlineshop.exceptions;

/**
 * The CalculationUnsupportedException extends Exception and this class
 * is responsible for exceptions that occur when calculating
 * the cost of the order and may be provided if the list of orders with the empty.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class CalculationUnsupportedException extends Exception {
    public CalculationUnsupportedException() {
        super();
    }

    public CalculationUnsupportedException(String message) {
        super(message);
    }

    public CalculationUnsupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalculationUnsupportedException(Throwable cause) {
        super(cause);
    }
}
