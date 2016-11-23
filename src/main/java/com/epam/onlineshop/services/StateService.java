package com.epam.onlineshop.services;

import com.epam.onlineshop.dao.OrderDAO;
import com.epam.onlineshop.exceptions.DaoException;
import org.apache.log4j.Logger;

/**
 * The StateService class responsible for performing operations with order state.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class StateService {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(StateService.class);

    /**
     * @param orderId   id of order which status will be changed
     * @param newStatus new status for order
     */
    public static void changeOrderStatus(int orderId, String newStatus) {
        try {
            OrderDAO.getInstance().changeOrderStatus(orderId, newStatus);
        } catch (DaoException e) {
            log.error(e);
        }
    }
}
