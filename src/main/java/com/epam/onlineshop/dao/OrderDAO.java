package com.epam.onlineshop.dao;

import com.epam.onlineshop.connection.ConnectionPool;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.constants.SymbolConstants;
import com.epam.onlineshop.constants.ExceptionConstants;
import com.epam.onlineshop.constants.DefaultValueConstants;
import com.epam.onlineshop.entity.orders.Order;
import com.epam.onlineshop.exceptions.DaoException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The OrderDAO class responsible for base and
 * necessary methods to the table orders in the database.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class OrderDAO extends AbstractDAO<Order> {
    private static final String SQL_INSERT_ORDER;
    private static final String SQL_GET_ORDER_ID;
    private static final String SQL_SELECT_ORDERS_LIST_FOR_USER;
    private static final String SQL_SELECT_STATUS_BY_ID;
    private static final String SQL_GET_DATA_BY_ID;
    private static final String SQL_DELETE_ORDER;
    private static final String SQL_CHANGE_ORDER;
    private static final String SQL_CHANGE_ORDER_STATUS;
    private static final String SQL_GET_ALL_ORDERS_BY_USER_ID;
    /**
     * Property - instance need to implement the singleton.
     */
    private static OrderDAO instance = null;
    /**
     * Property - lock responsible for thread-safe.
     *
     * @see ReentrantLock
     */
    private static ReentrantLock lock = new ReentrantLock();

    static {
        SQL_INSERT_ORDER = "INSERT INTO orders (id_user, delivery_condition, type_of_payment, total_cost)" +
                " VALUES(?, ?, ?, ?)";
    }

    static {
        SQL_GET_ORDER_ID = "SELECT id_order FROM orders WHERE id_user = ? AND total_cost = ?";
    }

    static {
        SQL_SELECT_STATUS_BY_ID = "SELECT status FROM orders WHERE id_order = ?";
    }

    static {
        SQL_SELECT_ORDERS_LIST_FOR_USER = "SELECT id_order, lead_time, delivery_condition, status, " +
                "type_of_payment, total_cost FROM orders WHERE id_user = ?";
    }

    static {
        SQL_GET_DATA_BY_ID = "SELECT `orders`.`total_cost`, SUM(`order_product`.`number_of_packages`) AS numberOfPackages " +
                "FROM `orders` INNER JOIN `order_product` ON `orders`.`id_order` = `order_product`.`id_order` " +
                "WHERE `orders`.`id_order` = ?";
    }

    static {
        SQL_DELETE_ORDER = "DELETE FROM orders WHERE id_order = ?";
    }

    static {
        SQL_CHANGE_ORDER = "UPDATE orders SET delivery_condition = ?, type_of_payment = ?" +
                " WHERE id_user = ? AND id_order = ?";
    }

    static {
        SQL_CHANGE_ORDER_STATUS = "UPDATE orders SET status = ? WHERE id_order = ?";
    }

    static {
        SQL_GET_ALL_ORDERS_BY_USER_ID = "SELECT COUNT(id_order) AS numberOfOrders FROM orders WHERE id_user = ?";
    }

    /**
     * Creates a new object of OrderDAO.
     */
    private OrderDAO() {
    }

    /**
     * This method is used to get class field instance
     * by which is called the class methods.
     *
     * @return instance - object of OrderDAO.
     * @see ReentrantLock#lock()
     * @see ReentrantLock#unlock()
     */
    public static OrderDAO getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new OrderDAO();
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
    @Override
    public void insert(Order entity) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_ORDER);
            preparedStatement.setInt(1, entity.getUser().getId());
            preparedStatement.setString(2, entity.getDeliveryCondition());
            preparedStatement.setString(3, entity.getTypeOfPayment());
            preparedStatement.setDouble(4, entity.getTotalCost());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.INSERT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * @param userId    first part of composite key with which the operation will be performed.
     * @param totalCost second part of composite key with which the operation will be performed.
     * @return orderId
     * @throws DaoException exception which occurs when an error in sql.
     */
    public int getOrderId(int userId, double totalCost) throws DaoException {
        double newTotalCost = new BigDecimal(totalCost).setScale(2, RoundingMode.HALF_UP).doubleValue();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int orderId = 0;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_ORDER_ID);
            preparedStatement.setInt(1, userId);
            preparedStatement.setDouble(2, newTotalCost);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderId = resultSet.getInt(ParameterConstants.ID_ORDER_PARAMETER);
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return orderId;
    }

    /**
     * @param userId key that will be chosen by the necessary data.
     * @return list of data, which will be presented to the user.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public List<Order> getOrdersListForUser(int userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        List<Order> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ORDERS_LIST_FOR_USER);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt(ParameterConstants.ID_ORDER_PARAMETER));
                order.setLeadTime(truncate(resultSet.getString(ParameterConstants.LEAD_TIME_ORDER_PARAMETER)));
                order.setDeliveryCondition(resultSet.getString(ParameterConstants.DELIVERY_CONDITION_PARAMETER));
                order.setStatus(resultSet.getString(ParameterConstants.STATUS_ORDER_PARAMETER));
                order.setTypeOfPayment(resultSet.getString(ParameterConstants.TYPE_OF_PAYMENT_PARAMETER));
                order.setTotalCost(resultSet.getDouble(ParameterConstants.TOTAL_COST_PARAMETER));
                list.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return list;
    }

    /**
     * @param sendingTime while sending the message.
     * @return the converted time.
     */
    private String truncate(String sendingTime) {
        return sendingTime.substring(DefaultValueConstants.START_INDEX, DefaultValueConstants.END_INDEX);
    }

    /**
     * @param key This value that will be removed from the table entry.
     * @throws DaoException exception which occurs when an error in sql.
     */
    @Override
    public void delete(int key) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_ORDER);
            preparedStatement.setInt(1, key);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.DELETE_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * @param key    is a value that will be updated in the record table.
     * @param entity an object that contains a set of new values,
     *               which will replace the old values in the table.
     * @return It returns the flag, which helps to establish the success or failure of the operation.
     * @throws DaoException exception which occurs when an error in sql.
     */
    @Override
    public boolean change(int key, Order entity) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int orderId = entity.getId();
        boolean flag = true;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHANGE_ORDER);
            preparedStatement.setString(1, entity.getDeliveryCondition());
            preparedStatement.setString(2, entity.getTypeOfPayment());
            preparedStatement.setInt(3, key);
            preparedStatement.setInt(4, orderId);
            if (preparedStatement.executeUpdate() == 0) {
                flag = false;
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.UPDATE_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return flag;
    }

    /**
     * This method is unsupported in this class.
     *
     * @throws UnsupportedOperationException Thrown to indicate that the requested operation is not supported.
     */
    @Override
    public List<Order> getAll() {
        throw new UnsupportedOperationException();
    }

    /**
     * This method is unsupported in this class.
     *
     * @throws UnsupportedOperationException Thrown to indicate that the requested operation is not supported.
     */
    @Override
    public List<Order> getPartForPagination(int offset, int numberRecords) {
        throw new UnsupportedOperationException();
    }

    /**
     * This method is unsupported in this class.
     *
     * @throws UnsupportedOperationException Thrown to indicate that the requested operation is not supported.
     */
    @Override
    public List<Order> search(String key) {
        throw new UnsupportedOperationException();
    }

    /**
     * This method is unsupported in this class.
     *
     * @throws UnsupportedOperationException Thrown to indicate that the requested operation is not supported.
     */
    @Override
    public List<Order> sort(int offset, int numberRecords, String sortingType) {
        return null;
    }

    /**
     * @param userId key under which data will be obtained.
     * @return true if in the table there are orders of the user and false of otherwise.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public boolean isThereOrdersByUserId(int userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean flag = false;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_ALL_ORDERS_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(ParameterConstants.NUMBER_OF_ORDERS_PARAMETER) > 0) {
                    flag = true;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return flag;
    }

    /**
     * @param orderId key that will be updated status.
     * @param status  new status which will updated in table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public void changeOrderStatus(int orderId, String status) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_CHANGE_ORDER_STATUS);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.UPDATE_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * @param orderId key that is received status
     * @return status of order.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public String getStatusById(int orderId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String status = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_STATUS_BY_ID);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                status = resultSet.getString(ParameterConstants.STATUS_ORDER_PARAMETER);
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return status;
    }

    /**
     * @param orderId key that is received status.
     * @return map which included number of packages and total cost of product.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public Map<String, String> getDataById(int orderId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        Map<String, String> data = new HashMap<>();
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_DATA_BY_ID);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                data.put(ParameterConstants.NUMBER_OF_PACKAGES_PRODUCT_PARAMETER, Double.toString(resultSet.getDouble(ParameterConstants.NUMBERS_PACKAGES_PARAMETER)));
                data.put(ParameterConstants.TOTAL_COST_PARAMETER, Double.toString(resultSet.getDouble(ParameterConstants.TOTAL_COST_PARAMETER)));
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return data;
    }
}
