package com.epam.onlineshop.services;

import com.epam.onlineshop.dao.UserDAO;
import com.epam.onlineshop.encryption.PasswordEncryption;
import com.epam.onlineshop.exceptions.DaoException;
import org.apache.log4j.Logger;

/**
 * The AuthorizationService class responsible for performing operations on data in the users table.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class AuthorizationService {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(AuthorizationService.class);

    /**
     * @param login    login of user
     * @param password password of user
     * @return flag of authorization. Can be success and error
     */
    public static int authorization(String login, String password) {
        int flag = 0;
        try {
            flag = UserDAO.getInstance().authorization(login, PasswordEncryption.encryptMD5(password));
        } catch (DaoException e) {
            log.error(e);
        }
        return flag;
    }

    /**
     * @param login login of user
     * @return id and name of user
     */
    public static String getIdAndNameByLogin(String login) {
        String data = null;
        try {
            data = UserDAO.getInstance().getIdAndNameByLogin(login);
        } catch (DaoException e) {
            log.error(e);
        }
        return data;
    }
}
