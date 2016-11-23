package com.epam.onlineshop.command.reviews;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.constants.WebConstants;
import com.epam.onlineshop.rerouting.ReviewsRerouting;
import com.epam.onlineshop.services.ReviewsService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The AddCommentCommand class responsible
 * for adding comments on websites.
 * This command is available to the user.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class AddCommentCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(AddCommentCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to add comments to website.
     * Parameters obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute(WebConstants.USER_ID_ATTRIBUTE);
        String message = request.getParameter(ParameterConstants.MESSAGE_REVIEWS_PARAMETER);
        ReviewsService.addComment(userId, message);
        try {
            ReviewsRerouting.redirectAfterWorkWithComment(request, response);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
