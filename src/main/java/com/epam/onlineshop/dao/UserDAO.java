package com.epam.onlineshop.dao;

import com.epam.onlineshop.connection.ConnectionPool;
import com.epam.onlineshop.constants.*;
import com.epam.onlineshop.entity.ObjectFactory;
import com.epam.onlineshop.entity.users.User;
import com.epam.onlineshop.entity.users.Users;
import com.epam.onlineshop.exceptions.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The UserDAO class responsible for base and
 * necessary methods to the table orders in the database.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class UserDAO extends AbstractDAO<User> {
    /**
     * Property - numberRecords, attribute that contains
     * the number of records that meet a specific request.
     */
    private int numberRecords;
    private static final String SQL_SELECT_ALL_LIST;
    private static final String SQL_SELECT_PART_OF_USERS;
    private static final String SQL_INSERT_NEW_USER;
    private static final String SQL_DELETE_USER;
    private static final String SQL_SORT_USERS_BY_NAME_UP;
    private static final String SQL_SORT_USERS_BY_NAME_DOWN;
    private static final String SQL_SEARCH_USERS_BY_NAME;
    private static final String SQL_SELECT_EMAIL;
    private static final String SQL_SELECT_LOGIN;
    private static final String SQL_SELECT_FOR_AUTHORIZATION;
    private static final String SQL_SELECT_ID_AND_NAME;
    private static final String SQL_SELECT_USER_DATA_FOR_LOADING;
    private static final String SQL_CHANGE_USER_DATA;
    private static final String SQL_GET_USER_ROLE;
    private static final String SQL_CHANGE_USER_ROLE;
    private static final String SQL_GET_USER_STATUS;
    private static final String SQL_CHANGE_USER_STATUS;
    private static final String SQL_CHANGE_AVATAR;
    private static final String SQL_SELECT_FOUND_ROWS;
    /**
     * Property - instance need to implement the singleton.
     */
    private static UserDAO instance = null;
    /**
     * Property - lock responsible for thread-safe.
     *
     * @see ReentrantLock
     */
    private static ReentrantLock lock = new ReentrantLock();

    static {
        SQL_SELECT_ALL_LIST = "SELECT id_user, name_user, email, phone, address, login, password, role, status, avatar FROM users";
    }

    static {
        SQL_SELECT_PART_OF_USERS = "SELECT SQL_CALC_FOUND_ROWS id_user, name_user, email, " +
                "address, login, role, status FROM users LIMIT ?, ?";
    }

    static {
        SQL_INSERT_NEW_USER = "INSERT INTO users (name_user, email, phone, address, login, " +
                "password, role, status) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    }

    static {
        SQL_CHANGE_AVATAR = "UPDATE users SET avatar = ? WHERE id_user = ?";
    }

    static {
        SQL_DELETE_USER = "DELETE FROM users WHERE id_user = ?";
    }

    static {
        SQL_SORT_USERS_BY_NAME_UP = "SELECT SQL_CALC_FOUND_ROWS id_user, name_user," +
                " email, address, login, role, status FROM users ORDER BY name_user LIMIT ?, ?";
    }

    static {
        SQL_SORT_USERS_BY_NAME_DOWN = "SELECT SQL_CALC_FOUND_ROWS id_user, name_user," +
                " email, address, login, role, status FROM users ORDER BY name_user DESC LIMIT ?, ?";
    }

    static {
        SQL_SEARCH_USERS_BY_NAME = "SELECT id_user, name_user, email, address, login, role, status " +
                "FROM users WHERE name_user LIKE ?";
    }

    static {
        SQL_SELECT_EMAIL = "SELECT email FROM users";
    }

    static {
        SQL_SELECT_LOGIN = "SELECT login FROM users WHERE login = ?";
    }

    static {
        SQL_SELECT_FOR_AUTHORIZATION = "SELECT login, password, role, status FROM users";
    }

    static {
        SQL_SELECT_ID_AND_NAME = "SELECT id_user, name_user FROM users WHERE login = ?";
    }

    static {
        SQL_SELECT_USER_DATA_FOR_LOADING = "SELECT name_user, email, phone, address, password FROM users" +
                " WHERE id_user = ?";
    }

    static {
        SQL_CHANGE_USER_DATA = "UPDATE users SET name_user = ?, email = ?, phone = ?, address = ?, password = ?" +
                " WHERE id_user = ?";
    }

    static {
        SQL_GET_USER_ROLE = "SELECT role FROM users WHERE id_user = ?";
    }

    static {
        SQL_CHANGE_USER_ROLE = "UPDATE users SET role = ? WHERE id_user = ?";
    }

    static {
        SQL_GET_USER_STATUS = "SELECT status FROM users WHERE id_user = ?";
    }

    static {
        SQL_CHANGE_USER_STATUS = "UPDATE users SET status = ? WHERE id_user = ?";
    }

    static {
        SQL_SELECT_FOUND_ROWS = "SELECT FOUND_ROWS()";
    }

    /**
     * Creates a new object of UserDAO.
     */
    private UserDAO() {
    }

    /**
     * This method is used to get class field instance
     * by which is called the class methods.
     *
     * @return instance - object of UserDAO.
     * @see ReentrantLock#lock()
     * @see ReentrantLock#unlock()
     */
    public static UserDAO getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new UserDAO();
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
    public void insert(User entity) throws DaoException {
        PreparedStatement preparedStatement;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_USER);
            preparedStatement.setString(1, entity.getNameUser());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPhone());
            preparedStatement.setString(4, entity.getAddress());
            preparedStatement.setString(5, entity.getLogin());
            preparedStatement.setString(6, entity.getPassword());
            preparedStatement.setString(7, entity.getRole());
            preparedStatement.setString(8, entity.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.INSERT_DAO_EXCEPTION);
        } finally {
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
            preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setInt(1, key);
            preparedStatement.executeUpdate();
            boolean flag;
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
    public boolean change(int key, User entity) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHANGE_USER_DATA);
            preparedStatement.setString(1, entity.getNameUser());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPhone());
            preparedStatement.setString(4, entity.getAddress());
            preparedStatement.setString(5, entity.getPassword());
            preparedStatement.setInt(6, key);
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
    public List<User> getAll() throws DaoException {
        Statement statement = null;
        Connection connection = null;
        List<User> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_LIST);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(ParameterConstants.ID_USER_PARAMETER));
                user.setNameUser(resultSet.getString(ParameterConstants.NAME_USER_PARAMETER));
                user.setEmail(resultSet.getString(ParameterConstants.EMAIL_USER_PARAMETER));
                user.setPhone(resultSet.getString(ParameterConstants.PHONE_USER_PARAMETER));
                user.setLogin(resultSet.getString(ParameterConstants.LOGIN_USER_PARAMETER));
                user.setPassword(resultSet.getString(ParameterConstants.PASSWORD_USER_PARAMETER));
                user.setRole(resultSet.getString(ParameterConstants.ROLE_USER_PARAMETER));
                user.setStatus(resultSet.getString(ParameterConstants.STATUS_USER_PARAMETER));
                user.setAvatar(resultSet.getString(ParameterConstants.AVATAR_USER_PARAMETER));
                list.add(user);
            }
            ObjectFactory factory = new ObjectFactory();
            Users users = factory.createUsers();
            users.setUsers(list);
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
    public List<User> getPartForPagination(int offset, int numberRecords) throws DaoException {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        Connection connection = null;
        List<User> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(SQL_SELECT_PART_OF_USERS);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, numberRecords);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(ParameterConstants.ID_USER_PARAMETER));
                user.setNameUser(resultSet.getString(ParameterConstants.NAME_USER_PARAMETER));
                user.setEmail(resultSet.getString(ParameterConstants.EMAIL_USER_PARAMETER));
                user.setAddress(resultSet.getString(ParameterConstants.ADDRESS_USER_PARAMETER));
                user.setLogin(resultSet.getString(ParameterConstants.LOGIN_USER_PARAMETER));
                user.setRole(resultSet.getString(ParameterConstants.ROLE_USER_PARAMETER));
                user.setStatus(resultSet.getString(ParameterConstants.STATUS_USER_PARAMETER));
                list.add(user);
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
     * @param email key that will be searched.
     * @return true if in the table there are same email
     * and false of otherwise.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public boolean isEmailExists(String email) throws DaoException {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_EMAIL);
            while (resultSet.next()) {
                if (email.equals(resultSet.getString(ParameterConstants.EMAIL_USER_PARAMETER))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return false;
    }

    /**
     * @param login key that will be searched.
     * @return true if in the table there are same login
     * and false of otherwise.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public boolean isLoginExists(String login) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        boolean flag = false;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_LOGIN);
            preparedStatement.setString(1, login);
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
     * @param login    user name for logging.
     * @param password authorization cipher.
     * @return flag.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public int authorization(String login, String password) throws DaoException {
        Statement statement = null;
        int authorizedAsAdminFlag = 1;
        int authorizedAsUserFlag = 2;
        int authorizationBanUserError = 3;
        int authorizationNotFoundUserError = 4;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_FOR_AUTHORIZATION);
            while (resultSet.next()) {
                if (DefaultValueConstants.BAN_USER_STATUS.equals(resultSet.getString(ParameterConstants.STATUS_USER_PARAMETER))) {
                    return authorizationBanUserError;
                }
                if (login.equals(resultSet.getString(ParameterConstants.LOGIN_USER_PARAMETER)) &&
                        password.equals(resultSet.getString(ParameterConstants.PASSWORD_USER_PARAMETER)) &&
                        DefaultValueConstants.DEFAULT_ADMIN_ROLE.equals(resultSet.getString(ParameterConstants.ROLE_USER_PARAMETER))) {
                    return authorizedAsAdminFlag;
                }
                if (login.equals(resultSet.getString(ParameterConstants.LOGIN_USER_PARAMETER)) &&
                        password.equals(resultSet.getString(ParameterConstants.PASSWORD_USER_PARAMETER)) &&
                        DefaultValueConstants.DEFAULT_USER_ROLE.equals(resultSet.getString(ParameterConstants.ROLE_USER_PARAMETER))) {
                    return authorizedAsUserFlag;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return authorizationNotFoundUserError;
    }

    /**
     * @param login key that will be searched.
     * @return string which included userId and userName.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public String getIdAndNameByLogin(String login) throws DaoException {
        String data = DefaultValueConstants.DEFAULT_EMPTY_STRING;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ID_AND_NAME);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                data += resultSet.getString(ParameterConstants.ID_USER_PARAMETER) + SymbolConstants.SPACE;
                data += resultSet.getString(ParameterConstants.NAME_USER_PARAMETER);
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
     * @param userId key that will be searched.
     * @return object of User class.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public User loadUserInformation(String userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        User user = null;
        try {
            user = new User();
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_DATA_FOR_LOADING);
            preparedStatement.setInt(1, Integer.parseInt(userId));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setNameUser(resultSet.getString(ParameterConstants.NAME_USER_PARAMETER));
                user.setEmail(resultSet.getString(ParameterConstants.EMAIL_USER_PARAMETER));
                user.setPhone(resultSet.getString(ParameterConstants.PHONE_USER_PARAMETER));
                user.setAddress(resultSet.getString(ParameterConstants.ADDRESS_USER_PARAMETER));
                user.setPassword(resultSet.getString(ParameterConstants.PASSWORD_USER_PARAMETER));
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return user;
    }

    /**
     * @param key value that will be searched.
     * @return returns a list of objects that contain
     * sought records from a table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    @Override
    public List<User> search(String key) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        List<User> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_SEARCH_USERS_BY_NAME);
            preparedStatement.setString(1, SymbolConstants.PERCENT + key + SymbolConstants.PERCENT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                int userId = resultSet.getInt(ParameterConstants.ID_USER_PARAMETER);
                user.setId(userId);
                String nameUser = resultSet.getString(ParameterConstants.NAME_USER_PARAMETER);
                user.setNameUser(nameUser);
                user.setEmail(resultSet.getString(ParameterConstants.EMAIL_USER_PARAMETER));
                user.setAddress(resultSet.getString(ParameterConstants.ADDRESS_USER_PARAMETER));
                user.setLogin(resultSet.getString(ParameterConstants.LOGIN_USER_PARAMETER));
                user.setRole(resultSet.getString(ParameterConstants.ROLE_USER_PARAMETER));
                user.setStatus(resultSet.getString(ParameterConstants.STATUS_USER_PARAMETER));
                list.add(user);
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
     * @param offset        index from which will accrue to the values from the table.
     * @param numberRecords the number of records that will be reflected.
     * @param sortingType   sorting type, which can be either ascending or descending order
     * @return returns a list of objects that was sorted and contain records from a table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    @Override
    public List<User> sort(int offset, int numberRecords, String sortingType) throws DaoException {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        Connection connection = null;
        List<User> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            if (sortingType.equals(ParameterConstants.UP_SORTING_PARAMETER)) {
                preparedStatement = connection.prepareStatement(SQL_SORT_USERS_BY_NAME_UP);
            } else {
                preparedStatement = connection.prepareStatement(SQL_SORT_USERS_BY_NAME_DOWN);
            }
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, numberRecords);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                int userId = resultSet.getInt(ParameterConstants.ID_USER_PARAMETER);
                user.setId(userId);
                user.setNameUser(resultSet.getString(ParameterConstants.NAME_USER_PARAMETER));
                user.setEmail(resultSet.getString(ParameterConstants.EMAIL_USER_PARAMETER));
                user.setAddress(resultSet.getString(ParameterConstants.ADDRESS_USER_PARAMETER));
                user.setLogin(resultSet.getString(ParameterConstants.LOGIN_USER_PARAMETER));
                user.setRole(resultSet.getString(ParameterConstants.ROLE_USER_PARAMETER));
                user.setStatus(resultSet.getString(ParameterConstants.STATUS_USER_PARAMETER));
                list.add(user);
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
     * @param userId value that will be searched.
     * @return role of user.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public String getUserRole(int userId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        String role = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_USER_ROLE);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role = resultSet.getString(ParameterConstants.ROLE_USER_PARAMETER);
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return role;
    }

    /**
     * @param userId  value that will be searched.
     * @param newRole new role for user.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public void changeUserRole(int userId, String newRole) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHANGE_USER_ROLE);
            preparedStatement.setString(1, newRole);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.UPDATE_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * @param userId value that will be searched.
     * @return status of user.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public String getUserStatus(int userId) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        String status = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_USER_STATUS);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                status = resultSet.getString(ParameterConstants.STATUS_USER_PARAMETER);
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
     * @param userId    value that will be searched.
     * @param newStatus new status for user.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public void changeUserStatus(int userId, String newStatus) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            boolean flag;
            preparedStatement = connection.prepareStatement(SQL_CHANGE_USER_STATUS);
            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.UPDATE_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * @param path   new path to avatar for user.
     * @param userId value that will be searched.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public void changeAvatar(String path, int userId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_CHANGE_AVATAR);
            boolean flag;
            preparedStatement.setString(1, path);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.UPDATE_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * @return number of records from table which satisfy the condition.
     */
    public int getNumberRecords() {
        return numberRecords;
    }
}
