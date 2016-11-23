package com.epam.onlineshop.services;

import com.epam.onlineshop.constants.DefaultValueConstants;
import com.epam.onlineshop.constants.LogicFlagConstants;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.dao.OrderDAO;
import com.epam.onlineshop.dao.UserDAO;
import com.epam.onlineshop.encryption.PasswordEncryption;
import com.epam.onlineshop.entity.users.User;
import com.epam.onlineshop.exceptions.DaoException;
import com.epam.onlineshop.exceptions.UserTechnicalException;
import com.epam.onlineshop.validation.ChangeDataUserValidator;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * The UsersService class responsible for performing operations on data in the users table.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class UsersService {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(UsersService.class);

    /**
     * @param userParameters map with new parameters for user, who add to the users table
     * @return flag. Flag can be success or error.
     */
    public static int validateParameters(Map<String, String> userParameters) {
        try {
            ChangeDataUserValidator.checkUserName(userParameters);
            ChangeDataUserValidator.checkEmail(userParameters);
            ChangeDataUserValidator.checkPhone(userParameters);
        } catch (UserTechnicalException e) {
            log.error(e);
            return LogicFlagConstants.FIRST_ERROR_FLAG;
        }
        return LogicFlagConstants.SUCCESS_FLAG;
    }

    /**
     * @param userId id of user who data will be loaded
     * @return user
     */
    public static User loadUserInformation(String userId) {
        User user = null;
        try {
            user = UserDAO.getInstance().loadUserInformation(userId);
        } catch (DaoException e) {
            log.error(e);
        }
        return user;
    }

    /**
     * @param userId         id of user who will be updated
     * @param userParameters map with new parameters for user
     */
    public static void changeUserData(String userId, Map<String, String> userParameters) {
        int key = Integer.parseInt(userId);
        User user = new User();
        user.setNameUser(userParameters.get(ParameterConstants.NAME_USER_PARAMETER));
        user.setEmail(userParameters.get(ParameterConstants.EMAIL_USER_PARAMETER));
        user.setPhone(userParameters.get(ParameterConstants.PHONE_USER_PARAMETER));
        user.setAddress(userParameters.get(ParameterConstants.ADDRESS_USER_PARAMETER));
        String password = userParameters.get(ParameterConstants.PASSWORD_USER_PARAMETER);
        String flag = userParameters.get(ParameterConstants.FLAG_PASSWORD_PARAMETER);
        if (flag.equals(ParameterConstants.TRUE_FLAG_PARAMETER)) {
            user.setPassword(PasswordEncryption.encryptMD5(password));
        } else {
            user.setPassword(password);
        }
        try {
            UserDAO.getInstance().change(key, user);
        } catch (DaoException e) {
            log.error(e);
        }
    }

    /**
     * @param userId id of user who will be deleted
     * @return true if operation finished successfully and false otherwise
     */
    public static boolean deleteUser(int userId) {
        boolean flag = false;
        try {
            flag = OrderDAO.getInstance().isThereOrdersByUserId(userId);
            if (!flag) {
                UserDAO.getInstance().delete(userId);
            }
        } catch (DaoException e) {
            log.error(e);
        }
        return flag;
    }

    /**
     * @param offset        the index in the table, from which the output begins
     * @param numberRecords the number of records that will be reflected
     * @return list with users
     */
    public static List<User> printUsers(int offset, int numberRecords) {
        List<User> types = null;
        try {
            types = UserDAO.getInstance().getPartForPagination(offset, numberRecords);
        } catch (DaoException e) {
            log.error(e);
        }
        return types;
    }

    /**
     * @param offset        the index in the table, from which the output begins
     * @param numberRecords the number of records that will be reflected
     * @param sortingType   type of sorting. Can be asc and desc
     * @return list with sorted users by name
     */
    public static List<User> sortUsers(int offset, int numberRecords, String sortingType) {
        List<User> users = null;
        try {
            users = UserDAO.getInstance().sort(offset, numberRecords, sortingType);
        } catch (DaoException e) {
            log.error(e);
        }
        return users;
    }

    /**
     * @param userName username, who will be find
     * @return list with find users
     */
    public static List<User> searchUsers(String userName) {
        List<User> users = null;
        try {
            users = UserDAO.getInstance().search(userName);
        } catch (DaoException e) {
            log.error(e);
        }
        return users;
    }

    /**
     * @param userId id of user who role will be updated
     */
    public static void changeUserRole(int userId) {
        String role;
        try {
            role = UserDAO.getInstance().getUserRole(userId);
            if (role.equals(DefaultValueConstants.DEFAULT_USER_ROLE)) {
                UserDAO.getInstance().changeUserRole(userId, DefaultValueConstants.DEFAULT_ADMIN_ROLE);
            } else {
                UserDAO.getInstance().changeUserRole(userId, DefaultValueConstants.DEFAULT_USER_ROLE);
            }
        } catch (DaoException e) {
            log.error(e);
        }
    }

    /**
     * @param userId id of user who status will be updated
     */
    public static void changeUserStatus(int userId) {
        String status;
        try {
            status = UserDAO.getInstance().getUserStatus(userId);
            if (status.equals(DefaultValueConstants.DEFAULT_USER_STATUS)) {
                UserDAO.getInstance().changeUserStatus(userId, DefaultValueConstants.BAN_USER_STATUS);
            } else {
                UserDAO.getInstance().changeUserStatus(userId, DefaultValueConstants.DEFAULT_USER_STATUS);
            }
        } catch (DaoException e) {
            log.error(e);
        }
    }

    /**
     * @param fileName file name which add to column avatar in users table
     * @param userId   id of user, who updated avatar
     */
    public static void changeAvatarForUser(String fileName, int userId) {
        String partOfFilePath = "../../img/avatars/";
        String fullPath = partOfFilePath + fileName;
        try {
            UserDAO.getInstance().changeAvatar(fullPath, userId);
        } catch (DaoException e) {
            log.error(e);
        }
    }

    /**
     * @return number of records in users table
     */
    public static int getNumberRecords() {
        return UserDAO.getInstance().getNumberRecords();
    }
}
