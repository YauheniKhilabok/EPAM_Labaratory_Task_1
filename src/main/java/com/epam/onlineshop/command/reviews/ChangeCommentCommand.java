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
 * The ChangeCommentCommand class responsible
 * for update comments on websites for user.
 * This command is available to the user.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ChangeCommentCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(ChangeCommentCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to change comments on website.
     * Parameters obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String reviewId = request.getParameter(ParameterConstants.ID_COMMENT_PARAMETER);
        String newMessage = request.getParameter(ParameterConstants.NEW_MESSAGE_REVIEWS_PARAMETER);
        ReviewsService.changeComment(reviewId, newMessage);
        try {
            ReviewsRerouting.redirectAfterWorkWithComment(request, response);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
