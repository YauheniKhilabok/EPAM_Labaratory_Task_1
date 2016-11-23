package com.epam.onlineshop.dao;

import com.epam.onlineshop.connection.ConnectionPool;
import com.epam.onlineshop.constants.ExceptionConstants;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.constants.SymbolConstants;
import com.epam.onlineshop.constants.WebConstants;
import com.epam.onlineshop.entity.ObjectFactory;
import com.epam.onlineshop.entity.types.Type;
import com.epam.onlineshop.exceptions.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The TypeDAO class responsible for base and
 * necessary methods to the table orders in the database.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class TypeDAO extends AbstractDAO<Type> {
    /**
     * Property - numberRecords, attribute that contains
     * the number of records that meet a specific request.
     */
    private int numberRecords;
    private static final String SQL_SELECT_ALL_LIST;
    private static final String SQL_SELECT_PART_OF_TYPES;
    private static final String SQL_INSERT_NEW_TYPE;
    private static final String SQL_DELETE_TYPE;
    private static final String SQL_CHANGE_TYPE;
    private static final String SQL_SELECT_TYPE;
    private static final String SQL_GET_MAX_ID;
    private static final String SQL_GET_TYPE_ID;
    private static final String SQL_SELECT_FOUND_ROWS;
    /**
     * Property - instance need to implement the singleton.
     */
    private static TypeDAO instance = null;
    /**
     * Property - lock responsible for thread-safe.
     *
     * @see ReentrantLock
     */
    private static ReentrantLock lock = new ReentrantLock();

    static {
        SQL_SELECT_ALL_LIST = "SELECT id_type, type, type_description FROM types";
    }

    static {
        SQL_SELECT_PART_OF_TYPES = "SELECT SQL_CALC_FOUND_ROWS id_type, type, type_description " +
                "FROM types LIMIT ?, ?";
    }

    static {
        SQL_SELECT_TYPE = "SELECT id_type FROM types WHERE type = ?";
    }

    static {
        SQL_INSERT_NEW_TYPE = "INSERT INTO types (type, type_description) VALUES (?, ?)";
    }

    static {
        SQL_DELETE_TYPE = "DELETE FROM types WHERE id_type = ?";
    }

    static {
        SQL_CHANGE_TYPE = "UPDATE types SET type = ?, type_description = ? WHERE id_type = ?";
    }

    static {
        SQL_GET_MAX_ID = "SELECT MAX(id_type) AS maxTypeId FROM types";
    }

    static {
        SQL_GET_TYPE_ID = "SELECT id_type FROM types WHERE type = ?";
    }

    static {
        SQL_SELECT_FOUND_ROWS = "SELECT FOUND_ROWS()";
    }

    /**
     * Creates a new object of TypeDAO.
     */
    private TypeDAO() {
    }

    /**
     * This method is used to get class field instance
     * by which is called the class methods.
     *
     * @return instance - object of TypeDAO.
     * @see ReentrantLock#lock()
     * @see ReentrantLock#unlock()
     */
    public static TypeDAO getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new TypeDAO();
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
    public void insert(Type entity) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_TYPE);
            preparedStatement.setString(1, entity.getType());
            preparedStatement.setString(2, entity.getTypeDescription());
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_TYPE);
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
    public boolean change(int key, Type entity) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_CHANGE_TYPE);
            preparedStatement.setString(1, entity.getType());
            preparedStatement.setString(2, entity.getTypeDescription());
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
    public List<Type> getAll() throws DaoException {
        Statement statement = null;
        Connection connection = null;
        List<Type> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_LIST);
            while (resultSet.next()) {
                Type typeObj = new Type();
                int typeId = resultSet.getInt(ParameterConstants.ID_TYPE_PARAMETER);
                String type = resultSet.getString(ParameterConstants.TYPE_PRODUCT_PARAMETER);
                String typeDescription = resultSet.getString(ParameterConstants.DESCRIPTION_TYPE_PARAMETER);
                typeObj.setId(typeId);
                typeObj.setType(type);
                typeObj.setTypeDescription(typeDescription);
                list.add(typeObj);
            }
            ObjectFactory factory = new ObjectFactory();
            com.epam.onlineshop.entity.types.Types types = factory.createTypes();
            types.setTypes(list);
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
    public List<Type> getPartForPagination(int offset, int numberRecords) throws DaoException {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        Connection connection = null;
        List<Type> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(SQL_SELECT_PART_OF_TYPES);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, numberRecords);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Type type = new Type();
                type.setId(resultSet.getInt(ParameterConstants.ID_TYPE_PARAMETER));
                type.setType(resultSet.getString(ParameterConstants.TYPE_PRODUCT_PARAMETER));
                type.setTypeDescription(resultSet.getString(ParameterConstants.DESCRIPTION_TYPE_PARAMETER));
                list.add(type);
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
     * @return max id in the table types.
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
                maxId = resultSet.getInt(WebConstants.MAX_TYPE_ID);
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
     * @param type key that will be searched.
     * @return true if in the table there are type
     * and false of otherwise.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public boolean isTypeExist(String type) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean flag = false;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_TYPE);
            preparedStatement.setString(1, type);
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
     * @param typeId key that will be searched.
     * @return true if in the table there are typeId
     * and false of otherwise.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public boolean isTypeIdExist(int typeId) throws DaoException {
        Statement statement = null;
        Connection connection = null;
        boolean flag = false;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_LIST);
            while (resultSet.next()) {
                if (typeId == resultSet.getInt(ParameterConstants.ID_TYPE_PARAMETER)) {
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
     * @param type key that will be searched.
     * @return typeId.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public int getTypeIdByType(String type) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        int typeId = 0;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_TYPE_ID);
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                typeId = resultSet.getInt(ParameterConstants.ID_TYPE_PARAMETER);
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return typeId;
    }

    /**
     * This method is unsupported in this class.
     *
     * @throws UnsupportedOperationException Thrown to indicate that the requested operation is not supported.
     */
    @Override
    public List<Type> search(String key) throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * This method is unsupported in this class.
     *
     * @throws UnsupportedOperationException Thrown to indicate that the requested operation is not supported.
     */
    @Override
    public List<Type> sort(int offset, int numberRecords, String sortingType) throws DaoException {
        throw new UnsupportedOperationException();
    }

    /**
     * @return number of records from table which satisfy the condition.
     */
    public int getNumberRecords() {
        return numberRecords;
    }
}
