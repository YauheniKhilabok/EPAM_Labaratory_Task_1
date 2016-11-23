package com.epam.onlineshop.pagination;

import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.constants.WebConstants;
import com.epam.onlineshop.entity.users.User;
import com.epam.onlineshop.services.UsersService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * The SortingUsersPagination class responsible
 * for pagination information from users table.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class SortingUsersPagination extends AbstractPagination {
    /**
     * Property - type of sorting. Can be ask and desc.
     */
    private static String sortingType;

    /**
     * @param request setting the required data on the JSP and redirect to the correct page
     */
    @Override
    public void makePagination(HttpServletRequest request) {
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter(ParameterConstants.PAGE_PARAMETER) != null)
            page = Integer.parseInt(request.getParameter(ParameterConstants.PAGE_PARAMETER));
        String sortingValue = request.getParameter(WebConstants.SORTING_ATTRIBUTE);
        List<User> users = new ArrayList<>();
        if (sortingValue != null) {
            sortingType = sortingValue;
            users = UsersService.sortUsers((page - 1) * recordsPerPage, recordsPerPage, sortingValue);
        }
        if (sortingValue == null) {
            users = UsersService.sortUsers((page - 1) * recordsPerPage, recordsPerPage, sortingType);
        }
        UsersService.printUsers((page - 1) * recordsPerPage, recordsPerPage);
        int numberRecords = UsersService.getNumberRecords();
        int numberPages = (int) Math.ceil(numberRecords * 1.0 / recordsPerPage);
        request.setAttribute(WebConstants.USERS_LIST, users);
        request.setAttribute(WebConstants.NUMBER_PAGES, numberPages);
        request.setAttribute(WebConstants.CURRENT_PAGE, page);
    }
}
