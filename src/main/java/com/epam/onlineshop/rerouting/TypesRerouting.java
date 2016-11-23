package com.epam.onlineshop.rerouting;

import com.epam.onlineshop.command.types.ShowTypesListCommand;
import com.epam.onlineshop.constants.*;
import com.epam.onlineshop.pagination.TypesPagination;
import com.epam.onlineshop.services.TypesService;
import com.epam.onlineshop.servlets.ControlServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The TypesRerouting class responsible for redirection
 * after operations associated with types of sport nutrition.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class TypesRerouting {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(TypesRerouting.class);

    /**
     * Redirect after show table with types of sport nutrition for admin
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterShowTypes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TypesPagination pagination = new TypesPagination();
        pagination.makePagination(request);
        int maxId = TypesService.getMaxId();
        request.setAttribute(WebConstants.MAX_TYPE_ID, maxId);
        request.getRequestDispatcher(PathConstants.TYPE_MANAGEMENT_PAGE).forward(request, response);
    }

    /**
     * Redirect after add type for admin
     *
     * @param request         parameter to the required to obtain data from the JSP,
     *                        to redirect data to the JSP and session management
     * @param response        parameter necessary for response to the client
     * @param type            new type of sport nutrition
     * @param typeDescription new type description
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterAddType(HttpServletRequest request, HttpServletResponse response,
                                            String type, String typeDescription)
            throws ServletException, IOException {
        boolean flag = TypesService.addType(type, typeDescription);
        if (flag) {
            log.info(MessageConstants.LOG_ERROR_ADD_TYPE_MESSAGE);
            request.setAttribute(WebConstants.ERROR_ADD_TYPE_ATTRIBUTE, MessageConstants.ERROR_ADD_TYPE_MESSAGE);
        }
        ControlServlet obj = new ControlServlet();
        obj.setPaginationValue(CommandConstants.SHOW_TYPES_LIST_COMMAND);
        ShowTypesListCommand command = new ShowTypesListCommand();
        command.execute(request, response);
    }

    /**
     * Redirect after delete type of sport nutrition
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @param typeId   id of type which will be deleted
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterDeleteType(HttpServletRequest request, HttpServletResponse response,
                                               int typeId) throws ServletException, IOException {
        boolean flag = TypesService.deleteType(typeId);
        if (flag) {
            log.info(MessageConstants.LOG_ERROR_DELETE_TYPE_MESSAGE);
            request.setAttribute(WebConstants.ERROR_PROCESSING_TYPE_ATTRIBUTE, MessageConstants.ERROR_PROCESSING_TYPE_MESSAGE);
        }
        ControlServlet obj = new ControlServlet();
        obj.setPaginationValue(CommandConstants.SHOW_TYPES_LIST_COMMAND);
        ShowTypesListCommand command = new ShowTypesListCommand();
        command.execute(request, response);
    }

    /**
     * Redirect after change type for admin
     *
     * @param request         parameter to the required to obtain data from the JSP,
     *                        to redirect data to the JSP and session management
     * @param response        parameter necessary for response to the client
     * @param typeId          id of type which will be updated
     * @param type            new type
     * @param typeDescription new type description
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterChangeType(HttpServletRequest request, HttpServletResponse response,
                                               int typeId, String type, String typeDescription)
            throws ServletException, IOException {
        int flag = TypesService.changeType(typeId, type, typeDescription);
        if (flag == LogicFlagConstants.FIRST_ERROR_FLAG) {
            log.info(MessageConstants.LOG_ERROR_CHANGE_TYPE_MESSAGE);
            request.setAttribute(WebConstants.ERROR_PROCESSING_TYPE_ATTRIBUTE, MessageConstants.ERROR_CHANGE_TYPE_MESSAGE);
        } else if (flag == LogicFlagConstants.SECOND_ERROR_FLAG) {
            log.info(MessageConstants.LOG_ERROR_CHANGE_TYPE_MESSAGE);
            request.setAttribute(WebConstants.ERROR_PROCESSING_TYPE_ATTRIBUTE, MessageConstants.ERROR_PROCESSING_TYPE_MESSAGE);
        }
        ControlServlet obj = new ControlServlet();
        obj.setPaginationValue(CommandConstants.SHOW_TYPES_LIST_COMMAND);
        ShowTypesListCommand command = new ShowTypesListCommand();
        command.execute(request, response);
    }
}
