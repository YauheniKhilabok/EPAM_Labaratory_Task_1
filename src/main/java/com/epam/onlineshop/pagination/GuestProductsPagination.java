package com.epam.onlineshop.pagination;

import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.constants.WebConstants;
import com.epam.onlineshop.entity.products.Product;
import com.epam.onlineshop.services.ProductsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * The GuestProductsPagination class responsible
 * for pagination information from products table.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class GuestProductsPagination extends AbstractPagination {
    /**
     * Property - type of sport nutrition
     */
    private static String type;

    /**
     * @param request setting the required data on the JSP and redirect to the correct page
     */
    @Override
    public void makePagination(HttpServletRequest request) {
        int page = 1;
        int recordsPerPage = 6;
        if (request.getParameter(ParameterConstants.PAGE_PARAMETER) != null)
            page = Integer.parseInt(request.getParameter(ParameterConstants.PAGE_PARAMETER));
        String typeValue = request.getParameter(ParameterConstants.TYPE_PRODUCT_PARAMETER);
        List<Product> products = new ArrayList<>();
        if (typeValue != null) {
            type = typeValue;
            products = ProductsService.printProductsForGuest(typeValue, (page - 1) * recordsPerPage, recordsPerPage);
        }
        if (typeValue == null) {
            products = ProductsService.printProductsForGuest(type, (page - 1) * recordsPerPage, recordsPerPage);
        }
        int numberRecords = ProductsService.getNumberRecords();
        int numberPages = (int) Math.ceil(numberRecords * 1.0 / recordsPerPage);
        request.setAttribute(ParameterConstants.TYPE_PRODUCT_PARAMETER, type);
        request.setAttribute(WebConstants.PRODUCTS_LIST, products);
        request.setAttribute(WebConstants.NUMBER_PAGES, numberPages);
        request.setAttribute(WebConstants.CURRENT_PAGE, page);
    }
}
