package com.epam.onlineshop.command.types;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.rerouting.TypesRerouting;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The ShowTypeListCommand class responsible for the
 * print list of types in the table.
 * This command is available to the admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ShowTypesListCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(ShowTypesListCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to print types of sport nutrition.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            TypesRerouting.redirectAfterShowTypes(request, response);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
