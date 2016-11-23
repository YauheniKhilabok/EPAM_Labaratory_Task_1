package com.epam.onlineshop.rerouting;

import com.epam.onlineshop.command.reviews.PrintReviewsCommand;
import com.epam.onlineshop.constants.CommandConstants;
import com.epam.onlineshop.constants.MessageConstants;
import com.epam.onlineshop.constants.PathConstants;
import com.epam.onlineshop.constants.WebConstants;
import com.epam.onlineshop.pagination.ReviewsPagination;
import com.epam.onlineshop.servlets.ControlServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The ReviewsRerouting class responsible for redirection after operations associated with reviews.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ReviewsRerouting {
    /**
     * Redirect after perform operations on the withdrawal of the sports nutrition for admin and user
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterWorkWithComment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute(WebConstants.SUCCESS_COMMENT_ATTRIBUTE, MessageConstants.SUCCESS_COMMENT_MESSAGE);
        ControlServlet obj = new ControlServlet();
        obj.setPaginationValue(CommandConstants.PRINT_REVIEWS_COMMAND);
        PrintReviewsCommand command = new PrintReviewsCommand();
        command.execute(request, response);
    }

    /**
     * Redirect after print comments for guest, user and admin
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterPrintComments(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReviewsPagination pagination = new ReviewsPagination();
        pagination.makePagination(request);
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute(WebConstants.USER_ID_ATTRIBUTE);
        request.setAttribute(WebConstants.USER_ID_ATTRIBUTE, userId);
        request.getRequestDispatcher(PathConstants.REVIEWS_PAGE).forward(request, response);
    }
}
