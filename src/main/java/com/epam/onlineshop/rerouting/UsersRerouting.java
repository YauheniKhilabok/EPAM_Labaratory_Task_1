package com.epam.onlineshop.rerouting;

import com.epam.onlineshop.command.users.LoadUserInformationCommand;
import com.epam.onlineshop.command.users.ShowUsersListCommand;
import com.epam.onlineshop.constants.*;
import com.epam.onlineshop.entity.users.User;
import com.epam.onlineshop.pagination.SortingUsersPagination;
import com.epam.onlineshop.pagination.UsersPagination;
import com.epam.onlineshop.services.UsersService;
import com.epam.onlineshop.servlets.ControlServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The UsersRerouting class responsible for redirection
 * after operations associated with users.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class UsersRerouting {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(UsersRerouting.class);

    /**
     * Redirect after load user data in change form for user
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterLoadUserData(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute(WebConstants.USER_ID_ATTRIBUTE);
        User user = UsersService.loadUserInformation(userId);
        request.setAttribute(WebConstants.USER_DATA, user);
        request.getRequestDispatcher(PathConstants.CHANGE_PERSONAL_INFORMATION_PAGE).forward(request, response);
    }

    /**
     * Redirect after change user data for user
     *
     * @param request        parameter to the required to obtain data from the JSP,
     *                       to redirect data to the JSP and session management
     * @param response       parameter necessary for response to the client
     * @param userParameters map with new parameters of user
     * @param flag           PS responsible operation on success and displays error otherwise
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterChangeUserData(HttpServletRequest request, HttpServletResponse response,
                                                   Map<String, String> userParameters, int flag)
            throws ServletException, IOException {
        switch (flag) {
            case LogicFlagConstants.SUCCESS_FLAG: {
                HttpSession session = request.getSession(false);
                String userId = (String) session.getAttribute(WebConstants.USER_ID_ATTRIBUTE);
                UsersService.changeUserData(userId, userParameters);
                LoadUserInformationCommand command = new LoadUserInformationCommand();
                command.execute(request, response);
                break;
            }
            case LogicFlagConstants.FIRST_ERROR_FLAG: {
                log.info(MessageConstants.LOG_ERROR_PARAMETER_CHANGE_DATA_USER_MESSAGE);
                request.setAttribute(WebConstants.ERROR_CHANGE_USER_DATA_ATTRIBUTE, MessageConstants.ERROR_PARAMETER_CHANGE_DATA_USER_MESSAGE);
                LoadUserInformationCommand command = new LoadUserInformationCommand();
                command.execute(request, response);
                break;
            }
            default: {
                log.info(MessageConstants.LOG_INPUT_ERROR);
            }
        }
    }

    /**
     * Redirect after show list of users for admin
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterShowUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsersPagination pagination = new UsersPagination();
        pagination.makePagination(request);
        request.getRequestDispatcher(PathConstants.USER_MANAGEMENT_PAGE).forward(request, response);
    }

    /**
     * Redirect after sort users for admin
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterSortUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SortingUsersPagination pagination = new SortingUsersPagination();
        pagination.makePagination(request);
        request.getRequestDispatcher(PathConstants.USER_MANAGEMENT_PAGE).forward(request, response);
    }

    /**
     * Redirect after search users for admin
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @param userName username who is sought
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterSearchUsers(HttpServletRequest request, HttpServletResponse response, String userName)
            throws ServletException, IOException {
        List<User> users = UsersService.searchUsers(userName);
        if (users.size() == 0) {
            request.setAttribute(WebConstants.WARNING_SEARCH_USERS_ATTRIBUTE, MessageConstants.WARNING_SEARCH_USERS_MESSAGE);
        } else {
            request.setAttribute(WebConstants.USERS_LIST, users);
        }
        request.getRequestDispatcher(PathConstants.USER_MANAGEMENT_PAGE).forward(request, response);
    }

    /**
     * Redirect after delete user for admin
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @param userId   id of user who will be deleted
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterDeleteUser(HttpServletRequest request, HttpServletResponse response,
                                               int userId) throws ServletException, IOException {
        boolean flag = UsersService.deleteUser(userId);
        if (flag) {
            log.info(MessageConstants.LOG_ERROR_DELETE_USER_MESSAGE);
            request.setAttribute(WebConstants.ERROR_DELETE_TYPE_ATTRIBUTE, MessageConstants.ERROR_DELETE_TYPE_MESSAGE);
        }
        ControlServlet obj = new ControlServlet();
        obj.setPaginationValue(CommandConstants.SHOW_USERS_LIST_COMMAND);
        ShowUsersListCommand command = new ShowUsersListCommand();
        command.execute(request, response);
    }

    /**
     * Redirect after change user role for admin
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @param userId   id of user who role will be changed
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterChangeUserRole(HttpServletRequest request, HttpServletResponse response,
                                                   int userId) throws ServletException, IOException {
        UsersService.changeUserRole(userId);
        ControlServlet obj = new ControlServlet();
        obj.setPaginationValue(CommandConstants.SHOW_USERS_LIST_COMMAND);
        ShowUsersListCommand command = new ShowUsersListCommand();
        command.execute(request, response);
    }

    /**
     * Redirect after change user status for admin
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @param userId   id of user who status will be changed
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterChangeUserStatus(HttpServletRequest request, HttpServletResponse response,
                                                     int userId) throws ServletException, IOException {
        UsersService.changeUserStatus(userId);
        ControlServlet obj = new ControlServlet();
        obj.setPaginationValue(CommandConstants.SHOW_USERS_LIST_COMMAND);
        ShowUsersListCommand command = new ShowUsersListCommand();
        command.execute(request, response);
    }

    /**
     * Redirect after add new avatar for user
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterAddAvatar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        addFileToDir(request);
        LoadUserInformationCommand command = new LoadUserInformationCommand();
        command.execute(request, response);
    }

    /**
     * Add image to directory
     *
     * @param request parameter to the required to obtain data from the JSP,
     *                to redirect data to the JSP and session management
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    private static void addFileToDir(HttpServletRequest request) throws ServletException, IOException {
        File fileSaveDir = new File(PathConstants.AVATARS_DIR);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        String fileName = null;
        for (Part part : request.getParts()) {
            fileName = extractFileName(part);
            part.write(PathConstants.AVATARS_DIR + File.separator + fileName);
            break;
        }
        HttpSession session = request.getSession(false);
        int userId = Integer.parseInt((String) session.getAttribute(WebConstants.USER_ID_ATTRIBUTE));
        UsersService.changeAvatarForUser(fileName, userId);
    }

    /**
     * Get file name
     *
     * @param part object which help to get file name
     * @return file name which add to directory
     */
    private static String extractFileName(Part part) {
        String contentDisposition = part.getHeader(WebConstants.CONTENT_DISPOSITION);
        String[] items = contentDisposition.split(SymbolConstants.SEMICOLON);
        for (String s : items) {
            if (s.trim().startsWith(WebConstants.FILENAME)) {
                return s.substring(s.indexOf(SymbolConstants.EQUALLY) + 2, s.length() - 1);
            }
        }
        return DefaultValueConstants.DEFAULT_EMPTY_STRING;
    }

}
