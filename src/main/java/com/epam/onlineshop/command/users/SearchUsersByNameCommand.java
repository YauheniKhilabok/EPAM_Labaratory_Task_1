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
 * The SearchUserByNameCommand class responsible for
 * search and displaying information about user by username.
 * This command is available to the admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class SearchUsersByNameCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(SearchUsersByNameCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to search users by name.
     * Parameter userName obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter(ParameterConstants.NAME_USER_PARAMETER);
        try {
            UsersRerouting.redirectAfterSearchUsers(request, response, userName);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
