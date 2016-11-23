package com.epam.onlineshop.pagination;

import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.constants.WebConstants;
import com.epam.onlineshop.entity.producers.Producer;
import com.epam.onlineshop.entity.products.Product;
import com.epam.onlineshop.entity.types.Type;
import com.epam.onlineshop.services.ProducersService;
import com.epam.onlineshop.services.ProductsService;
import com.epam.onlineshop.services.TypesService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The ProductsPagination class responsible
 * for pagination information from products table.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ProductsPagination extends AbstractPagination {
    /**
     * @param request setting the required data on the JSP and redirect to the correct page
     */
    @Override
    public void makePagination(HttpServletRequest request) {
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter(ParameterConstants.PAGE_PARAMETER) != null)
            page = Integer.parseInt(request.getParameter(ParameterConstants.PAGE_PARAMETER));
        List<Product> products = ProductsService.printProductsForAdmin((page - 1) * recordsPerPage, recordsPerPage);
        List<Type> types = TypesService.getAllTypes();
        List<Producer> producers = ProducersService.getAllProducers();
        int numberRecords = ProductsService.getNumberRecords();
        int numberPages = (int) Math.ceil(numberRecords * 1.0 / recordsPerPage);
        request.setAttribute(WebConstants.PRODUCTS_LIST, products);
        request.setAttribute(WebConstants.TYPES_LIST, types);
        request.setAttribute(WebConstants.PRODUCERS_LIST, producers);
        request.setAttribute(WebConstants.NUMBER_PAGES, numberPages);
        request.setAttribute(WebConstants.CURRENT_PAGE, page);
    }
}
