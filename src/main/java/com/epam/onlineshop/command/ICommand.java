package com.epam.onlineshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The ICommand interface declares a method that will
 * be implemented by a particular instruction.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public interface ICommand {
    /**
     * This method is used to retrieve data from jsp and perform specific commands.
     *
     * @param request  This is the first parameter to execute method,
     *                 which helps to get values from the jsp, set the attributes,
     *                 get the value of the session and redirect.
     * @param response This is the second parameter to execute method,
     *                 which used to answer and to transmit data jsp.
     * @return String This returns empty string.
     * @see HttpServletRequest
     * @see HttpServletResponse
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
