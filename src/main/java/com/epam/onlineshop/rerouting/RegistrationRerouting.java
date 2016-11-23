package com.epam.onlineshop.rerouting;

import com.epam.onlineshop.constants.LogicFlagConstants;
import com.epam.onlineshop.constants.MessageConstants;
import com.epam.onlineshop.constants.PathConstants;
import com.epam.onlineshop.constants.WebConstants;
import com.epam.onlineshop.services.RegistrationService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * The RegistrationRerouting class responsible for redirection after the registration.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class RegistrationRerouting {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(RegistrationRerouting.class);

    /**
     * Redirect after registration for new user
     *
     * @param userParameters map with parameters of product which will be add
     * @param request        parameter to the required to obtain data from the JSP,
     *                       to redirect data to the JSP and session management
     * @param response       parameter necessary for response to the client
     * @param flag           PS responsible operation on success and displays error otherwise
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirect(Map<String, String> userParameters, HttpServletRequest request,
                                HttpServletResponse response, int flag)
            throws ServletException, IOException {
        switch (flag) {
            case LogicFlagConstants.SUCCESS_FLAG: {
                RegistrationService.registration(userParameters);
                log.info(MessageConstants.LOG_SUCCESS_REGISTRATION_MESSAGE);
                request.setAttribute(WebConstants.SUCCESS_REGISTRATION_ATTRIBUTE, MessageConstants.SUCCESS_REGISTRATION_MESSAGE);
                request.getRequestDispatcher(PathConstants.REGISTRATION_PAGE).forward(request, response);
                break;
            }
            case LogicFlagConstants.FIRST_ERROR_FLAG: {
                log.info(MessageConstants.LOG_ERROR_DUPLICATE_EMAIL_MESSAGE);
                request.setAttribute(WebConstants.ERROR_REGISTRATION_ATTRIBUTE, MessageConstants.ERROR_DUPLICATE_EMAIL_MESSAGE);
                request.getRequestDispatcher(PathConstants.REGISTRATION_PAGE).forward(request, response);
                break;
            }
            case LogicFlagConstants.SECOND_ERROR_FLAG: {
                log.info(MessageConstants.LOG_ERROR_DUPLICATE_LOGIN_MESSAGE);
                request.setAttribute(WebConstants.ERROR_REGISTRATION_ATTRIBUTE, MessageConstants.ERROR_DUPLICATE_LOGIN_MESSAGE);
                request.getRequestDispatcher(PathConstants.REGISTRATION_PAGE).forward(request, response);
                break;
            }
            case LogicFlagConstants.THIRD_ERROR_FLAG: {
                log.info(MessageConstants.LOG_ERROR_NOT_IDENTITY_PASSWORDS_MESSAGE);
                request.setAttribute(WebConstants.ERROR_REGISTRATION_ATTRIBUTE, MessageConstants.ERROR_NOT_IDENTITY_PASSWORDS_MESSAGE);
                request.getRequestDispatcher(PathConstants.REGISTRATION_PAGE).forward(request, response);
                break;
            }
            case LogicFlagConstants.FOURTH_ERROR_FLAG: {
                log.info(MessageConstants.LOG_ERROR_PARAMETER_REGISTRATION_MESSAGE);
                request.setAttribute(WebConstants.ERROR_REGISTRATION_ATTRIBUTE, MessageConstants.ERROR_PARAMETER_REGISTRATION_MESSAGE);
                request.getRequestDispatcher(PathConstants.REGISTRATION_PAGE).forward(request, response);
                break;
            }
            default: {
                log.info(MessageConstants.LOG_INPUT_ERROR);
            }
        }
    }
}
