package com.epam.onlineshop.dao;

import com.epam.onlineshop.connection.ConnectionPool;
import com.epam.onlineshop.constants.ParameterConstants;
import com.epam.onlineshop.constants.SymbolConstants;
import com.epam.onlineshop.constants.DefaultValueConstants;
import com.epam.onlineshop.constants.ExceptionConstants;
import com.epam.onlineshop.entity.ObjectFactory;
import com.epam.onlineshop.entity.ordersproducts.OrderProduct;
import com.epam.onlineshop.entity.products.Product;
import com.epam.onlineshop.entity.products.Products;
import com.epam.onlineshop.entity.statistics.UserChart;
import com.epam.onlineshop.exceptions.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The ProductDAO class responsible for base and
 * necessary methods to the table orders in the database.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ProductDAO extends AbstractDAO<Product> {
    /**
     * Property - numberRecords, attribute that contains
     * the number of records that meet a specific request.
     */
    private int numberRecords;
    private static final String SQL_SELECT_ALL_LIST;
    private static final String SQL_SELECT_PART_OF_LIST_FOR_ADMIN;
    private static final String SQL_SELECT_PART_OF_LIST_FOR_GUEST;
    private static final String SQL_SELECT_PART_OF_LIST_FOR_USER;
    private static final String SQL_SELECT_FULL_DATA_PRODUCT_FOR_USER;
    private static final String SQL_INSERT_NEW_PRODUCT;
    private static final String SQL_DELETE_PRODUCT;
    private static final String SQL_CHANGE_PRODUCT;
    private static final String SQL_SEARCH_PRODUCTS_BY_NAME;
    private static final String SQL_SORT_PRODUCTS_BY_PRICE_UP;
    private static final String SQL_SORT_PRODUCTS_BY_PRICE_DOWN;
    private static final String SQL_SORT_PRODUCTS_BY_SHELF_LIFE_UP;
    private static final String SQL_SORT_PRODUCTS_BY_SHELF_LIFE_DOWN;
    private static final String SQL_SELECT_DATA_FOR_USER_CHART;
    private static final String SQL_SELECT_DATA_FOR_TEMP_ORDER;
    private static final String SQL_SELECT_NUMBER_OF_PACKAGES;
    private static final String SQL_SET_NEW_NUMBER_OF_PACKAGES;
    private static final String SQL_GET_ALL_BRANDS_BY_PRODUCER_ID;
    private static final String SQL_GET_ALL_TYPES_BY_TYPE_ID;
    private static final String SQL_GET_ALL_PRODUCTS_BY_ID;
    private static final String SQL_SET_DISCOUNT;
    private static final String SQL_SET_STATUS;
    private static final String SQL_CHECK_IDENTITY_PRODUCT;
    private static final String SQL_SELECT_FOUND_ROWS;
    /**
     * Property - instance need to implement the singleton.
     */
    private static ProductDAO instance = null;
    /**
     * Property - lock responsible for thread-safe.
     *
     * @see ReentrantLock
     */
    private static ReentrantLock lock = new ReentrantLock();

    static {
        SQL_SELECT_ALL_LIST = "SELECT `producers`.`id_producer`, `producers`.`production_region`, `producers`.`brand`, " +
                "`types`.`id_type`, `types`.`type`, " +
                "`types`.`type_description`, `products`.`id_product`, `products`.`date_of_delivery`, " +
                "`products`.`shelf_life`, `products`.`name_product`, `products`.`number_of_packages`, " +
                "`products`.`unit`, `products`.`number_per_unit`, `products`.`taste`, `products`.`discounts`, " +
                "`products`.`purchase_price`, `products`.`status`, `products`.`product_description`, " +
                "`products`.`path_to_image` FROM `products` " +
                "INNER JOIN `producers` ON `products`.`id_producer` = `producers`.`id_producer` " +
                "INNER JOIN `types` ON `products`.`id_type` = `types`.`id_type`";
    }

    static {
        SQL_SELECT_PART_OF_LIST_FOR_ADMIN = "SELECT SQL_CALC_FOUND_ROWS `products`.`id_product`, `products`.`date_of_delivery`, " +
                "`products`.`shelf_life`, `products`.`name_product`, `types`.`type`, `producers`.`brand`, `products`.`taste`, " +
                "`products`.`number_of_packages`, `products`.`discounts`, `products`.`purchase_price`" +
                "FROM `products` INNER JOIN `producers` ON `products`.`id_producer` = `producers`.`id_producer`" +
                "INNER JOIN `types` ON `products`.`id_type` = `types`.`id_type` ORDER BY `products`.`id_product` LIMIT ?, ?";
    }

    static {
        SQL_SELECT_PART_OF_LIST_FOR_GUEST = "SELECT SQL_CALC_FOUND_ROWS `products`.`name_product`,`products`.`unit`," +
                " `products`.`number_per_unit`, `products`.`path_to_image`, `producers`.`brand`, `products`.`taste`," +
                " `products`.`status`, `products`.`discounts`, `products`.`purchase_price`" +
                "FROM `products` INNER JOIN `producers` ON `products`.`id_producer` = `producers`.`id_producer`" +
                "INNER JOIN `types` ON `products`.`id_type` = `types`.`id_type`" +
                "WHERE `types`.`type` = ? LIMIT ?, ?";
    }

    static {
        SQL_SELECT_PART_OF_LIST_FOR_USER = "SELECT SQL_CALC_FOUND_ROWS `products`.`id_product`, `products`.`name_product`," +
                " `products`.`path_to_image`, `types`.`type` FROM products INNER JOIN `types` ON `products`.`id_type` = `types`.`id_type`" +
                " LIMIT ?, ?";
    }

    static {
        SQL_SELECT_FULL_DATA_PRODUCT_FOR_USER = "SELECT `products`.`id_product`, `products`.`date_of_delivery`, " +
                "`products`.`shelf_life`, `products`.`name_product`, `products`.`number_of_packages`, " +
                "`products`.`unit`, `products`.`number_per_unit`, `products`.`taste`, `products`.`discounts`, " +
                "`products`.`purchase_price`, `products`.`status`, `products`.`product_description`, " +
                "`products`.`path_to_image`, `types`.`type`, `producers`.`production_region`, `producers`.`brand` " +
                "FROM `products` INNER JOIN `producers` ON `products`.`id_producer` = `producers`.`id_producer` " +
                "INNER JOIN `types` ON `products`.`id_type` = `types`.`id_type` WHERE `products`.`id_product` = ?";
    }

    static {
        SQL_INSERT_NEW_PRODUCT = "INSERT INTO products (id_producer, id_type, date_of_delivery, shelf_life, " +
                "name_product, number_of_packages, unit, number_per_unit, taste, discounts, purchase_price, " +
                "product_description, path_to_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    static {
        SQL_DELETE_PRODUCT = "DELETE FROM products WHERE id_product = ?";
    }

    static {
        SQL_CHANGE_PRODUCT = "UPDATE products SET id_producer = ?, id_type = ?, date_of_delivery = ?, " +
                "shelf_life = ?, name_product = ?, number_of_packages = ?, unit = ?, " +
                "number_per_unit = ?, taste = ?, discounts = ?, purchase_price = ?, " +
                "product_description = ?, path_to_image = ? WHERE id_product = ?";
    }

    static {
        SQL_SEARCH_PRODUCTS_BY_NAME = "SELECT `products`.`id_product`, `products`.`date_of_delivery`, " +
                "`products`.`shelf_life`, `products`.`name_product`, `types`.`type`, `producers`.`brand`, " +
                "`products`.`taste`, `products`.`number_of_packages`, `products`.`discounts`, `products`.`purchase_price`, " +
                "`products`.`path_to_image` FROM `products` " +
                "INNER JOIN `producers` ON `products`.`id_producer` = `producers`.`id_producer` " +
                "INNER JOIN `types` ON `products`.`id_type` = `types`.`id_type` " +
                "WHERE `products`.`name_product` LIKE ?";
    }

    static {
        SQL_SORT_PRODUCTS_BY_PRICE_UP = "SELECT SQL_CALC_FOUND_ROWS `products`.`id_product`, `products`.`name_product`," +
                " `products`.`path_to_image`, `types`.`type` FROM products INNER JOIN `types` ON `products`.`id_type` = `types`.`id_type`" +
                " ORDER BY `products`.`purchase_price` LIMIT ?, ?";
    }

    static {
        SQL_SORT_PRODUCTS_BY_PRICE_DOWN = "SELECT SQL_CALC_FOUND_ROWS `products`.`id_product`, `products`.`name_product`," +
                " `products`.`path_to_image`, `types`.`type` FROM products INNER JOIN `types` ON `products`.`id_type` = `types`.`id_type`" +
                " ORDER BY `products`.`purchase_price` DESC LIMIT ?, ?";
    }

    static {
        SQL_SORT_PRODUCTS_BY_SHELF_LIFE_UP = "SELECT SQL_CALC_FOUND_ROWS `products`.`id_product`, `products`.`date_of_delivery`, " +
                "`products`.`shelf_life`, `products`.`name_product`, `types`.`type`, `producers`.`brand`, `products`.`taste`, " +
                "`products`.`number_of_packages`, `products`.`discounts`, `products`.`purchase_price` FROM `products` " +
                "INNER JOIN `producers` ON `products`.`id_producer` = `producers`.`id_producer` " +
                "INNER JOIN `types` ON `products`.`id_type` = `types`.`id_type` ORDER BY `products`.`shelf_life` LIMIT ?, ?";
    }

    static {
        SQL_SORT_PRODUCTS_BY_SHELF_LIFE_DOWN = "SELECT SQL_CALC_FOUND_ROWS `products`.`id_product`, `products`.`date_of_delivery`, " +
                "`products`.`shelf_life`, `products`.`name_product`, `types`.`type`, `producers`.`brand`, `products`.`taste`, " +
                "`products`.`number_of_packages`, `products`.`discounts`, `products`.`purchase_price` FROM `products` " +
                "INNER JOIN `producers` ON `products`.`id_producer` = `producers`.`id_producer` " +
                "INNER JOIN `types` ON `products`.`id_type` = `types`.`id_type` ORDER BY `products`.`shelf_life` DESC LIMIT ?, ?";
    }

    static {
        SQL_SELECT_DATA_FOR_USER_CHART = "SELECT `types`.`type`, AVG(`products`.`purchase_price`) AS averagePrice" +
                " FROM `types` INNER JOIN `products` ON `types`.`id_type` = `products`.`id_type` GROUP BY `types`.`type`";
    }

    static {
        SQL_SELECT_DATA_FOR_TEMP_ORDER = "SELECT `products`.`id_product`, `products`.`name_product`, `products`.`discounts`, `products`.`purchase_price`, `types`.`type`, " +
                "`producers`.`brand`, `products`.`taste` FROM `products` INNER JOIN `producers` ON `products`.`id_producer` = `producers`.`id_producer` " +
                "INNER JOIN `types` ON `products`.`id_type` = `types`.`id_type` WHERE `products`.`id_product` = ?";
    }

    static {
        SQL_SELECT_NUMBER_OF_PACKAGES = "SELECT number_of_packages FROM products WHERE id_product = ?";
    }

    static {
        SQL_SET_NEW_NUMBER_OF_PACKAGES = "UPDATE products SET number_of_packages = ? WHERE id_product = ?";
    }

    static {
        SQL_GET_ALL_BRANDS_BY_PRODUCER_ID = "SELECT COUNT(`producers`.`brand`) AS numberOfBrands FROM `products` " +
                "INNER JOIN `producers` ON `products`.`id_producer` = `producers`.`id_producer` WHERE `producers`.`id_producer` = ?";
    }

    static {
        SQL_GET_ALL_TYPES_BY_TYPE_ID = "SELECT COUNT(`types`.`type`) AS numberOfTypes FROM `products` " +
                "INNER JOIN `types` ON `products`.`id_type` = `types`.`id_type` WHERE `types`.`id_type` = ?";
    }

    static {
        SQL_GET_ALL_PRODUCTS_BY_ID = "SELECT name_product FROM products WHERE id_product = ?";
    }

    static {
        SQL_SET_DISCOUNT = "UPDATE products SET discounts = ? WHERE id_product = ?";
    }

    static {
        SQL_SET_STATUS = "UPDATE products SET status = ? WHERE id_product = ?";
    }

    static {
        SQL_CHECK_IDENTITY_PRODUCT = "SELECT `products`.`name_product`, `products`.`taste`, `types`.`type`, " +
                "`producers`.`brand` FROM `products` INNER JOIN `types` ON `products`.`id_type` = `types`.`id_type` " +
                "INNER JOIN `producers` ON `producers`.`id_producer` = `products`.`id_producer` " +
                "WHERE `products`.`name_product` = ? AND `types`.`type` = ? AND `producers`.`brand` = ? AND `products`.`taste` = ?";
    }

    static {
        SQL_SELECT_FOUND_ROWS = "SELECT FOUND_ROWS()";
    }

    /**
     * Creates a new object of ProductDAO
     */
    private ProductDAO() {
    }

    /**
     * This method is used to get class field instance
     * by which is called the class methods.
     *
     * @return instance - object of ProductDAO.
     * @see ReentrantLock#lock()
     * @see ReentrantLock#unlock()
     */
    public static ProductDAO getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new ProductDAO();
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
    public void insert(Product entity) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_PRODUCT);
            preparedStatement.setInt(1, entity.getProducer().getId());
            preparedStatement.setInt(2, entity.getType().getId());
            preparedStatement.setString(3, entity.getDateOfDelivery());
            preparedStatement.setString(4, entity.getShelfLife());
            preparedStatement.setString(5, entity.getNameProduct());
            preparedStatement.setInt(6, entity.getNumberOfPackages());
            preparedStatement.setString(7, entity.getUnit());
            preparedStatement.setDouble(8, entity.getNumberPerUnit());
            preparedStatement.setString(9, entity.getTaste());
            preparedStatement.setInt(10, entity.getDiscounts());
            preparedStatement.setDouble(11, entity.getPurchasePrice());
            preparedStatement.setString(12, entity.getProductDescription());
            preparedStatement.setString(13, entity.getPathToImage());
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
            boolean flag = false;
            preparedStatement = connection.prepareStatement(SQL_DELETE_PRODUCT);
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
    public boolean change(int key, Product entity) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_CHANGE_PRODUCT);
            preparedStatement.setInt(1, entity.getProducer().getId());
            preparedStatement.setInt(2, entity.getType().getId());
            preparedStatement.setString(3, entity.getDateOfDelivery());
            preparedStatement.setString(4, entity.getShelfLife());
            preparedStatement.setString(5, entity.getNameProduct());
            preparedStatement.setInt(6, entity.getNumberOfPackages());
            preparedStatement.setString(7, entity.getUnit());
            preparedStatement.setDouble(8, entity.getNumberPerUnit());
            preparedStatement.setString(9, entity.getTaste());
            preparedStatement.setInt(10, entity.getDiscounts());
            preparedStatement.setDouble(11, entity.getPurchasePrice());
            preparedStatement.setString(12, entity.getProductDescription());
            preparedStatement.setString(13, entity.getPathToImage());
            preparedStatement.setInt(14, key);
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
    public List<Product> getAll() throws DaoException {
        Statement statement = null;
        Connection connection = null;
        List<Product> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_LIST);
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt(ParameterConstants.ID_PRODUCT_PARAMETER));
                product.getProducer().setId(resultSet.getInt(ParameterConstants.ID_PRODUCER_PARAMETER));
                product.getProducer().setProductionRegion(resultSet.getString(ParameterConstants.PRODUCTION_REGION_PRODUCT_PARAMETER));
                product.getProducer().setBrand(resultSet.getString(ParameterConstants.BRAND_PRODUCT_PARAMETER));
                product.getType().setId(resultSet.getInt(ParameterConstants.ID_TYPE_PARAMETER));
                product.getType().setType(resultSet.getString(ParameterConstants.TYPE_PRODUCT_PARAMETER));
                product.getType().setType(resultSet.getString(ParameterConstants.DESCRIPTION_TYPE_PARAMETER));
                product.setDateOfDelivery(resultSet.getString(ParameterConstants.DATE_OF_DELIVERY_PRODUCT_PARAMETER));
                product.setShelfLife(resultSet.getString(ParameterConstants.SHELF_LIFE_PRODUCT_PARAMETER));
                product.setNameProduct(resultSet.getString(ParameterConstants.NAME_PRODUCT_PARAMETER));
                product.setNumberOfPackages(resultSet.getInt(ParameterConstants.NUMBER_OF_PACKAGES_PRODUCT_PARAMETER));
                product.setUnit(resultSet.getString(ParameterConstants.UNIT_PRODUCT_PARAMETER));
                product.setNumberPerUnit(resultSet.getDouble(ParameterConstants.NUMBER_PER_UNIT_PRODUCT_PARAMETER));
                product.setTaste(resultSet.getString(ParameterConstants.TASTE_PRODUCT_PARAMETER));
                product.setDiscounts(resultSet.getInt(ParameterConstants.DISCOUNTS_PRODUCT_PARAMETER));
                product.setPurchasePrice(resultSet.getDouble(ParameterConstants.PURCHASE_PRICE_PRODUCT_PARAMETER));
                product.setStatus(resultSet.getString(ParameterConstants.STATUS_PRODUCT_PARAMETER));
                product.setProductDescription(resultSet.getString(ParameterConstants.DESCRIPTION_PRODUCT_PARAMETER));
                product.setPathToImage(resultSet.getString(ParameterConstants.PATH_TO_IMAGE_PRODUCT_PARAMETER));
                list.add(product);
            }
            ObjectFactory factory = new ObjectFactory();
            Products products = factory.createProducts();
            products.setProducts(list);
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
    public List<Product> getPartForPagination(int offset, int numberRecords) throws DaoException {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        Connection connection = null;
        List<Product> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(SQL_SELECT_PART_OF_LIST_FOR_ADMIN);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, numberRecords);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt(ParameterConstants.ID_PRODUCT_PARAMETER));
                product.setDateOfDelivery(resultSet.getString(ParameterConstants.DATE_OF_DELIVERY_PRODUCT_PARAMETER));
                product.setShelfLife(resultSet.getString(ParameterConstants.SHELF_LIFE_PRODUCT_PARAMETER));
                product.setNameProduct(resultSet.getString(ParameterConstants.NAME_PRODUCT_PARAMETER));
                product.getType().setType(resultSet.getString(ParameterConstants.TYPE_PRODUCT_PARAMETER));
                product.getProducer().setBrand(resultSet.getString(ParameterConstants.BRAND_PRODUCT_PARAMETER));
                product.setTaste(resultSet.getString(ParameterConstants.TASTE_PRODUCT_PARAMETER));
                product.setNumberOfPackages(resultSet.getInt(ParameterConstants.NUMBER_OF_PACKAGES_PRODUCT_PARAMETER));
                product.setDiscounts(resultSet.getInt(ParameterConstants.DISCOUNTS_PRODUCT_PARAMETER));
                product.setPurchasePrice(resultSet.getDouble(ParameterConstants.PURCHASE_PRICE_PRODUCT_PARAMETER));
                list.add(product);
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
     * @param type          sports nutrition type.
     * @param offset        index from which will accrue to the values from the table.
     * @param numberRecords the number of records that will be reflected.
     * @return returns a list of objects that contain all records from a table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public List<Product> getProductsForGuest(String type, int offset, int numberRecords) throws DaoException {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        Connection connection = null;
        List<Product> list = null;
        try {
            list = new LinkedList<>();
            ObjectFactory factory = new ObjectFactory();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(SQL_SELECT_PART_OF_LIST_FOR_GUEST);
            preparedStatement.setString(1, type);
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, numberRecords);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = factory.createProduct();
                product.setNameProduct(resultSet.getString(ParameterConstants.NAME_PRODUCT_PARAMETER));
                if (resultSet.getString(ParameterConstants.UNIT_PRODUCT_PARAMETER).equals(DefaultValueConstants.MILLILITERS_UNIT)) {
                    product.setUnit(DefaultValueConstants.MILLILITERS_REDUCTION_UNIT);
                } else {
                    product.setUnit(resultSet.getString(ParameterConstants.UNIT_PRODUCT_PARAMETER));
                }
                product.setNumberPerUnit(resultSet.getDouble(ParameterConstants.NUMBER_PER_UNIT_PRODUCT_PARAMETER));
                product.setPathToImage(resultSet.getString(ParameterConstants.PATH_TO_IMAGE_PRODUCT_PARAMETER));
                product.getProducer().setBrand(resultSet.getString(ParameterConstants.BRAND_PRODUCT_PARAMETER));
                product.setTaste(resultSet.getString(ParameterConstants.TASTE_PRODUCT_PARAMETER));
                product.setStatus(resultSet.getString(ParameterConstants.STATUS_PRODUCT_PARAMETER));
                product.setDiscounts(resultSet.getInt(ParameterConstants.DISCOUNTS_PRODUCT_PARAMETER));
                product.setPurchasePrice(resultSet.getDouble(ParameterConstants.PURCHASE_PRICE_PRODUCT_PARAMETER));
                list.add(product);
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
     * @param offset        index from which will accrue to the values from the table.
     * @param numberRecords the number of records that will be reflected.
     * @return returns a list of objects that contain all records from a table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public List<Product> getProductsForUser(int offset, int numberRecords) throws DaoException {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        Connection connection = null;
        List<Product> list = null;
        try {
            list = new LinkedList<>();
            ObjectFactory factory = new ObjectFactory();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(SQL_SELECT_PART_OF_LIST_FOR_USER);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, numberRecords);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = factory.createProduct();
                product.setId(resultSet.getInt(ParameterConstants.ID_PRODUCT_PARAMETER));
                product.setNameProduct(resultSet.getString(ParameterConstants.NAME_PRODUCT_PARAMETER));
                product.setPathToImage(resultSet.getString(ParameterConstants.PATH_TO_IMAGE_PRODUCT_PARAMETER));
                String type = ParameterConstants.TYPE_PRODUCT_PARAMETER;
                if (resultSet.getString(type).equals(DefaultValueConstants.COMPLEX)) {
                    product.getType().setType(DefaultValueConstants.COMPLEX_TYPE_REDUCTION);
                } else if (resultSet.getString(type).equals(DefaultValueConstants.SPECIAL_PREPARATION)) {
                    product.getType().setType(DefaultValueConstants.SPECIAL_PREPARATION_REDUCTION);
                } else {
                    product.getType().setType(resultSet.getString(type));
                }
                list.add(product);
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
     * @param key value that will be searched.
     * @return returns a list of objects that contain
     * sought records from a table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    @Override
    public List<Product> search(String key) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        List<Product> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_SEARCH_PRODUCTS_BY_NAME);
            preparedStatement.setString(1, SymbolConstants.PERCENT + key + SymbolConstants.PERCENT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt(ParameterConstants.ID_PRODUCT_PARAMETER));
                product.setDateOfDelivery(resultSet.getString(ParameterConstants.DATE_OF_DELIVERY_PRODUCT_PARAMETER));
                product.setShelfLife(resultSet.getString(ParameterConstants.SHELF_LIFE_PRODUCT_PARAMETER));
                product.setNameProduct(resultSet.getString(ParameterConstants.NAME_PRODUCT_PARAMETER));
                product.getType().setType(resultSet.getString(ParameterConstants.TYPE_PRODUCT_PARAMETER));
                product.getProducer().setBrand(resultSet.getString(ParameterConstants.BRAND_PRODUCT_PARAMETER));
                product.setTaste(resultSet.getString(ParameterConstants.TASTE_PRODUCT_PARAMETER));
                product.setNumberOfPackages(resultSet.getInt(ParameterConstants.NUMBER_OF_PACKAGES_PRODUCT_PARAMETER));
                product.setDiscounts(resultSet.getInt(ParameterConstants.DISCOUNTS_PRODUCT_PARAMETER));
                product.setPurchasePrice(resultSet.getDouble(ParameterConstants.PURCHASE_PRICE_PRODUCT_PARAMETER));
                product.setPathToImage(resultSet.getString(ParameterConstants.PATH_TO_IMAGE_PRODUCT_PARAMETER));
                list.add(product);
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
    public List<Product> sort(int offset, int numberRecords, String sortingType) throws DaoException {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        Connection connection = null;
        List<Product> list = null;
        try {
            list = new LinkedList<>();
            ObjectFactory factory = new ObjectFactory();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            if (sortingType.equals(ParameterConstants.UP_SORTING_PARAMETER)) {
                preparedStatement = connection.prepareStatement(SQL_SORT_PRODUCTS_BY_PRICE_UP);
            } else {
                preparedStatement = connection.prepareStatement(SQL_SORT_PRODUCTS_BY_PRICE_DOWN);
            }
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, numberRecords);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = factory.createProduct();
                product.setId(resultSet.getInt(ParameterConstants.ID_PRODUCT_PARAMETER));
                product.setNameProduct(resultSet.getString(ParameterConstants.NAME_PRODUCT_PARAMETER));
                if (resultSet.getString(ParameterConstants.TYPE_PRODUCT_PARAMETER).equals(DefaultValueConstants.COMPLEX)) {
                    product.getType().setType(DefaultValueConstants.COMPLEX_TYPE_REDUCTION);
                } else if (resultSet.getString(ParameterConstants.TYPE_PRODUCT_PARAMETER).equals(DefaultValueConstants.SPECIAL_PREPARATION)) {
                    product.getType().setType(DefaultValueConstants.SPECIAL_PREPARATION_REDUCTION);
                } else {
                    product.getType().setType(resultSet.getString(ParameterConstants.TYPE_PRODUCT_PARAMETER));
                }
                product.setPathToImage(resultSet.getString(ParameterConstants.PATH_TO_IMAGE_PRODUCT_PARAMETER));
                list.add(product);
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
     * @param offset        index from which will accrue to the values from the table.
     * @param numberRecords the number of records that will be reflected.
     * @param sortingType   sorting type, which can be either ascending or descending order
     * @return returns a list of objects that was sorted and contain records from a table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public List<Product> sortByShelfLife(int offset, int numberRecords, String sortingType) throws DaoException {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        Connection connection = null;
        List<Product> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            if (sortingType.equals(ParameterConstants.UP_SORTING_PARAMETER)) {
                preparedStatement = connection.prepareStatement(SQL_SORT_PRODUCTS_BY_SHELF_LIFE_UP);
            } else {
                preparedStatement = connection.prepareStatement(SQL_SORT_PRODUCTS_BY_SHELF_LIFE_DOWN);
            }
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, numberRecords);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                int productId = resultSet.getInt(ParameterConstants.ID_PRODUCT_PARAMETER);
                product.setId(productId);
                product.setDateOfDelivery(resultSet.getString(ParameterConstants.DATE_OF_DELIVERY_PRODUCT_PARAMETER));
                product.setShelfLife(resultSet.getString(ParameterConstants.SHELF_LIFE_PRODUCT_PARAMETER));
                product.setNameProduct(resultSet.getString(ParameterConstants.NAME_PRODUCT_PARAMETER));
                product.getType().setType(resultSet.getString(ParameterConstants.TYPE_PRODUCT_PARAMETER));
                product.getProducer().setBrand(resultSet.getString(ParameterConstants.BRAND_PRODUCT_PARAMETER));
                product.setTaste(resultSet.getString(ParameterConstants.TASTE_PRODUCT_PARAMETER));
                product.setNumberOfPackages(resultSet.getInt(ParameterConstants.NUMBER_OF_PACKAGES_PRODUCT_PARAMETER));
                product.setDiscounts(resultSet.getInt(ParameterConstants.DISCOUNTS_PRODUCT_PARAMETER));
                product.setPurchasePrice(resultSet.getDouble(ParameterConstants.PURCHASE_PRICE_PRODUCT_PARAMETER));
                list.add(product);
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
     * @param key the index to which will be searched for data.
     * @return object of class Product.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public Product getFullProductData(int key) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Product product = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_FULL_DATA_PRODUCT_FOR_USER);
            preparedStatement.setInt(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            product = new Product();
            while (resultSet.next()) {
                product.setId(resultSet.getInt(ParameterConstants.ID_PRODUCT_PARAMETER));
                product.getProducer().setBrand(resultSet.getString(ParameterConstants.BRAND_PRODUCT_PARAMETER));
                product.getProducer().setProductionRegion(resultSet.getString(ParameterConstants.PRODUCTION_REGION_PRODUCT_PARAMETER));
                product.getType().setType(resultSet.getString(ParameterConstants.TYPE_PRODUCT_PARAMETER));
                product.setDateOfDelivery(resultSet.getString(ParameterConstants.DATE_OF_DELIVERY_PRODUCT_PARAMETER));
                product.setShelfLife(resultSet.getString(ParameterConstants.SHELF_LIFE_PRODUCT_PARAMETER));
                product.setNameProduct(resultSet.getString(ParameterConstants.NAME_PRODUCT_PARAMETER));
                product.setNumberOfPackages(resultSet.getInt(ParameterConstants.NUMBER_OF_PACKAGES_PRODUCT_PARAMETER));
                product.setUnit(resultSet.getString(ParameterConstants.UNIT_PRODUCT_PARAMETER));
                product.setNumberPerUnit(resultSet.getDouble(ParameterConstants.NUMBER_PER_UNIT_PRODUCT_PARAMETER));
                product.setTaste(resultSet.getString(ParameterConstants.TASTE_PRODUCT_PARAMETER));
                product.setDiscounts(resultSet.getInt(ParameterConstants.DISCOUNTS_PRODUCT_PARAMETER));
                product.setPurchasePrice(resultSet.getDouble(ParameterConstants.PURCHASE_PRICE_PRODUCT_PARAMETER));
                product.setStatus(resultSet.getString(ParameterConstants.STATUS_PRODUCT_PARAMETER));
                product.setProductDescription(resultSet.getString(ParameterConstants.DESCRIPTION_PRODUCT_PARAMETER));
                product.setPathToImage(resultSet.getString(ParameterConstants.PATH_TO_IMAGE_PRODUCT_PARAMETER));
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return product;
    }

    /**
     * @return data for user chart
     * @throws DaoException exception which occurs when an error in sql.
     */
    public List<UserChart> getDataForUserChart() throws DaoException {
        Statement statement = null;
        Connection connection = null;
        List<UserChart> list = null;
        try {
            list = new LinkedList<>();
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_DATA_FOR_USER_CHART);
            while (resultSet.next()) {
                UserChart data = new UserChart();
                data.setType(resultSet.getString(ParameterConstants.TYPE_PRODUCT_PARAMETER));
                data.setAveragePrice(resultSet.getDouble(ParameterConstants.AVERAGE_PRICE_PARAMETER));
                list.add(data);
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closeStatement(statement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return list;
    }

    /**
     * @param userId           id of user which added to object.
     * @param productId        key that will be searched.
     * @param numberOfPackages number of packages parameter will add to object.
     * @return OrderProduct object.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public OrderProduct fillPartOfObjectForTempOrder(int userId, int productId, int numberOfPackages) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        OrderProduct orderProduct = new OrderProduct();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_DATA_FOR_TEMP_ORDER);
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderProduct.getProduct().setId(resultSet.getInt(ParameterConstants.ID_PRODUCT_PARAMETER));
                orderProduct.getOrder().getUser().setId(userId);
                orderProduct.getProduct().setNameProduct(resultSet.getString(ParameterConstants.NAME_PRODUCT_PARAMETER));
                orderProduct.getProduct().getType().setType(resultSet.getString(ParameterConstants.TYPE_PRODUCT_PARAMETER));
                orderProduct.getProduct().getProducer().setBrand(resultSet.getString(ParameterConstants.BRAND_PRODUCT_PARAMETER));
                orderProduct.getProduct().setTaste(resultSet.getString(ParameterConstants.TASTE_PRODUCT_PARAMETER));
                orderProduct.setNumberOfPackages(numberOfPackages);
                orderProduct.getProduct().setDiscounts(resultSet.getInt(ParameterConstants.DISCOUNTS_PRODUCT_PARAMETER));
                double purchasePrice = resultSet.getDouble(ParameterConstants.PURCHASE_PRICE_PRODUCT_PARAMETER);
                double cost = purchasePrice * (double) numberOfPackages;
                orderProduct.setCost(cost);
            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.INSERT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return orderProduct;
    }

    /**
     * @param productId key that will be searched.
     * @return number of packages.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public int getNumberOfPackages(int productId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        int numberOfPackages = 0;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_NUMBER_OF_PACKAGES);
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                numberOfPackages = resultSet.getInt(ParameterConstants.NUMBER_OF_PACKAGES_PRODUCT_PARAMETER);

            }
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.SELECT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return numberOfPackages;
    }

    /**
     * @param productId        key that will be searched.
     * @param numberOfPackages new number of packages which insert in the table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public void setNewNumberOfPackages(int productId, int numberOfPackages) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_SET_NEW_NUMBER_OF_PACKAGES);
            preparedStatement.setInt(1, numberOfPackages);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.INSERT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * @param producerId key that will be searched.
     * @return true if in the table there are brands by producerId
     * and false of otherwise.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public boolean isThereBrandsByProducerId(int producerId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        boolean flag = false;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_ALL_BRANDS_BY_PRODUCER_ID);
            preparedStatement.setInt(1, producerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(ParameterConstants.NUMBER_OF_TYPES_PARAMETER) > 0) {
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
     * @param typeId key that will be searched.
     * @return true if in the table there are type by typeId
     * and false of otherwise.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public boolean isThereTypesByTypeId(int typeId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean flag = false;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_ALL_TYPES_BY_TYPE_ID);
            preparedStatement.setInt(1, typeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(ParameterConstants.NUMBER_OF_TYPES_PARAMETER) > 0) {
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
     * @param productId key that will be searched.
     * @return true if in the table there are products by productId
     * and false of otherwise.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public boolean isThereProductsById(int productId) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean flag = false;
        try {
            preparedStatement = connection.prepareStatement(SQL_GET_ALL_PRODUCTS_BY_ID);
            preparedStatement.setInt(1, productId);
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
     * @param productId key that will be searched.
     * @param discount  new discount for product which insert in the table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public void setDiscount(int productId, int discount) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_SET_DISCOUNT);
            preparedStatement.setInt(1, discount);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.INSERT_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * @param productId key that will be searched.
     * @param status    new status for product which insert in the table.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public void setNewProductStatus(int productId, String status) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            preparedStatement = connection.prepareStatement(SQL_SET_STATUS);
            preparedStatement.setInt(2, productId);
            preparedStatement.setString(1, status);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e + SymbolConstants.SPACE + ExceptionConstants.UPDATE_DAO_EXCEPTION);
        } finally {
            closePreparedStatement(preparedStatement);
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    /**
     * @param nameProduct nameProduct parameter - first part of composite search key.
     * @param type        type parameter - second part of composite search key.
     * @param brand       brand parameter - third part of composite search key.
     * @param taste       taste parameter - fourth part of composite search key.
     * @return true if in the table there are products by parameters:
     * nameProduct, type, brand and taste
     * and false of otherwise.
     * @throws DaoException exception which occurs when an error in sql.
     */
    public boolean isProductExists(String nameProduct, String type, String brand, String taste) throws DaoException {
        PreparedStatement preparedStatement = null;
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean flag = false;
        try {
            preparedStatement = connection.prepareStatement(SQL_CHECK_IDENTITY_PRODUCT);
            preparedStatement.setString(1, nameProduct);
            preparedStatement.setString(2, type);
            preparedStatement.setString(3, brand);
            preparedStatement.setString(4, taste);
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
     * @return number of records from table which satisfy the condition.
     */
    public int getNumberRecords() {
        return numberRecords;
    }
}
