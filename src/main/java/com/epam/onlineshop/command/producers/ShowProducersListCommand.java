package com.epam.onlineshop.command.producers;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.rerouting.ProducersRerouting;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The ShowProducersListCommand class responsible
 * for print all producers from database in table.
 * This command is available to the admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ShowProducersListCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(ShowProducersListCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to print all producers.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ProducersRerouting.redirectAfterShowProducers(request, response);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
