package com.epam.onlineshop.constants;

/**
 * The ExceptionConstants class announces a number of constants
 * that are passed in the throw section to determine the concrete exception.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ExceptionConstants {
    public static final String USER_NAME_TECHNICAL_EXCEPTION = "Error! The field should contain only letters of the Russian or the Latin alphabet, and must not be empty.";
    public static final String EMAIL_TECHNICAL_EXCEPTION = "Error! Entered email is incorrect.";
    public static final String PHONE_TECHNICAL_EXCEPTION = "Error! The phone number should be entered for the specified pattern.";
    public static final String ADDRESS_TECHNICAL_EXCEPTION = "Error! Address is required.";
    public static final String LOGIN_TECHNICAL_EXCEPTION = "Error! Login is incorrect.";
    public static final String PASSWORD_TECHNICAL_EXCEPTION = "Error! The password should consist of numbers and letters of upper and lower case.";
    public static final String CHANGE_USER_NAME_TECHNICAL_EXCEPTION = "Change error! The field should contain only letters of the Russian or the Latin alphabet, and must not be empty.";
    public static final String CHANGE_EMAIL_TECHNICAL_EXCEPTION = "Change error! Entered email is incorrect.";
    public static final String CHANGE_PHONE_TECHNICAL_EXCEPTION = "Change error! The phone number should be entered for the specified pattern.";
    public static final String CALCULATION_UNSUPPORTED_EXCEPTION = "The total cost can not be calculated, since the intermediate orders list is empty.";
    public static final String PATH_TO_IMAGE_TECHNICAL_EXCEPTION = "Error! Path to image is incorrect.";
    public static final String INSERT_DAO_EXCEPTION = "An exception occurred when performing the addition to the database";
    public static final String DELETE_DAO_EXCEPTION = "An exception occurred when performing the removing to the database";
    public static final String UPDATE_DAO_EXCEPTION = "An exception occurred when the update data in the database";
    public static final String SELECT_DAO_EXCEPTION = "An exception occurred during data retrieval from the database";
}
