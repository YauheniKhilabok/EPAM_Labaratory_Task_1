package com.epam.onlineshop.validation;

import com.epam.onlineshop.constants.DefaultValueConstants;
import com.epam.onlineshop.constants.ExceptionConstants;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.exceptions.UserTechnicalException;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The ChangeDataUserValidator class is responsible
 * for validating the data when changing user information.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ChangeDataUserValidator {
    private static final String USER_NAME_PATTERN = "^[A-Za-zА-Яа-яЁё\\s]+$";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_PATTERN = "^375-[0-9]{2}-[0-9]{3}-[0-9]{2}-[0-9]{2}$";

    /**
     * Validation of user name
     *
     * @param userParameters map containing the user parameters
     * @throws UserTechnicalException bug with incorrect data entry
     */
    public static void checkUserName(Map<String, String> userParameters) throws UserTechnicalException {
        String userName = userParameters.get(ParameterConstants.NAME_USER_PARAMETER);
        Pattern pattern = Pattern.compile(USER_NAME_PATTERN);
        Matcher matcher = pattern.matcher(userName);
        if (!(matcher.find())) {
            throw new UserTechnicalException(ExceptionConstants.CHANGE_USER_NAME_TECHNICAL_EXCEPTION);
        }
    }

    /**
     * Validation of email
     *
     * @param userParameters map containing the user parameters
     * @throws UserTechnicalException bug with incorrect data entry
     */
    public static void checkEmail(Map<String, String> userParameters) throws UserTechnicalException {
        String email = userParameters.get(ParameterConstants.EMAIL_USER_PARAMETER);
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (!(matcher.find())) {
            throw new UserTechnicalException(ExceptionConstants.CHANGE_EMAIL_TECHNICAL_EXCEPTION);
        }
    }

    /**
     * Validation of phone
     *
     * @param userParameters map containing the user parameters
     * @throws UserTechnicalException bug with incorrect data entry
     */
    public static void checkPhone(Map<String, String> userParameters) throws UserTechnicalException {
        String phone = userParameters.get(ParameterConstants.PHONE_USER_PARAMETER);
        if (!phone.equals(DefaultValueConstants.DEFAULT_USER_PHONE)) {
            Pattern pattern = Pattern.compile(PHONE_PATTERN);
            Matcher matcher = pattern.matcher(phone);
            if (!matcher.find()) {
                throw new UserTechnicalException(ExceptionConstants.CHANGE_PHONE_TECHNICAL_EXCEPTION);
            }
        }
    }
}
