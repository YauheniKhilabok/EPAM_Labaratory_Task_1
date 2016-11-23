package com.epam.onlineshop.dao;

import com.epam.onlineshop.connection.ConnectionPool;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.constants.SymbolConstants;
import com.epam.onlineshop.constants.ExceptionConstants;
import com.epam.onlineshop.constants.DefaultValueConstants;
import com.epam.onlineshop.entity.reviews.Comment;
import com.epam.onlineshop.entity.ObjectFactory;
import com.epam.onlineshop.entity.reviews.Reviews;
import com.epam.onlineshop.exceptions.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The CommentDAO class responsible for base and
 * necessary methods to the table comments in the database.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class CommentDAO extends AbstractDAO<Comment> {
    /**
     * Property - numberRecords, attribute that contains
     * the number of records that meet a specific request.
     */
    private int numberRecords;
    private static final String SQL_INSERT_NEW_COMMENT;
    private static final String SQL_DELETE_COMMENT;
    private static final String SQL_CHANGE_COMMENT;
    private static final String SQL_SELECT_ALL_LIST;
    private static final String SQL_SELECT_PART_OF_LIST;
    private static final String SQL_SELECT_FOUND_ROWS;
    /**
     * Property - instance need to implement the singleton.
     */
    private static CommentDAO instance = null;
    /**
     * Property - lock responsible for thread-safe.
     *
     * @see ReentrantLock
     */
    private static ReentrantLock lock = new ReentrantLock();

    static {
        SQL_INSERT_NEW_COMMENT = "INSERT INTO reviews (id_user, message) VALUES(?, ?)";
    }

    static {
        SQL_DELETE_COMMENT = "DELETE FROM reviews WHERE id_review = ?";
    }

    static {
        SQL_CHANGE_COMMENT = "UPDATE reviews SET message = ? WHERE id_review = ?";
    }

    static {
        SQL_SELECT_ALL_LIST = "SELECT `reviews`.`id_review`,`reviews`.`id_user`, `users`.`name_user`, `users`.`avatar`, `reviews`.`message`, `reviews`.`sending_time`" +
                "FROM `reviews` INNER JOIN `users` ON `reviews`.`id_user` = `users`.`id_user`";
    }

    static {
        SQL_SELECT_PART_OF_LIST = "SELECT SQL_CALC_FOUND_ROWS `reviews`.`id_review`,`reviews`.`id_user`, `users`.`name_user`, `users`.`avatar`, `reviews`.`message`, `reviews`.`sending_time`" +
                "FROM `reviews` INNER JOIN `users` ON `reviews`.`id_user` = `users`.`id_user` ORDER BY `reviews`.`id_review`  LIMIT ?, ?";
    }

    static {
        SQL_SELECT_FOUND_ROWS = "SELECT FOUND_ROWS()";
    }

    /**
     * Creates a new object of CommentDAO.
     */
    private CommentDAO() {
    }

    /**
     * This method is used to get class field instance
     * by which is called the class methods.
     *
     * @return instance - object of CommentDAO.
     * @see ReentrantLock#lock()
     * @see ReentrantLock#unlock()
     */
    public static CommentDAO getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new CommentDAO();
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
    public void insert(Comment entity) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_COMMENT);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setString(2, entity.getMessage());
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
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            boolean flag = true;
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_COMMENT);
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
    public boolean change(int key, Comment entity) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHANGE_COMMENT);
            preparedStatement.setString(1, entity.getMessage());
            preparedStatement.setInt(2, key);
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
    public List<Comment> getAll() throws DaoException {
        Statement statement = null;
        Connection connection = null;
        List<Comment> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_LIST);
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt(ParameterConstants.ID_COMMENT_PARAMETER));
                comment.setUserId(resultSet.getInt(ParameterConstants.ID_USER_PARAMETER));
                comment.setUserName(resultSet.getString(ParameterConstants.NAME_USER_PARAMETER));
                comment.setUserAvatar(resultSet.getString(ParameterConstants.AVATAR_USER_PARAMETER));
                comment.setMessage(resultSet.getString(ParameterConstants.MESSAGE_REVIEWS_PARAMETER));
                comment.setSendingTime(resultSet.getString(ParameterConstants.SENDING_TIME_REVIEWS_PARAMETER));
                list.add(comment);
            }
            ObjectFactory factory = new ObjectFactory();
            Reviews reviews = factory.createReviews();
            reviews.setReviews(list);
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.DELETE_DAO_EXCEPTION);
        } finally {
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return list;
    }

    /**
     * @param offset        index from which will accrue to the values from the table.
     * @param numberRecords the number of records that will be reflected.
     * @return returns a list of objects that contain sought records from a table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    @Override
    public List<Comment> getPartForPagination(int offset, int numberRecords) throws DaoException {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        Connection connection = null;
        List<Comment> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(SQL_SELECT_PART_OF_LIST);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, numberRecords);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt(ParameterConstants.ID_COMMENT_PARAMETER));
                comment.setUserId(resultSet.getInt(ParameterConstants.ID_USER_PARAMETER));
                comment.setUserName(resultSet.getString(ParameterConstants.NAME_USER_PARAMETER));
                comment.setUserAvatar(resultSet.getString(ParameterConstants.AVATAR_USER_PARAMETER));
                comment.setMessage(resultSet.getString(ParameterConstants.MESSAGE_REVIEWS_PARAMETER));
                comment.setSendingTime(truncate(resultSet.getString(ParameterConstants.SENDING_TIME_REVIEWS_PARAMETER)));
                list.add(comment);
            }
            resultSet.close();
            resultSet = statement.executeQuery(SQL_SELECT_FOUND_ROWS);
            if (resultSet.next()) {
                this.numberRecords = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return list;
    }

    /**
     * This method is unsupported in this class.
     *
     * @throws UnsupportedOperationException Thrown to indicate that the requested operation is not supported.
     */
    @Override
    public List<Comment> search(String key) {
        throw new UnsupportedOperationException();
    }

    /**
     * This method is unsupported in this class.
     *
     * @throws UnsupportedOperationException Thrown to indicate that the requested operation is not supported.
     */
    @Override
    public List<Comment> sort(int offset, int numberRecords, String sortingType) {
        throw new UnsupportedOperationException();
    }

    /**
     * @param sendingTime while sending the message.
     * @return the converted time.
     */
    private String truncate(String sendingTime) {
        return sendingTime.substring(DefaultValueConstants.START_INDEX, DefaultValueConstants.END_INDEX);
    }

    /**
     * @return number of records from table which satisfy the condition.
     */
    public int getNumberRecords() {
        return numberRecords;
    }
}
