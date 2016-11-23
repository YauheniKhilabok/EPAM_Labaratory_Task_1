package com.epam.onlineshop.command.authentication;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.rerouting.AuthorizationRerouting;
import com.epam.onlineshop.services.AuthorizationService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The AuthorizationCommand class responsible
 * for authorized user in website.
 * This command is available for user and admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class AuthorizationCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(AuthorizationCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to authorized user.
     * Parameters obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(ParameterConstants.LOGIN_USER_PARAMETER);
        String password = request.getParameter(ParameterConstants.PASSWORD_USER_PARAMETER);
        int flag = AuthorizationService.authorization(login, password);
        try {
            AuthorizationRerouting.redirect(request, response, flag, login);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
