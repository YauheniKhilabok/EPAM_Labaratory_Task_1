package com.epam.onlineshop.rerouting;

import com.epam.onlineshop.constants.*;
import com.epam.onlineshop.services.AuthorizationService;
import com.epam.onlineshop.session.SessionManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The AuthorizationRerouting class responsible for redirection after the authorization.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class AuthorizationRerouting {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(AuthorizationRerouting.class);

    /**
     * Redirect after authorization for user and admin
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @param flag     authorized flag
     * @param login    login of user
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirect(HttpServletRequest request, HttpServletResponse response, int flag, String login)
            throws ServletException, IOException {
        String data = null;
        String nameUser = null;
        if (flag == LogicFlagConstants.AUTHORIZED_AS_ADMIN_FLAG || flag == LogicFlagConstants.AUTHORIZED_AS_USER_FLAG) {
            data = AuthorizationService.getIdAndNameByLogin(login);
            nameUser = getNameFromData(data);
        }
        switch (flag) {
            case LogicFlagConstants.AUTHORIZED_AS_ADMIN_FLAG: {
                log.info(MessageConstants.LOG_ADMIN_AUTHORIZED_MESSAGE);
                response.sendRedirect(PathConstants.HOME_PAGE);
                SessionManager.createUserSession(request, WebConstants.AUTHORIZED_TRUE_VALUE,
                        WebConstants.ADMIN_ROLE_VALUE, WebConstants.USER_ID_DEFAULT_VALUE, nameUser);
                break;
            }
            case LogicFlagConstants.AUTHORIZED_AS_USER_FLAG: {
                String[] dataArray = data.split(SymbolConstants.SPACE);
                int idIndex = 0;
                String userId = dataArray[idIndex];
                log.info(MessageConstants.LOG_ID + userId + MessageConstants.LOG_AUTHORIZED_SUCCESS_MESSAGE);
                response.sendRedirect(PathConstants.HOME_PAGE);
                SessionManager.createUserSession(request, WebConstants.AUTHORIZED_TRUE_VALUE,
                        WebConstants.USER_ROLE_VALUE, userId, nameUser);
                break;
            }
            case LogicFlagConstants.AUTHORIZATION_BAN_USER_ERROR: {
                String page = request.getParameter(ParameterConstants.PAGE_PARAMETER);
                log.info(MessageConstants.LOG_AUTHORIZED_BAN_ERROR_MESSAGE);
                request.setAttribute(WebConstants.ERROR_AUTHORIZATION_ATTRIBUTE, MessageConstants.BAN_ERROR_AUTHORIZED_MESSAGE);
                request.getRequestDispatcher(page).forward(request, response);
                SessionManager.createUserSession(request, WebConstants.AUTHORIZED_FALSE_VALUE,
                        WebConstants.GUEST_ROLE_VALUE, WebConstants.USER_ID_DEFAULT_VALUE, DefaultValueConstants.DEFAULT_EMPTY_STRING);
                break;
            }
            case LogicFlagConstants.AUTHORIZATION_NOT_USER_FOUND_ERROR: {
                String page = request.getParameter(ParameterConstants.PAGE_PARAMETER);
                log.info(MessageConstants.LOG_AUTHORIZED_ERROR_MESSAGE);
                request.setAttribute(WebConstants.ERROR_AUTHORIZATION_ATTRIBUTE, MessageConstants.ERROR_AUTHORIZED_MESSAGE);
                request.getRequestDispatcher(page).forward(request, response);
                SessionManager.createUserSession(request, WebConstants.AUTHORIZED_FALSE_VALUE,
                        WebConstants.GUEST_ROLE_VALUE, WebConstants.USER_ID_DEFAULT_VALUE, DefaultValueConstants.DEFAULT_EMPTY_STRING);
                break;
            }
            default: {
                log.info(MessageConstants.LOG_INPUT_ERROR);
            }
        }
    }

    /**
     * @param data string with user id and name user
     * @return username
     */
    private static String getNameFromData(String data) {
        String[] dataArray = data.split(SymbolConstants.SPACE);
        String name;
        int firstArrayIndex = 1;
        int secondArrayIndex = 2;
        int firstStrLength = 2;
        int secondStrLength = 3;
        if (data.length() == firstStrLength) {
            name = dataArray[firstArrayIndex];
        } else if (data.length() == secondStrLength) {
            name = dataArray[secondArrayIndex];
        } else {
            name = dataArray[secondArrayIndex];
        }
        return name;
    }
}
