package com.epam.onlineshop.dao;

import com.epam.onlineshop.connection.ConnectionPool;
import com.epam.onlineshop.constants.ExceptionConstants;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.constants.SymbolConstants;
import com.epam.onlineshop.constants.WebConstants;
import com.epam.onlineshop.entity.ObjectFactory;
import com.epam.onlineshop.entity.producers.Producer;
import com.epam.onlineshop.entity.producers.Producers;
import com.epam.onlineshop.exceptions.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The ProducerDAO class responsible for base and
 * necessary methods to the table orders in the database.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ProducerDAO extends AbstractDAO<Producer> {
    /**
     * Property - numberRecords, attribute that contains
     * the number of records that meet a specific request.
     */
    private int numberRecords;
    private static final String SQL_SELECT_ALL_LIST;
    private static final String SQL_SELECT_PART_OF_PRODUCERS;
    private static final String SQL_INSERT_NEW_PRODUCER;
    private static final String SQL_DELETE_PRODUCER;
    private static final String SQL_CHANGE_PRODUCER;
    private static final String SQL_GET_MAX_ID;
    private static final String SQL_SELECT_PRODUCER;
    private static final String SQL_GET_PRODUCER_ID;
    private static final String SQL_SELECT_FOUND_ROWS;
    /**
     * Property - instance need to implement the singleton.
     */
    private static ProducerDAO instance = null;
    /**
     * Property - lock responsible for thread-safe.
     *
     * @see ReentrantLock
     */
    private static ReentrantLock lock = new ReentrantLock();

    static {
        SQL_SELECT_ALL_LIST = "SELECT id_producer, production_region, brand FROM producers";
    }

    static {
        SQL_SELECT_PART_OF_PRODUCERS = "SELECT SQL_CALC_FOUND_ROWS id_producer, production_region, brand " +
                "FROM producers LIMIT ?, ?";
    }

    static {
        SQL_INSERT_NEW_PRODUCER = "INSERT INTO producers (production_region, brand) VALUES (?, ?)";
    }

    static {
        SQL_DELETE_PRODUCER = "DELETE FROM producers WHERE id_producer = ?";
    }

    static {
        SQL_CHANGE_PRODUCER = "UPDATE producers SET production_region = ?, brand = ? WHERE id_producer = ?";
    }

    static {
        SQL_GET_MAX_ID = "SELECT MAX(id_producer) AS maxProducerId FROM producers";
    }

    static {
        SQL_SELECT_PRODUCER = "SELECT id_producer FROM producers WHERE production_region = ? AND brand = ?";
    }

    static {
        SQL_GET_PRODUCER_ID = "SELECT id_producer FROM producers WHERE brand = ?";
    }

    static {
        SQL_SELECT_FOUND_ROWS = "SELECT FOUND_ROWS()";
    }

    /**
     * Creates a new object of ProducerDAO.
     */
    private ProducerDAO() {
    }

    /**
     * This method is used to get class field instance
     * by which is called the class methods.
     *
     * @return instance - object of ProducerDAO.
     * @see ReentrantLock#lock()
     * @see ReentrantLock#unlock()
     */
    public static ProducerDAO getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new ProducerDAO();
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
    public void insert(Producer entity) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_PRODUCER);
            preparedStatement.setString(1, entity.getProductionRegion());
            preparedStatement.setString(2, entity.getBrand());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.INSERT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * @param key This value that will be removed from the table entry.
     * @throws DaoException exception which occurs when an error in sql.
     */
    @Override
    public void delete(int key) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            boolean flag;
            preparedStatement = connection.prepareStatement(SQL_DELETE_PRODUCER);
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
    public boolean change(int key, Producer entity) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_CHANGE_PRODUCER);
            preparedStatement.setString(1, entity.getProductionRegion());
            preparedStatement.setString(2, entity.getBrand());
            preparedStatement.setInt(3, key);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.UPDATE_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return true;
    }

    /**
     * @return returns a list of objects that contain all records from a table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    @Override
    public List<Producer> getAll() throws DaoException {
        Statement statement = null;
        Connection connection = null;
        List<Producer> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_LIST);
            while (resultSet.next()) {
                Producer producer = new Producer();
                int producerId = resultSet.getInt(ParameterConstants.ID_PRODUCER_PARAMETER);
                String productionRegion = resultSet.getString(ParameterConstants.PRODUCTION_REGION_PRODUCT_PARAMETER);
                String brand = resultSet.getString(ParameterConstants.BRAND_PRODUCT_PARAMETER);
                producer.setId(producerId);
                producer.setProductionRegion(productionRegion);
                producer.setBrand(brand);
                list.add(producer);
            }
            ObjectFactory factory = new ObjectFactory();
            Producers producers = factory.createProducers();
            producers.setProducers(list);
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return list;
    }

    /**
     * @param offset        index from which will accrue to the values from the table.
     * @param numberRecords the number of records that will be reflected.
     * @return returns a list of objects that contain records which satisfy the condition from a table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    @Override
    public List<Producer> getPartForPagination(int offset, int numberRecords) throws DaoException {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        Connection connection = null;
        List<Producer> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(SQL_SELECT_PART_OF_PRODUCERS);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, numberRecords);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Producer producer = new Producer();
                producer.setId(resultSet.getInt(ParameterConstants.ID_PRODUCER_PARAMETER));
                producer.setProductionRegion(resultSet.getString(ParameterConstants.PRODUCTION_REGION_PRODUCT_PARAMETER));
                producer.setBrand(resultSet.getString(ParameterConstants.BRAND_PRODUCT_PARAMETER));
                list.add(producer);
            }
            resultSet.close();
            resultSet = statement.executeQuery(SQL_SELECT_FOUND_ROWS);
            if (resultSet.next()) {
                this.numberRecords = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return list;
    }

    /**
     * @return max id in the table producers.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public int getMaxId() throws DaoException {
        Statement statement = null;
        Connection connection = null;
        int maxId = 0;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_MAX_ID);
            while (resultSet.next()) {
                maxId = resultSet.getInt(WebConstants.MAX_PRODUCER_ID);
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return maxId;
    }

    /**
     * @param productionRegion region where produce products.
     * @param brand            label of producer.
     * @return true if in the table there are producers by productionRegion and brand
     * and false of otherwise.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public boolean isProducerExist(String productionRegion, String brand) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        boolean flag = false;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_PRODUCER);
            preparedStatement.setString(1, productionRegion);
            preparedStatement.setString(2, brand);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                flag = true;
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
     * @param producerId key that condition is tested.
     * @return true if in the table there are producerId by condition and false of otherwise.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public boolean isProducerIdExist(int producerId) throws DaoException {
        Statement statement = null;
        Connection connection = null;
        boolean flag = false;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_LIST);
            while (resultSet.next()) {
                if (producerId == resultSet.getInt(ParameterConstants.ID_PRODUCER_PARAMETER)) {
                    flag = true;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return flag;
    }

    /**
     * @param brand key that is searched.
     * @return producerId.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public int getProducerIdByBrand(String brand) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        int producerId = 0;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_PRODUCER_ID);
            preparedStatement.setString(1, brand);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                producerId = resultSet.getInt(ParameterConstants.ID_PRODUCER_PARAMETER);
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return producerId;
    }

    /**
     * This method is unsupported in this class.
     *
     * @throws UnsupportedOperationException Thrown to indicate that the requested operation is not supported.
     */
    @Override
    public List<Producer> search(String key) {
        throw new UnsupportedOperationException();
    }

    /**
     * This method is unsupported in this class.
     *
     * @throws UnsupportedOperationException Thrown to indicate that the requested operation is not supported.
     */
    @Override
    public List<Producer> sort(int offset, int numberRecords, String sortingType) {
        throw new UnsupportedOperationException();
    }

    /**
     * @return number of records from table which satisfy the condition.
     */
    public int getNumberRecords() {
        return numberRecords;
    }
}
