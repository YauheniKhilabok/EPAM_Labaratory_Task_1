package com.epam.onlineshop.dao;

import com.epam.onlineshop.entity.Entity;
import com.epam.onlineshop.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * The AbstractDAO class announces a number of methods
 * that must be defined in all child classes.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public abstract class AbstractDAO<T extends Entity> {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(AbstractDAO.class);

    /**
     * This method is used to insert new data into table.
     *
     * @param entity This is the an object that contains
     *               a set of parameters that will be added to the table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public abstract void insert(T entity) throws DaoException;

    /**
     * This method is used to delete record from table.
     *
     * @param key This value that will be removed from the table entry.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public abstract void delete(int key) throws DaoException;

    /**
     * This method is used to update record in table.
     *
     * @param key    is a value that will be updated in the record table.
     * @param entity an object that contains a set of new values,
     *               which will replace the old values in the table.
     * @return It returns the flag, which helps to establish the success or failure of the operation.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public abstract boolean change(int key, T entity) throws DaoException;

    /**
     * This method is used to get all records from the table.
     *
     * @return returns a list of objects that contain all records from a table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public abstract List<T> getAll() throws DaoException;

    /**
     * This method is used to get part of records from the table.
     *
     * @param offset        index from which will accrue to the values from the table.
     * @param numberRecords the number of records that will be reflected.
     * @return returns a list of objects that contain records which satisfy the condition from a table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public abstract List<T> getPartForPagination(int offset, int numberRecords) throws DaoException;

    /**
     * This method is used to find necessary information and get this data from a table.
     *
     * @param key value that will be searched.
     * @return returns a list of objects that contain
     * sought records from a table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public abstract List<T> search(String key) throws DaoException;

    /**
     * This method is used to sort records in the table.
     *
     * @param offset        index from which will accrue to the values from the table.
     * @param numberRecords the number of records that will be reflected.
     * @param sortingType   sorting type, which can be either ascending or descending order
     * @return returns a list of objects that was sorted and contain records from a table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public abstract List<T> sort(int offset, int numberRecords, String sortingType) throws DaoException;

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
