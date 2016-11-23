package com.epam.onlineshop.pagination;

import javax.servlet.http.HttpServletRequest;

/**
 * The AbstractPagination class responsible
 * for pagination information from database tables.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public abstract class AbstractPagination {
    /**
     * Method is responsible for the withdrawal of a certain amount of data on the page,
     * as well as for the formation of the number of pages to display
     *
     * @param request setting the required data on the JSP and redirect to the correct page
     */
    public abstract void makePagination(HttpServletRequest request);
}
