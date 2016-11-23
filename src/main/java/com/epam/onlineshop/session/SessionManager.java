package com.epam.onlineshop.session;

import com.epam.onlineshop.constants.LogicFlagConstants;
import com.epam.onlineshop.constants.MessageConstants;
import com.epam.onlineshop.constants.WebConstants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The SessionManager class, responsible for working with sessions and management.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class SessionManager {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(SessionManager.class);

    /**
     * Method creates a new session
     *
     * @param request    is necessary to create a new session
     * @param authorized flag responsible for the fact that the user authorization
     * @param role       the role of the user after login
     * @param userId     user id who authorized
     * @param nameUser   username who authorized
     */
    public static void createUserSession(HttpServletRequest request, boolean authorized, String role, String userId, String nameUser) {
        HttpSession session = request.getSession(true);
        session.setAttribute(WebConstants.AUTHORIZED_ATTRIBUTE, authorized);
        session.setAttribute(WebConstants.ROLE_ATTRIBUTE, role);
        session.setAttribute(WebConstants.USER_ID_ATTRIBUTE, userId);
        session.setAttribute(WebConstants.NAME_USER_ATTRIBUTE, nameUser);
    }

    /**
     * Method delete the session
     *
     * @param request is necessary to delete a session
     */
    public static void deleteUserSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        inform(session);
        session.invalidate();
    }

    /**
     * Method provides information about who is logged in and who exit of his account
     *
     * @param session parameter required to obtain information about the session
     */
    private static void inform(HttpSession session) {
        Boolean authorized = (Boolean) session.getAttribute(WebConstants.AUTHORIZED_ATTRIBUTE);
        if (authorized) {
            String role = (String) session.getAttribute(WebConstants.ROLE_ATTRIBUTE);
            int flag = getFlagByRole(role);
            switch (flag) {
                case LogicFlagConstants.EXIT_ADMIN_FLAG: {
                    log.info(MessageConstants.LOG_ADMIN_EXIT_MESSAGE);
                    break;
                }
                case LogicFlagConstants.EXIT_USER_FLAG: {
                    String user_id = (String) session.getAttribute(WebConstants.USER_ID_ATTRIBUTE);
                    log.info(MessageConstants.LOG_ID + user_id + MessageConstants.LOG_USER_EXIT_MESSAGE);
                    break;
                }
                default: {
                    log.info(MessageConstants.LOG_INPUT_ERROR);
                }
            }
        }
    }

    /**
     * Determines who is logged off
     *
     * @param role the option for which the output will be determined by the flag
     * @return flag
     */
    private static int getFlagByRole(String role) {
        int flag = 0;
        if (role.equals(WebConstants.ADMIN_ROLE_VALUE)) {
            flag = LogicFlagConstants.EXIT_ADMIN_FLAG;
        } else if (role.equals(WebConstants.USER_ROLE_VALUE)) {
            flag = LogicFlagConstants.EXIT_USER_FLAG;
        }
        return flag;
    }
}
