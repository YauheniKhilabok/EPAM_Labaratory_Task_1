package com.epam.onlineshop.rerouting;

import com.epam.onlineshop.command.statistics.ShowAdminStatisticsCommand;
import com.epam.onlineshop.constants.CommandConstants;
import com.epam.onlineshop.constants.PathConstants;
import com.epam.onlineshop.constants.WebConstants;
import com.epam.onlineshop.entity.statistics.AdminChart;
import com.epam.onlineshop.entity.statistics.Statistics;
import com.epam.onlineshop.services.StatisticsService;
import com.epam.onlineshop.servlets.ControlServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The StatisticsRerouting class responsible for redirection after operations associated with statistics.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class StatisticsRerouting {
    /**
     * Redirect after show statistics for admin
     *
     * @param request  parameter to the required to obtain data from the JSP,
     *                 to redirect data to the JSP and session management
     * @param response parameter necessary for response to the client
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterShowStatistics(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<AdminChart> list = StatisticsService.getDataForAdminChart();
        request.setAttribute(WebConstants.ADMIN_CHART, list);
        List<Statistics> statisticsList = StatisticsService.printStatistics();
        request.setAttribute(WebConstants.STATISTICS_LIST, statisticsList);
        request.getRequestDispatcher(PathConstants.ADMIN_STATISTICS_PAGE).forward(request, response);
    }

    /**
     * Redirect after add new statistics period for admin
     *
     * @param request             parameter to the required to obtain data from the JSP,
     *                            to redirect data to the JSP and session management
     * @param response            parameter necessary for response to the client
     * @param statisticParameters map with parameters which will be add
     * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
     * @throws IOException      Signals that an I/O exception of some sort has occurred.
     *                          This class is the general class of exceptions produced by failed or interrupted I/O operations.
     */
    public static void redirectAfterAddPeriod(HttpServletRequest request, HttpServletResponse response, Map<String, String> statisticParameters)
            throws ServletException, IOException {
        StatisticsService.addNewPeriod(statisticParameters);
        ControlServlet obj = new ControlServlet();
        obj.setPaginationValue(CommandConstants.SHOW_ADMIN_STATISTICS_COMMAND);
        ShowAdminStatisticsCommand command = new ShowAdminStatisticsCommand();
        command.execute(request, response);
    }
}
