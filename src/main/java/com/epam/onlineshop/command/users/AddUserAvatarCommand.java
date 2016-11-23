package com.epam.onlineshop.command.users;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.rerouting.UsersRerouting;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The AddUserAvatarCommand class responsible for the
 * implementation of PS avatar Adding a user command.
 * This command is available to the user.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class AddUserAvatarCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(AddUserAvatarCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to add a user avatar.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            UsersRerouting.redirectAfterAddAvatar(request, response);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
