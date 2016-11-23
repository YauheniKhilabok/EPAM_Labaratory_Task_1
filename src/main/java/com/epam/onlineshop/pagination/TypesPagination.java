package com.epam.onlineshop.pagination;

import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.constants.WebConstants;
import com.epam.onlineshop.entity.types.Type;
import com.epam.onlineshop.services.TypesService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * The TypesPagination class responsible
 * for pagination information from types table.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class TypesPagination extends AbstractPagination {
    /**
     *
     * @param request setting the required data on the JSP and redirect to the correct page
     */
    @Override
    public void makePagination(HttpServletRequest request) {
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter(ParameterConstants.PAGE_PARAMETER) != null)
            page = Integer.parseInt(request.getParameter(ParameterConstants.PAGE_PARAMETER));
        List<Type> types = TypesService.printTypesForAdmin((page - 1) * recordsPerPage, recordsPerPage);
        int numberRecords = TypesService.getNumberRecords();
        int numberPages = (int) Math.ceil(numberRecords * 1.0 / recordsPerPage);
        request.setAttribute(WebConstants.TYPES_LIST, types);
        request.setAttribute(WebConstants.NUMBER_PAGES, numberPages);
        request.setAttribute(WebConstants.CURRENT_PAGE, page);
    }
}
