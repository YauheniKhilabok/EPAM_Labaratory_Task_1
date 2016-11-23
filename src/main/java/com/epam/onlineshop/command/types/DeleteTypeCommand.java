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
 * The DeleteTypeCommand class responsible for
 * delete type of sports nutrition from the database.
 * This command is available to the admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class DeleteTypeCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(DeleteTypeCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to delete type of sport nutrition.
     * Parameters typeId obtained from jsp by request and need to delete record from table.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int typeId = Integer.parseInt(request.getParameter(ParameterConstants.ID_TYPE_PARAMETER));
        try {
            TypesRerouting.redirectAfterDeleteType(request, response, typeId);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
