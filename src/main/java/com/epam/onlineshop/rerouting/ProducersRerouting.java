package com.epam.onlineshop.rerouting;

import com.epam.onlineshop.command.producers.ShowProducersListCommand;
import com.epam.onlineshop.constants.*;
import com.epam.onlineshop.pagination.ProducersPagination;
import com.epam.onlineshop.services.ProducersService;
import com.epam.onlineshop.servlets.ControlServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The ProducersRerouting class responsible for redirection after operations associated with producers.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ProducersRerouting {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(ProducersRerouting.class);

    /**
     * Redirect after show producers for admin
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterShowProducers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProducersPagination pagination = new ProducersPagination();
        pagination.makePagination(request);
        int maxId = ProducersService.getMaxId();
        request.setAttribute(WebConstants.MAX_PRODUCER_ID, maxId);
        request.getRequestDispatcher(PathConstants.SUPPLIER_MANAGEMENT_PAGE).forward(request, response);
    }

    /**
     * Redirect after add new producer for admin
     *
     * @param request          parameter to the required to obtain data from the JSP,
     *                         to redirect data to the JSP and session management
     * @param response         parameter necessary for response to the client
     * @param productionRegion production region of new producer
     * @param brand            brand of new producer
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterAddProducer(HttpServletRequest request, HttpServletResponse response,
                                                String productionRegion, String brand)
            throws ServletException, IOException {
        boolean flag = ProducersService.addProducer(productionRegion, brand);
        if (flag) {
            log.info(MessageConstants.LOG_ERROR_ADD_PRODUCER_MESSAGE);
            request.setAttribute(WebConstants.ERROR_ADD_PRODUCER_ATTRIBUTE, MessageConstants.ERROR_ADD_PRODUCER_MESSAGE);
        }
        ControlServlet obj = new ControlServlet();
        obj.setPaginationValue(CommandConstants.SHOW_PRODUCERS_LIST_COMMAND);
        ShowProducersListCommand command = new ShowProducersListCommand();
        command.execute(request, response);
    }

    /**
     * Redirect after delete producer for admin
     *
     * @param request    parameter to the required to obtain data from the JSP,
     *                   to redirect data to the JSP and session management
     * @param response   parameter necessary for response to the client
     * @param producerId id of producer who will be delete
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterDeleteProducer(HttpServletRequest request, HttpServletResponse response,
                                                   int producerId) throws ServletException, IOException {
        boolean flag = ProducersService.deleteProducer(producerId);
        if (flag) {
            log.info(MessageConstants.LOG_ERROR_DELETE_PRODUCER_MESSAGE);
            request.setAttribute(WebConstants.ERROR_PROCESSING_PRODUCER_ATTRIBUTE, MessageConstants.ERROR_PROCESSING_PRODUCER_MESSAGE);
        }
        ControlServlet obj = new ControlServlet();
        obj.setPaginationValue(CommandConstants.SHOW_PRODUCERS_LIST_COMMAND);
        ShowProducersListCommand command = new ShowProducersListCommand();
        command.execute(request, response);
    }

    /**
     * Redirect after change producer data for admin
     *
     * @param request          parameter to the required to obtain data from the JSP,
     *                         to redirect data to the JSP and session management
     * @param response         parameter necessary for response to the client
     * @param producerId       id of producer who will be update
     * @param productionRegion new production region
     * @param brand            new brand
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterChangeProducer(HttpServletRequest request, HttpServletResponse response,
                                                   int producerId, String productionRegion, String brand)
            throws ServletException, IOException {
        int flag = ProducersService.changeProducer(producerId, productionRegion, brand);
        if (flag == LogicFlagConstants.FIRST_ERROR_FLAG) {
            log.info(MessageConstants.LOG_ERROR_CHANGE_PRODUCER_MESSAGE);
            request.setAttribute(WebConstants.ERROR_PROCESSING_PRODUCER_ATTRIBUTE, MessageConstants.ERROR_CHANGE_PRODUCER_MESSAGE);
        } else if (flag == LogicFlagConstants.SECOND_ERROR_FLAG) {
            log.info(MessageConstants.LOG_ERROR_CHANGE_PRODUCER_MESSAGE);
            request.setAttribute(WebConstants.ERROR_PROCESSING_PRODUCER_ATTRIBUTE, MessageConstants.ERROR_PROCESSING_PRODUCER_MESSAGE);
        }
        ControlServlet obj = new ControlServlet();
        obj.setPaginationValue(CommandConstants.SHOW_PRODUCERS_LIST_COMMAND);
        ShowProducersListCommand command = new ShowProducersListCommand();
        command.execute(request, response);
    }
}
