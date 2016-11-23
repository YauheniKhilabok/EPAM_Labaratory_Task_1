package com.epam.onlineshop.services;

import com.epam.onlineshop.constants.DefaultValueConstants;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.dao.OrderProductDAO;
import com.epam.onlineshop.dao.StatisticsDAO;
import com.epam.onlineshop.entity.statistics.AdminChart;
import com.epam.onlineshop.entity.statistics.Statistics;
import com.epam.onlineshop.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * The StatisticsService class responsible for performing operations on data in the statistics table.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class StatisticsService {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(StatisticsService.class);

    /**
     * @return list with statistics data for admin
     */
    public static List<AdminChart> getDataForAdminChart() {
        List<AdminChart> data = null;
        try {
            data = OrderProductDAO.getInstance().getDataForAdminChart();
        } catch (DaoException e) {
            log.error(e);
        }
        return data;
    }

    /**
     * @return list with statistics
     */
    public static List<Statistics> printStatistics() {
        List<Statistics> list = null;
        try {
            list = StatisticsDAO.getInstance().printStatistics();
        } catch (DaoException e) {
            log.error(e);
        }
        return list;
    }

    /**
     * @param statisticParameters map with new statistics parameters
     */
    public static void addNewPeriod(Map<String, String> statisticParameters) {
        String beginningPeriod = statisticParameters.get(ParameterConstants.BEGINNING_REPORT_PERIOD);
        String endPeriod = statisticParameters.get(ParameterConstants.END_REPORT_PERIOD);
        int amountPurchasedGoods = Integer.parseInt(statisticParameters.get(ParameterConstants.AMOUNT_PURCHASED_GOODS));
        double consumption = Double.parseDouble(statisticParameters.get(ParameterConstants.CONSUMPTION));
        Statistics statistics = new Statistics();
        statistics.setBeginningReportPeriod(beginningPeriod);
        statistics.setEndReportPeriod(endPeriod);
        statistics.setAmountPurchasedGoods(amountPurchasedGoods);
        statistics.setConsumption(consumption);
        try {
            StatisticsDAO.getInstance().changePreviousPeriodStatus(DefaultValueConstants.DEFAULT_STATISTICS_STATUS);
            StatisticsDAO.getInstance().insert(statistics);
        } catch (DaoException e) {
            log.error(e);
        }
    }
}
