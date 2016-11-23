package com.epam.onlineshop.services;

import com.epam.onlineshop.constants.LogicFlagConstants;
import com.epam.onlineshop.dao.ProductDAO;
import com.epam.onlineshop.dao.TypeDAO;
import com.epam.onlineshop.entity.types.Type;
import com.epam.onlineshop.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * The TypesService class responsible for performing operations on data in the types table.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class TypesService {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(TypesService.class);

    /**
     * @param offset        the index in the table, from which the output begins
     * @param numberRecords the number of records that will be reflected
     * @return list with types
     */
    public static List<Type> printTypesForAdmin(int offset, int numberRecords) {
        List<Type> types = null;
        try {
            types = TypeDAO.getInstance().getPartForPagination(offset, numberRecords);
        } catch (DaoException e) {
            log.error(e);
        }
        return types;
    }

    /**
     * @return list with all types
     */
    public static List<Type> getAllTypes() {
        List<Type> types = null;
        try {
            types = TypeDAO.getInstance().getAll();
        } catch (DaoException e) {
            log.error(e);
        }
        return types;
    }

    /**
     * @param type            new type of sport nutrition
     * @param typeDescription type description
     * @return true if operation finished successfully and false otherwise
     */
    public static boolean addType(String type, String typeDescription) {
        Type typeObj = new Type();
        typeObj.setType(type);
        typeObj.setTypeDescription(typeDescription);
        boolean flag = false;
        try {
            flag = TypeDAO.getInstance().isTypeExist(type);
            if (!flag) {
                TypeDAO.getInstance().insert(typeObj);
            }
        } catch (DaoException e) {
            log.error(e);
        }
        return flag;
    }

    /**
     * @param typeId id of type which will be deleted
     * @return true if operation finished successfully and false otherwise
     */
    public static boolean deleteType(int typeId) {
        boolean flag = false;
        try {
            flag = ProductDAO.getInstance().isThereTypesByTypeId(typeId);
            if (!flag) {
                TypeDAO.getInstance().delete(typeId);
            }
        } catch (DaoException e) {
            log.error(e);
        }
        return flag;
    }

    /**
     * @param typeId          id of type which will be updated
     * @param type            new type
     * @param typeDescription new type description
     * @return flag. Flag can be success and error
     */
    public static int changeType(int typeId, String type, String typeDescription) {
        boolean flag;
        Type typeObj = new Type();
        typeObj.setType(type);
        typeObj.setTypeDescription(typeDescription);
        try {
            flag = TypeDAO.getInstance().isTypeIdExist(typeId);
            if (!flag) {
                return LogicFlagConstants.FIRST_ERROR_FLAG;
            }
            flag = ProductDAO.getInstance().isThereTypesByTypeId(typeId);
            if (flag) {
                return LogicFlagConstants.SECOND_ERROR_FLAG;
            }
            TypeDAO.getInstance().change(typeId, typeObj);
        } catch (DaoException e) {
            log.error(e);
        }
        return LogicFlagConstants.SUCCESS_FLAG;
    }

    /**
     * @return max id of type
     */
    public static int getMaxId() {
        int maxId = 0;
        try {
            maxId = TypeDAO.getInstance().getMaxId();
        } catch (DaoException e) {
            log.error(e);
        }
        return maxId;
    }

    /**
     * @return number of records in types table
     */
    public static int getNumberRecords() {
        return TypeDAO.getInstance().getNumberRecords();
    }
}
