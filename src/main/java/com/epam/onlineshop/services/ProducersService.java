package com.epam.onlineshop.services;

import com.epam.onlineshop.constants.LogicFlagConstants;
import com.epam.onlineshop.dao.ProducerDAO;
import com.epam.onlineshop.dao.ProductDAO;
import com.epam.onlineshop.entity.producers.Producer;
import com.epam.onlineshop.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The ProducersService class responsible for performing operations on data in the producers table.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ProducersService {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(ProducersService.class);

    /**
     * @param offset        the index in the table, from which the output begins
     * @param numberRecords the number of records that will be reflected
     * @return list with producers
     */
    public static List<Producer> printProducersForAdmin(int offset, int numberRecords) {
        List<Producer> producers = null;
        try {
            producers = ProducerDAO.getInstance().getPartForPagination(offset, numberRecords);
        } catch (DaoException e) {
            log.error(e);
        }
        return producers;
    }

    /**
     * @return list with all producers
     */
    public static List<Producer> getAllProducers() {
        List<Producer> producers = null;
        try {
            producers = ProducerDAO.getInstance().getAll();
        } catch (DaoException e) {
            log.error(e);
        }
        return producers;
    }

    /**
     * @param productionRegion production region
     * @param brand            brand
     * @return true if add is success and false otherwise
     */
    public static boolean addProducer(String productionRegion, String brand) {
        Producer producer = new Producer();
        producer.setProductionRegion(productionRegion);
        producer.setBrand(brand);
        boolean flag = false;
        try {
            flag = ProducerDAO.getInstance().isProducerExist(productionRegion, brand);
            if (!flag) {
                ProducerDAO.getInstance().insert(producer);
            }
        } catch (DaoException e) {
            log.error(e);
        }
        return flag;
    }

    /**
     * @param producerId id of producer who will be deleted
     * @return true is operation is success and false otherwise
     */
    public static boolean deleteProducer(int producerId) {
        boolean flag = false;
        try {
            flag = ProductDAO.getInstance().isThereBrandsByProducerId(producerId);
            if (!flag) {
                ProducerDAO.getInstance().delete(producerId);
            }
        } catch (DaoException e) {
            log.error(e);
        }
        return flag;
    }

    /**
     * @param producerId       id of producer who will be updated
     * @param productionRegion new production region
     * @param brand            new brand
     * @return flag. Flag can be success and error.
     */
    public static int changeProducer(int producerId, String productionRegion, String brand) {
        boolean flag;
        Producer producer = new Producer();
        producer.setProductionRegion(productionRegion);
        producer.setBrand(brand);
        try {
            flag = ProducerDAO.getInstance().isProducerIdExist(producerId);
            if (!flag) {
                return LogicFlagConstants.FIRST_ERROR_FLAG;
            }
            flag = ProductDAO.getInstance().isThereBrandsByProducerId(producerId);
            if (flag) {
                return LogicFlagConstants.SECOND_ERROR_FLAG;
            }
            ProducerDAO.getInstance().change(producerId, producer);
        } catch (DaoException e) {
            log.error(e);
        }
        return LogicFlagConstants.SUCCESS_FLAG;
    }

    /**
     * @return max producer id
     */
    public static int getMaxId() {
        int maxId = 0;
        try {
            maxId = ProducerDAO.getInstance().getMaxId();
        } catch (DaoException e) {
            log.error(e);
        }
        return maxId;
    }

    /**
     * @return number of records
     */
    public static int getNumberRecords() {
        return ProducerDAO.getInstance().getNumberRecords();
    }
}
