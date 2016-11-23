package com.epam.onlineshop.servlets;

import com.epam.onlineshop.command.CommandManager;
import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.constants.WebConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The ControlServlet class acting as a controller
 * whose task is to perform certain operations on
 * the server at the customer's request.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
@WebServlet(WebConstants.CONTROL_SERVLET)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class ControlServlet extends HttpServlet {
    /**
     * Property - a variable that holds the value of the previous command.
     * It is necessary for pagination information.
     */
    private String paginationValue;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    /**
     * Method makes the call command responsible for performing certain actions
     *
     * @param request  request from a client to perform certain actions
     * @param response response to the client
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String commandValue = request.getParameter(WebConstants.COMMAND_VALUE);
        CommandManager commandManager = new CommandManager();
        ICommand command;
        if (commandValue != null) {
            setPaginationValue(commandValue);
            command = commandManager.getCommand(commandValue);
        } else {
            command = commandManager.getCommand(getPaginationValue());
        }
        command.execute(request, response);
    }

    /**
     * Method to get the value field {@link ControlServlet#paginationValue}
     *
     * @return Return pagination value
     */
    public String getPaginationValue() {
        return paginationValue;
    }

    /**
     * @param paginationValue new pagination value
     */
    public void setPaginationValue(String paginationValue) {
        this.paginationValue = paginationValue;
    }
}
