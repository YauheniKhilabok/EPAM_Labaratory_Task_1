package com.epam.onlineshop.constants;

/**
 * The LogicFlagConstants class announces a group of flags
 * that help to establish the result and the further course of the program.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class LogicFlagConstants {
    public static final int SUCCESS_FLAG = 0;
    public static final int FIRST_ERROR_FLAG = 1;
    public static final int SECOND_ERROR_FLAG = 2;
    public static final int THIRD_ERROR_FLAG = 3;
    public static final int FOURTH_ERROR_FLAG = 4;
    public static final int AUTHORIZED_AS_ADMIN_FLAG = 1;
    public static final int AUTHORIZED_AS_USER_FLAG = 2;
    public static final int AUTHORIZATION_BAN_USER_ERROR = 3;
    public static final int AUTHORIZATION_NOT_USER_FOUND_ERROR = 4;
    public static final int EXIT_ADMIN_FLAG = 1;
    public static final int EXIT_USER_FLAG = 2;
}
