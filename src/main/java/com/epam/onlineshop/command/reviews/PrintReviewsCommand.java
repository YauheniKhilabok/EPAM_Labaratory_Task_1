package com.epam.onlineshop.command.reviews;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.rerouting.ReviewsRerouting;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The PrintReviewsCommand class responsible
 * for show comments on websites.
 * This command is available to the user and admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class PrintReviewsCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(PrintReviewsCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to show comments on website.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ReviewsRerouting.redirectAfterPrintComments(request, response);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
