package com.epam.onlineshop.command.users;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.rerouting.UsersRerouting;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The ChangeUserStatusCommand class responsible for the
 * change status for user.
 * This command is available to the admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ChangeUserStatusCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(ChangeUserStatusCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to change user status.
     * Parameter userId obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = Integer.parseInt(request.getParameter(ParameterConstants.ID_USER_PARAMETER));
        try {
            UsersRerouting.redirectAfterChangeUserStatus(request, response, userId);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
