package com.epam.onlineshop.command.authentication;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.rerouting.ExitRerouting;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The ExitCommand class responsible
 * for exit from output account and delete user session.
 * This command is available for user and admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ExitCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(ExitCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to output account.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ExitRerouting.redirect(request, response);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
