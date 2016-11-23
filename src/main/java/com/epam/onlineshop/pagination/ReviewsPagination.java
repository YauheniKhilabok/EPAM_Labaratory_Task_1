package com.epam.onlineshop.pagination;

import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.constants.WebConstants;
import com.epam.onlineshop.entity.reviews.Comment;
import com.epam.onlineshop.services.ReviewsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The ReviewsPagination class responsible
 * for pagination information from reviews table.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ReviewsPagination extends AbstractPagination {
    /**
     * @param request setting the required data on the JSP and redirect to the correct page
     */
    @Override
    public void makePagination(HttpServletRequest request) {
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter(ParameterConstants.PAGE_PARAMETER) != null)
            page = Integer.parseInt(request.getParameter(ParameterConstants.PAGE_PARAMETER));
        List<Comment> reviews = ReviewsService.printReviews((page - 1) * recordsPerPage, recordsPerPage);
        int numberRecords = ReviewsService.getNumberRecords();
        int numberPages = (int) Math.ceil(numberRecords * 1.0 / recordsPerPage);
        int numberOfReplies = ReviewsService.getNumberOfReplies();
        request.setAttribute(WebConstants.NUMBER_OF_REPLIES, numberOfReplies);
        request.setAttribute(WebConstants.REVIEWS_LIST, reviews);
        request.setAttribute(WebConstants.NUMBER_PAGES, numberPages);
        request.setAttribute(WebConstants.CURRENT_PAGE, page);
    }
}
