package com.epam.onlineshop.command.reviews;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.rerouting.ReviewsRerouting;
import com.epam.onlineshop.services.ReviewsService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The DeleteCommentCommand class responsible
 * for delete comments on websites.
 * This command is available to the user and admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class DeleteCommentCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(DeleteCommentCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to delete comment on website.
     * reviewId parameter need to to determine which comments will be removed.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String reviewId = request.getParameter(ParameterConstants.ID_COMMENT_PARAMETER);
        ReviewsService.deleteComment(reviewId);
        try {
            ReviewsRerouting.redirectAfterWorkWithComment(request, response);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
