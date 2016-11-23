package com.epam.onlineshop.command.users;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.rerouting.UsersRerouting;
import com.epam.onlineshop.services.UsersService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The ChangeUserDataCommand class responsible for the
 * change in user information.
 * This command is available to the user.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ChangeUserDataCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(ChangeUserDataCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to change a user data.
     * Creates a HashMap with user's parameters, which necessary to change.
     * Parameters obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> userParameters = new HashMap<>();
        userParameters.put(ParameterConstants.NAME_USER_PARAMETER, request.getParameter(ParameterConstants.NAME_USER_PARAMETER));
        userParameters.put(ParameterConstants.EMAIL_USER_PARAMETER, request.getParameter(ParameterConstants.EMAIL_USER_PARAMETER));
        userParameters.put(ParameterConstants.PHONE_USER_PARAMETER, request.getParameter(ParameterConstants.PHONE_USER_PARAMETER));
        userParameters.put(ParameterConstants.ADDRESS_USER_PARAMETER, request.getParameter(ParameterConstants.ADDRESS_USER_PARAMETER));
        String password = request.getParameter(ParameterConstants.NEW_PASSWORD_PARAMETER);
        if (password.isEmpty()) {
            userParameters.put(ParameterConstants.PASSWORD_USER_PARAMETER, request.getParameter(ParameterConstants.OLD_PASSWORD_PARAMETER));
            userParameters.put(ParameterConstants.FLAG_PASSWORD_PARAMETER, ParameterConstants.FALSE_FLAG_PARAMETER);
        } else {
            userParameters.put(ParameterConstants.PASSWORD_USER_PARAMETER, password);
            userParameters.put(ParameterConstants.FLAG_PASSWORD_PARAMETER, ParameterConstants.TRUE_FLAG_PARAMETER);
        }
        Map<String, String> userParametersCopy = Collections.synchronizedMap(userParameters);
        int flag = UsersService.validateParameters(userParametersCopy);
        try {
            UsersRerouting.redirectAfterChangeUserData(request, response, userParametersCopy, flag);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
