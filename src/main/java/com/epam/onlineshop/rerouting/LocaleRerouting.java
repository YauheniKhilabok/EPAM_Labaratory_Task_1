package com.epam.onlineshop.rerouting;

import com.epam.onlineshop.constants.WebConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The LocaleRerouting class responsible for redirection after the change locale on website.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class LocaleRerouting {
    /**
     * Redirect after change locale for admin and user
     *
     * @param request     parameter to the required to obtain data from the JSP,
     *                    to redirect data to the JSP and session management
     * @param response    parameter necessary for response to the client
     * @param localeValue parameter with name of locale
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterChangeLocale(HttpServletRequest request, HttpServletResponse response,
                                                 String localeValue)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute(WebConstants.LOCALE_ATTRIBUTE, localeValue);
        String pageURL = (String) session.getAttribute(WebConstants.PAGE_URL_ATTRIBUTE);
        response.sendRedirect(pageURL);
    }
}
