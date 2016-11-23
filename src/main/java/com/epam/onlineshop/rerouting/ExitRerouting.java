package com.epam.onlineshop.rerouting;

import com.epam.onlineshop.constants.PathConstants;
import com.epam.onlineshop.constants.WebConstants;
import com.epam.onlineshop.session.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The ExitRerouting class responsible for redirection after the exit from account.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ExitRerouting {
    /**
     * Redirect after exit from account for admin and user
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String locale = (String) session.getAttribute(WebConstants.LOCALE_ATTRIBUTE);
        SessionManager.deleteUserSession(request);
        HttpSession localeSession = request.getSession(true);
        localeSession.setAttribute(WebConstants.LOCALE_ATTRIBUTE, locale);
        response.sendRedirect(PathConstants.HOME_PAGE);
    }
}
