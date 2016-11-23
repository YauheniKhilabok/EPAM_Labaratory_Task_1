package com.epam.onlineshop.pagination;

import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.constants.WebConstants;
import com.epam.onlineshop.entity.products.Product;
import com.epam.onlineshop.services.ProductsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * The SortingProductsPaginationForUser class responsible
 * for pagination information from products table.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class SortingProductsPaginationForUser extends AbstractPagination {
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
        int recordsPerPage = 12;
        if (request.getParameter(ParameterConstants.PAGE_PARAMETER) != null)
            page = Integer.parseInt(request.getParameter(ParameterConstants.PAGE_PARAMETER));
        String sortingValue = request.getParameter(WebConstants.SORTING_ATTRIBUTE);
        List<Product> products = new ArrayList<>();
        if (sortingValue != null) {
            sortingType = sortingValue;
            products = ProductsService.sortProductsForUser((page - 1) * recordsPerPage, recordsPerPage, sortingValue);
        }
        if (sortingValue == null) {
            products = ProductsService.sortProductsForUser((page - 1) * recordsPerPage, recordsPerPage, sortingType);
        }
        int numberRecords = ProductsService.getNumberRecords();
        int numberPages = (int) Math.ceil(numberRecords * 1.0 / recordsPerPage);
        request.setAttribute(WebConstants.PRODUCTS_LIST, products);
        request.setAttribute(WebConstants.NUMBER_PAGES, numberPages);
        request.setAttribute(WebConstants.CURRENT_PAGE, page);
    }
}
