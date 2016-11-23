package com.epam.onlineshop.connection;

import com.epam.onlineshop.constants.WebConstants;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The ConnectionPool class need to establish a connection to the database
 * and to return the connection back to the pool.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ConnectionPool {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(ConnectionPool.class);
    /**
     * Property - instance need to implement the singleton.
     */
    private static ConnectionPool instance = null;
    /**
     * Property - lock responsible for thread-safe.
     *
     * @see ReentrantLock
     */
    private static ReentrantLock lock = new ReentrantLock();
    /**
     * @see Context
     */
    private static Context envCtx = null;
    /**
     * @see DataSource
     */
    private static DataSource dataSource = null;

    /**
     * Creates a new object of ConnectionPool,
     * initializes the class field as envCtx and dataSource.
     */
    private ConnectionPool() {
        try {
            if (envCtx == null) {
                envCtx = (Context) (new InitialContext().lookup(WebConstants.CONTEXT));
            }
            if (dataSource == null) {
                dataSource = (DataSource) envCtx.lookup(WebConstants.DATABASE_NAME);
            }
        } catch (NamingException e) {
            log.error(e);
        }
    }

    /**
     * This method is used to get class field instance
     * by which is called the class methods.
     *
     * @return instance - object of ConnectionPool.
     * @see ReentrantLock#lock()
     * @see ReentrantLock#unlock()
     */
    public static ConnectionPool getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new ConnectionPool();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    /**
     * This method is used to get object of Connection,
     * which necessary to establish connection with database.
     * Connection class object is taken from the connection pool.
     *
     * @return connection - object of Connection.
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            log.error(e);
        }
        return connection;
    }

    /**
     * This method is used to return connection to connection pool.
     *
     * @param connection new connection to database
     */
    public void returnConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
