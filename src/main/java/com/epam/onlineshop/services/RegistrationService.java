package com.epam.onlineshop.services;

import com.epam.onlineshop.constants.*;
import com.epam.onlineshop.dao.UserDAO;
import com.epam.onlineshop.encryption.PasswordEncryption;
import com.epam.onlineshop.entity.ObjectFactory;
import com.epam.onlineshop.entity.users.User;
import com.epam.onlineshop.exceptions.DaoException;
import com.epam.onlineshop.exceptions.RegistrationTechnicalException;
import com.epam.onlineshop.file.MyFileWriter;
import com.epam.onlineshop.validation.RegistrationValidator;
import org.apache.log4j.Logger;

import java.util.Map;
/**
 * The RegistrationService class responsible for performing operations on data in the users table.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class RegistrationService {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(RegistrationService.class);

    /**
     *
     * @param userParameters parameters of user which will be validate
     * @return flag. Flag can be success or error.
     */
    public static int validateParameters(Map<String, String> userParameters) {
        if (RegistrationValidator.checkDuplicateEmail(userParameters.get(ParameterConstants.EMAIL_USER_PARAMETER))) {
            return LogicFlagConstants.FIRST_ERROR_FLAG;
        }
        if (RegistrationValidator.checkDuplicateLogin(userParameters.get(ParameterConstants.LOGIN_USER_PARAMETER))) {
            return LogicFlagConstants.SECOND_ERROR_FLAG;
        }
        if (!RegistrationValidator.checkIdentityPasswords(userParameters.get(ParameterConstants.PASSWORD_USER_PARAMETER),
                userParameters.get(ParameterConstants.CONFIRM_PASSWORD_USER_PARAMETER))) {
            return LogicFlagConstants.THIRD_ERROR_FLAG;
        }
        try {
            RegistrationValidator.checkUserName(userParameters.get(ParameterConstants.NAME_USER_PARAMETER));
            RegistrationValidator.checkEmail(userParameters.get(ParameterConstants.EMAIL_USER_PARAMETER));
            RegistrationValidator.checkPhone(userParameters.get(ParameterConstants.PHONE_USER_PARAMETER));
            RegistrationValidator.checkAddress(userParameters.get(ParameterConstants.ADDRESS_USER_PARAMETER));
            RegistrationValidator.checkLogin(userParameters.get(ParameterConstants.LOGIN_USER_PARAMETER));
            RegistrationValidator.checkPassword(userParameters.get(ParameterConstants.PASSWORD_USER_PARAMETER));
        } catch (RegistrationTechnicalException e) {
            log.error(e);
            return LogicFlagConstants.FOURTH_ERROR_FLAG;
        }
        return LogicFlagConstants.SUCCESS_FLAG;
    }

    /**
     *
     * @param userParameters new parameters of user who finished registration
     */
    public static void registration(Map<String, String> userParameters) {
        ObjectFactory factory = new ObjectFactory();
        User user = factory.createUser();
        user.setNameUser(userParameters.get(ParameterConstants.NAME_USER_PARAMETER));
        user.setEmail(userParameters.get(ParameterConstants.EMAIL_USER_PARAMETER));
        user.setPhone(userParameters.get(ParameterConstants.PHONE_USER_PARAMETER));
        user.setAddress(userParameters.get(ParameterConstants.ADDRESS_USER_PARAMETER));
        String login = userParameters.get(ParameterConstants.LOGIN_USER_PARAMETER);
        user.setLogin(login);
        String password = userParameters.get(ParameterConstants.PASSWORD_USER_PARAMETER);
        user.setPassword(PasswordEncryption.encryptMD5(password));
        String dataFile = DefaultValueConstants.DEFAULT_LOGIN_STRING_FOR_FILE + login + SymbolConstants.SPACE +
                DefaultValueConstants.DEFAULT_PASSWORD_STRING_FOR_FILE + password + SymbolConstants.PARAGRAPH;
        if (MyFileWriter.writeInFile(PathConstants.OUTGOING_PASSWORDS_TXT, dataFile, true)) {
            log.info(MessageConstants.LOG_WRITE_IN_FILE_SUCCESS);
        }
        try {
            UserDAO.getInstance().insert(user);
        } catch (DaoException e) {
            log.error(e);
        }
    }
}
