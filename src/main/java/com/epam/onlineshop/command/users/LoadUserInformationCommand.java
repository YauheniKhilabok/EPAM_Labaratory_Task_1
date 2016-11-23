package com.epam.onlineshop.command.users;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.rerouting.UsersRerouting;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The LoadUserInformationCommand class responsible for
 * displaying detailed information about the user.
 * This command is need to load information into update user's data form.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class LoadUserInformationCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(LoadUserInformationCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to load user information.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            UsersRerouting.redirectAfterLoadUserData(request, response);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
