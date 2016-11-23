package com.epam.onlineshop.command.statistics;

import com.epam.onlineshop.command.ICommand;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.rerouting.StatisticsRerouting;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The AddPeriodCommand class responsible for
 * adding a new period of statistics.
 * This command is available to the admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class AddPeriodCommand implements ICommand {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(AddPeriodCommand.class);

    /**
     * Transfers control further down the hierarchy to the command to add new period of statistics.
     * Parameters obtained from jsp by request.
     *
     * @see ICommand#execute(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String beginningPeriod = request.getParameter(ParameterConstants.BEGINNING_REPORT_PERIOD);
        String endPeriod = request.getParameter(ParameterConstants.END_REPORT_PERIOD);
        String amountPurchasedGoods = request.getParameter(ParameterConstants.AMOUNT_PURCHASED_GOODS);
        String consumption = request.getParameter(ParameterConstants.CONSUMPTION);
        Map<String, String> statisticParameters = new HashMap<>();
        statisticParameters.put(ParameterConstants.BEGINNING_REPORT_PERIOD, beginningPeriod);
        statisticParameters.put(ParameterConstants.END_REPORT_PERIOD, endPeriod);
        statisticParameters.put(ParameterConstants.AMOUNT_PURCHASED_GOODS, amountPurchasedGoods);
        statisticParameters.put(ParameterConstants.CONSUMPTION, consumption);
        Map<String, String> statisticParametersCopy = Collections.synchronizedMap(statisticParameters);
        try {
            StatisticsRerouting.redirectAfterAddPeriod(request, response, statisticParametersCopy);
        } catch (ServletException | IOException e) {
            log.error(e);
        }
        return null;
    }
}
