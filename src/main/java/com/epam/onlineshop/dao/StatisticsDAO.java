package com.epam.onlineshop.dao;

import com.epam.onlineshop.connection.ConnectionPool;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.constants.SymbolConstants;
import com.epam.onlineshop.constants.DefaultValueConstants;
import com.epam.onlineshop.constants.ExceptionConstants;
import com.epam.onlineshop.entity.statistics.Statistics;
import com.epam.onlineshop.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The StatisticsDAO class responsible for base and
 * necessary methods to the table orders in the database.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class StatisticsDAO {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(StatisticsDAO.class);
    private static final String SQL_SELECT_ALL_LIST;
    private static final String SQL_GET_DATA_BY_STATUS;
    private static final String SQL_INSERT_NEW_PERIOD;
    private static final String SQL_CHANGE_PERIOD_STATUS;
    private static final String SQL_UPDATE_PERIOD_DATA;
    /**
     * Property - instance need to implement the singleton.
     */
    private static StatisticsDAO instance = null;
    /**
     * Property - lock responsible for thread-safe.
     *
     * @see ReentrantLock
     */
    private static ReentrantLock lock = new ReentrantLock();

    static {
        SQL_SELECT_ALL_LIST = "SELECT id_period, beginning_report_period, end_report_period, amount_purchased_goods, " +
                "amount_sold_goods, consumption, income, profit, status FROM statistics";
    }

    static {
        SQL_GET_DATA_BY_STATUS = "SELECT amount_sold_goods,consumption, income FROM statistics WHERE status = ?";
    }

    static {
        SQL_INSERT_NEW_PERIOD = "INSERT INTO statistics (beginning_report_period, end_report_period, " +
                "amount_purchased_goods, consumption) VALUES (?, ?, ?, ?)";
    }

    static {
        SQL_CHANGE_PERIOD_STATUS = "UPDATE statistics SET status = ?";
    }

    static {
        SQL_UPDATE_PERIOD_DATA = "UPDATE statistics SET amount_sold_goods = ?, income = ?, profit = ? " +
                "WHERE status = ?";
    }

    /**
     * Creates a new object of StatisticsDAO
     */
    private StatisticsDAO() {
    }

    /**
     * This method is used to get class field instance
     * by which is called the class methods.
     *
     * @return instance - object of StatisticsDAO.
     * @see ReentrantLock#lock()
     * @see ReentrantLock#unlock()
     */
    public static StatisticsDAO getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new StatisticsDAO();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    /**
     * @param entity This is the an object that contains
     *               a set of parameters that will be added to the table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public void insert(Statistics entity) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_PERIOD);
            preparedStatement.setString(1, entity.getBeginningReportPeriod());
            preparedStatement.setString(2, entity.getEndReportPeriod());
            preparedStatement.setDouble(3, entity.getAmountPurchasedGoods());
            preparedStatement.setDouble(4, entity.getConsumption());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.INSERT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * @return list od statistics object.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public List<Statistics> printStatistics() throws DaoException {
        Statement statement = null;
        Connection connection = null;
        List<Statistics> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_LIST);
            while (resultSet.next()) {
                Statistics statistics = new Statistics();
                statistics.setPeriodId(resultSet.getInt(ParameterConstants.ID_STATISTICS_PARAMETER));
                statistics.setBeginningReportPeriod(resultSet.getString(ParameterConstants.BEGINNING_REPORT_PERIOD));
                statistics.setEndReportPeriod(resultSet.getString(ParameterConstants.END_REPORT_PERIOD));
                statistics.setAmountPurchasedGoods(resultSet.getDouble(ParameterConstants.AMOUNT_PURCHASED_GOODS));
                statistics.setAmountSoldGoods(resultSet.getDouble(ParameterConstants.AMOUNT_SOLD_GOODS));
                statistics.setConsumption(resultSet.getDouble(ParameterConstants.CONSUMPTION));
                statistics.setIncome(resultSet.getDouble(ParameterConstants.INCOME));
                statistics.setProfit(resultSet.getDouble(ParameterConstants.PROFIT));
                statistics.setStatus(resultSet.getString(ParameterConstants.STATUS));
                list.add(statistics);
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return list;
    }

    /**
     * @param status new status of period
     * @throws DaoException exception which occurs when an error in sql.
     */
    public void changePreviousPeriodStatus(String status) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_CHANGE_PERIOD_STATUS);
            preparedStatement.setString(1, status);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.UPDATE_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * @param status key that will be searched.
     * @return map with data.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public Map<String, String> getDataByStatus(String status) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        Map<String, String> data;
        try {
            data = new HashMap<>();
            preparedStatement = connection.prepareStatement(SQL_GET_DATA_BY_STATUS);
            preparedStatement.setString(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                data.put(ParameterConstants.AMOUNT_SOLD_GOODS, Double.toString(resultSet.getDouble(ParameterConstants.AMOUNT_SOLD_GOODS)));
                data.put(ParameterConstants.CONSUMPTION, Double.toString(resultSet.getDouble(ParameterConstants.CONSUMPTION)));
                data.put(ParameterConstants.INCOME, Double.toString(resultSet.getDouble(ParameterConstants.INCOME)));
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return data;
    }

    /**
     * @param amountSoldGoods new amount sold of goods.
     * @param income          new income.
     * @param profit          new profit.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public void updatePeriodData(double amountSoldGoods, double income, double profit) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_PERIOD_DATA);
            preparedStatement.setDouble(1, amountSoldGoods);
            preparedStatement.setDouble(2, income);
            preparedStatement.setDouble(3, profit);
            preparedStatement.setString(4, DefaultValueConstants.CURRENT_STATISTICS_STATUS);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.UPDATE_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * Method close object PreparedStatement and ResultSet
     *
     * @param ps object of PreparedStatement which will be closed
     */
    public void closePreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                log.error(e);
            }
        }
    }

    /**
     * Method close object Statement and ResultSet
     *
     * @param st object of PreparedStatement which will be closed
     */
    public void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                log.error(e);
            }
        }
    }
}
