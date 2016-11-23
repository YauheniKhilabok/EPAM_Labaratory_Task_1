package com.epam.onlineshop.command.authentication;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.rerouting.RegistrationRerouting;
import com.epam.onlineshop.services.RegistrationService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The RegistrationCommand class responsible
 * for registration user in website and put user's data in  the database.
 * This command is available only for users.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class RegistrationCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(RegistrationCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to registration user.
     * Parameters obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put(ParameterConstants.NAME_USER_PARAMETER, request.getParameter(ParameterConstants.NAME_USER_PARAMETER));
        userParameters.put(ParameterConstants.EMAIL_USER_PARAMETER, request.getParameter(ParameterConstants.EMAIL_USER_PARAMETER));
        userParameters.put(ParameterConstants.PHONE_USER_PARAMETER, request.getParameter(ParameterConstants.PHONE_USER_PARAMETER));
        userParameters.put(ParameterConstants.ADDRESS_USER_PARAMETER, request.getParameter(ParameterConstants.ADDRESS_USER_PARAMETER));
        userParameters.put(ParameterConstants.LOGIN_USER_PARAMETER, request.getParameter(ParameterConstants.LOGIN_USER_PARAMETER));
        userParameters.put(ParameterConstants.PASSWORD_USER_PARAMETER, request.getParameter(ParameterConstants.PASSWORD_USER_PARAMETER));
        userParameters.put(ParameterConstants.CONFIRM_PASSWORD_USER_PARAMETER, request.getParameter(ParameterConstants.CONFIRM_PASSWORD_USER_PARAMETER));
        Map<String, String> userParametersCopy = Collections.synchronizedMap(userParameters);
        int flag = RegistrationService.validateParameters(userParametersCopy);
        try {
            RegistrationRerouting.redirect(userParametersCopy, request, response, flag);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
