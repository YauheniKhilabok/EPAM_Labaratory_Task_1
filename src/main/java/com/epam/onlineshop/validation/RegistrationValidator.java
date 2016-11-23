package com.epam.onlineshop.validation;

import com.epam.onlineshop.constants.DefaultValueConstants;
import com.epam.onlineshop.constants.ExceptionConstants;
import com.epam.onlineshop.dao.UserDAO;
import com.epam.onlineshop.exceptions.DaoException;
import com.epam.onlineshop.exceptions.RegistrationTechnicalException;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The RegistrationValidator class is responsible
 * for validating the data when add new user.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class RegistrationValidator {
    private static final Logger log = Logger.getLogger(RegistrationValidator.class);
    private static final String USER_NAME_PATTERN = "^[A-Za-zА-Яа-яЁё\\s]+$";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_PATTERN = "^375-[0-9]{2}-[0-9]{3}-[0-9]{2}-[0-9]{2}$";
    private static final String LOGIN_PATTERN = "^[a-zA-Zа-яА-ЯЁё][a-zA-Zа-яА-ЯЁё0-9-_\\.]{1,35}$";
    private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-zа-яё])(?=.*[A-ZА-ЯЁ])(?!.*\\s).*$";

    /**
     * Checks for identical email of user in the database
     *
     * @param email email which checked
     * @return It returns true if the database is a user with the same email
     * and false otherwise
     */
    public static boolean checkDuplicateEmail(String email) {
        boolean flag = false;
        try {
            flag = UserDAO.getInstance().isEmailExists(email);
        } catch (DaoException e) {
            log.error(e);
        }
        return flag;
    }

    /**
     * Checks for identical login of user in the database
     *
     * @param login login which checked
     * @return It returns true if the database is a user with the same login
     * and false otherwise
     */
    public static boolean checkDuplicateLogin(String login) {
        boolean flag = false;
        try {
            flag = UserDAO.getInstance().isLoginExists(login);
        } catch (DaoException e) {
            log.error(e);
        }
        return flag;
    }

    /**
     * Checks for identical input passwords
     *
     * @param password        password with the first field in the form of INPUT
     * @param confirmPassword password with the second field in the form of INPUT
     * @return true if password is equal and false otherwise
     */
    public static boolean checkIdentityPasswords(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    /**
     * Validation of username
     *
     * @param userName username which check
     * @throws RegistrationTechnicalException bug with incorrect data entry
     */
    public static void checkUserName(String userName) throws RegistrationTechnicalException {
        Pattern pattern = Pattern.compile(USER_NAME_PATTERN);
        Matcher matcher = pattern.matcher(userName);
        if (!(matcher.find()) || userName.equals(DefaultValueConstants.DEFAULT_EMPTY_STRING)) {
            throw new RegistrationTechnicalException(ExceptionConstants.USER_NAME_TECHNICAL_EXCEPTION);
        }
    }

    /**
     * Validation of email
     *
     * @param email email which check
     * @throws RegistrationTechnicalException bug with incorrect data entry
     */
    public static void checkEmail(String email) throws RegistrationTechnicalException {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        if (!(matcher.find()) || email.equals(DefaultValueConstants.DEFAULT_EMPTY_STRING)) {
            throw new RegistrationTechnicalException(ExceptionConstants.EMAIL_TECHNICAL_EXCEPTION);
        }
    }

    /**
     * Validation of phone
     *
     * @param phone phone which check
     * @throws RegistrationTechnicalException bug with incorrect data entry
     */
    public static void checkPhone(String phone) throws RegistrationTechnicalException {
        if (!phone.equals(DefaultValueConstants.DEFAULT_USER_PHONE)) {
            Pattern pattern = Pattern.compile(PHONE_PATTERN);
            Matcher matcher = pattern.matcher(phone);
            if (!matcher.find()) {
                throw new RegistrationTechnicalException(ExceptionConstants.PHONE_TECHNICAL_EXCEPTION);
            }
        }
    }

    /**
     * Validation of address
     *
     * @param address address which check
     * @throws RegistrationTechnicalException bug with incorrect data entry
     */
    public static void checkAddress(String address) throws RegistrationTechnicalException {
        if (address.equals(DefaultValueConstants.DEFAULT_EMPTY_STRING)) {
            throw new RegistrationTechnicalException(ExceptionConstants.ADDRESS_TECHNICAL_EXCEPTION);
        }
    }

    /**
     * Validation of login
     *
     * @param login login which check
     * @throws RegistrationTechnicalException bug with incorrect data entry
     */
    public static void checkLogin(String login) throws RegistrationTechnicalException {
        Pattern pattern = Pattern.compile(LOGIN_PATTERN);
        Matcher matcher = pattern.matcher(login);
        if (!(matcher.find()) || login.equals(DefaultValueConstants.DEFAULT_EMPTY_STRING)) {
            throw new RegistrationTechnicalException(ExceptionConstants.LOGIN_TECHNICAL_EXCEPTION);
        }
    }

    /**
     * Validation of password
     *
     * @param password password which check
     * @throws RegistrationTechnicalException bug with incorrect data entry
     */
    public static void checkPassword(String password) throws RegistrationTechnicalException {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        if (!(matcher.find()) || password.equals(DefaultValueConstants.DEFAULT_EMPTY_STRING)) {
            throw new RegistrationTechnicalException(ExceptionConstants.PASSWORD_TECHNICAL_EXCEPTION);
        }
    }
}
