package com.epam.onlineshop.command.types;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.rerouting.TypesRerouting;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The AddTypeCommand class responsible for
 * adding a new type of sports nutrition.
 * This command is available to the admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class AddTypeCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(AddTypeCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to add new type of sport nutrition.
     * Parameters type and typeDescription obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String type = request.getParameter(ParameterConstants.TYPE_PRODUCT_PARAMETER);
        String typeDescription = request.getParameter(ParameterConstants.DESCRIPTION_TYPE_PARAMETER);
        try {
            TypesRerouting.redirectAfterAddType(request, response, type, typeDescription);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
