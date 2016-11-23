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
 * The ChangeTypeCommand class responsible for
 * change type of sports nutrition.
 * This command is available to the admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ChangeTypeCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(ChangeTypeCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to change type of sport nutrition.
     * Parameters typeId, type and typeDescription obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int typeId = Integer.parseInt(request.getParameter(ParameterConstants.ID_TYPE_PARAMETER));
        String type = request.getParameter(ParameterConstants.TYPE_PRODUCT_PARAMETER);
        String typeDescription = request.getParameter(ParameterConstants.DESCRIPTION_TYPE_PARAMETER);
        try {
            TypesRerouting.redirectAfterChangeType(request, response, typeId, type, typeDescription);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
