package com.epam.onlineshop.services;

import com.epam.onlineshop.constants.*;
import com.epam.onlineshop.dao.OrderProductDAO;
import com.epam.onlineshop.dao.ProducerDAO;
import com.epam.onlineshop.dao.ProductDAO;
import com.epam.onlineshop.dao.TypeDAO;
import com.epam.onlineshop.entity.products.Product;
import com.epam.onlineshop.entity.statistics.UserChart;
import com.epam.onlineshop.enums.TypeEnum;
import com.epam.onlineshop.exceptions.DaoException;
import com.epam.onlineshop.exceptions.ProductTechnicalException;
import com.epam.onlineshop.validation.ProductsValidator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The ProductsService class responsible for performing operations on data in the products table.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class ProductsService {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(ProductsService.class);

    /**
     * @param productParameters parameters of product which will be validate
     * @return flag. Flag can be success or error.
     */
    public static int validateParameters(Map<String, String> productParameters) {
        if (ProductsValidator.checkDuplicateProduct(productParameters)) {
            return LogicFlagConstants.FIRST_ERROR_FLAG;
        }
        try {
            ProductsValidator.checkPathToImage(productParameters.get(ParameterConstants.PATH_TO_IMAGE_PRODUCT_PARAMETER));
        } catch (ProductTechnicalException e) {
            log.error(e);
            return LogicFlagConstants.SECOND_ERROR_FLAG;
        }
        return LogicFlagConstants.SUCCESS_FLAG;
    }

    /**
     * @param productParameters parameters of product which will be add
     */
    public static void addProduct(Map<String, String> productParameters) {
        try {
            int typeId = TypeDAO.getInstance().getTypeIdByType(productParameters.get(ParameterConstants.TYPE_PRODUCT_PARAMETER));
            int producerId = ProducerDAO.getInstance().getProducerIdByBrand(productParameters.get(ParameterConstants.BRAND_PRODUCT_PARAMETER));
            productParameters.remove(ParameterConstants.TYPE_PRODUCT_PARAMETER);
            productParameters.remove(ParameterConstants.BRAND_PRODUCT_PARAMETER);
            Product product = new Product();
            product.getType().setId(typeId);
            product.getProducer().setId(producerId);
            product.setDateOfDelivery(productParameters.get(ParameterConstants.DATE_OF_DELIVERY_PRODUCT_PARAMETER));
            product.setShelfLife(productParameters.get(ParameterConstants.SHELF_LIFE_PRODUCT_PARAMETER));
            product.setNameProduct(productParameters.get(ParameterConstants.NAME_PRODUCT_PARAMETER));
            product.setNumberOfPackages(Integer.parseInt(productParameters.get(ParameterConstants.NUMBER_OF_PACKAGES_PRODUCT_PARAMETER)));
            product.setUnit(productParameters.get(ParameterConstants.UNIT_PRODUCT_PARAMETER));
            product.setNumberPerUnit(Integer.parseInt(productParameters.get(ParameterConstants.NUMBER_PER_UNIT_PRODUCT_PARAMETER)));
            product.setTaste(productParameters.get(ParameterConstants.TASTE_PRODUCT_PARAMETER));
            product.setDiscounts(Integer.parseInt(productParameters.get(ParameterConstants.DISCOUNTS_PRODUCT_PARAMETER)));
            product.setPurchasePrice(Double.parseDouble(productParameters.get(ParameterConstants.PURCHASE_PRICE_PRODUCT_PARAMETER)));
            product.setProductDescription(productParameters.get(ParameterConstants.DESCRIPTION_PRODUCT_PARAMETER));
            product.setPathToImage(productParameters.get(ParameterConstants.PATH_TO_IMAGE_PRODUCT_PARAMETER));
            ProductDAO.getInstance().insert(product);
        } catch (DaoException e) {
            log.error(e);
        }
    }

    /**
     * @param productId id of product which will be deleted
     * @return flag. Flag can be success or error.
     */
    public static boolean deleteProduct(int productId) {
        boolean flag = false;
        try {
            flag = OrderProductDAO.getInstance().isThereOrdersByProductId(productId);
            if (!flag) {
                ProductDAO.getInstance().delete(productId);
            }
        } catch (DaoException e) {
            log.error(e);
        }
        return flag;
    }

    /**
     * @param productId         id of product which will be updated
     * @param productParameters new parameters of product
     * @return flag. Flag can be success or error.
     */
    public static int changeProduct(int productId, Map<String, String> productParameters) {
        int mainFlag = LogicFlagConstants.SUCCESS_FLAG;
        try {
            boolean flag = OrderProductDAO.getInstance().isThereOrdersByProductId(productId);
            if (!flag) {
                int typeId = TypeDAO.getInstance().getTypeIdByType(productParameters.get(ParameterConstants.TYPE_PRODUCT_PARAMETER));
                int producerId = ProducerDAO.getInstance().getProducerIdByBrand(productParameters.get(ParameterConstants.BRAND_PRODUCT_PARAMETER));
                Product product = new Product();
                product.getType().setId(typeId);
                product.getProducer().setId(producerId);
                product.setDateOfDelivery(productParameters.get(ParameterConstants.DATE_OF_DELIVERY_PRODUCT_PARAMETER));
                product.setShelfLife(productParameters.get(ParameterConstants.SHELF_LIFE_PRODUCT_PARAMETER));
                product.setNameProduct(productParameters.get(ParameterConstants.NAME_PRODUCT_PARAMETER));
                product.setNumberOfPackages(Integer.parseInt(productParameters.get(ParameterConstants.NUMBER_OF_PACKAGES_PRODUCT_PARAMETER)));
                product.setUnit(productParameters.get(ParameterConstants.UNIT_PRODUCT_PARAMETER));
                product.setNumberPerUnit(Double.parseDouble(productParameters.get(ParameterConstants.NUMBER_PER_UNIT_PRODUCT_PARAMETER)));
                product.setTaste(productParameters.get(ParameterConstants.TASTE_PRODUCT_PARAMETER));
                product.setDiscounts(Integer.parseInt(productParameters.get(ParameterConstants.DISCOUNTS_PRODUCT_PARAMETER)));
                product.setPurchasePrice(Double.parseDouble(productParameters.get(ParameterConstants.PURCHASE_PRICE_PRODUCT_PARAMETER)));
                product.setProductDescription(productParameters.get(ParameterConstants.DESCRIPTION_PRODUCT_PARAMETER));
                product.setPathToImage(productParameters.get(ParameterConstants.PATH_TO_IMAGE_PRODUCT_PARAMETER));
                ProductDAO.getInstance().change(productId, product);
            } else {
                mainFlag = LogicFlagConstants.FIRST_ERROR_FLAG;
            }
            try {
                ProductsValidator.checkPathToImage(productParameters.get(ParameterConstants.PATH_TO_IMAGE_PRODUCT_PARAMETER));
            } catch (ProductTechnicalException e) {
                mainFlag = LogicFlagConstants.SECOND_ERROR_FLAG;
                log.error(e);
            }
        } catch (DaoException e) {
            log.error(e);
        }
        return mainFlag;
    }

    /**
     * @param type          type of sport nutrition
     * @param offset        the index in the table, from which the output begins
     * @param numberRecords the number of records that will be reflected
     * @return list with products
     */
    public static List<Product> printProductsForGuest(String type, int offset, int numberRecords) {
        List<Product> products = new ArrayList<>();
        TypeEnum typeEnum = TypeEnum.valueOf(type.toUpperCase());
        try {
            switch (typeEnum) {
                case PROTEINS: {
                    products = ProductDAO.getInstance().getProductsForGuest(DefaultValueConstants.PROTEIN, offset, numberRecords);
                    break;
                }
                case AMINOACIDS: {
                    products = ProductDAO.getInstance().getProductsForGuest(DefaultValueConstants.AMINO, offset, numberRecords);
                    break;
                }
                case BCAA: {
                    products = ProductDAO.getInstance().getProductsForGuest(DefaultValueConstants.BCAA, offset, numberRecords);
                    break;
                }
                case GAINERS: {
                    products = ProductDAO.getInstance().getProductsForGuest(DefaultValueConstants.GAINER, offset, numberRecords);
                    break;
                }
                case CREATINE: {
                    products = ProductDAO.getInstance().getProductsForGuest(DefaultValueConstants.CREATINE, offset, numberRecords);
                    break;
                }
                case TESTOSTERONE: {
                    products = ProductDAO.getInstance().getProductsForGuest(DefaultValueConstants.TESTOSTERONE, offset, numberRecords);
                    break;
                }
                case COMPLEXES: {
                    products = ProductDAO.getInstance().getProductsForGuest(DefaultValueConstants.COMPLEX, offset, numberRecords);
                    break;
                }
                case WEIGHTLOSS: {
                    products = ProductDAO.getInstance().getProductsForGuest(DefaultValueConstants.WEIGHT_LOSS, offset, numberRecords);
                    break;
                }
                case CHONDROPROTECTORS: {
                    products = ProductDAO.getInstance().getProductsForGuest(DefaultValueConstants.CHONDROPROTECTOR, offset, numberRecords);
                    break;
                }
                case VITAMINS: {
                    products = ProductDAO.getInstance().getProductsForGuest(DefaultValueConstants.VITAMIN, offset, numberRecords);
                    break;
                }
                case ENERGY: {
                    products = ProductDAO.getInstance().getProductsForGuest(DefaultValueConstants.ENERGY, offset, numberRecords);
                    break;
                }
                case SPECIALPREPARATIONS: {
                    products = ProductDAO.getInstance().getProductsForGuest(DefaultValueConstants.SPECIAL_PREPARATION, offset, numberRecords);
                    break;
                }
                case BARS: {
                    products = ProductDAO.getInstance().getProductsForGuest(DefaultValueConstants.BAR, offset, numberRecords);
                    break;
                }
                default: {
                    log.info(MessageConstants.LOG_INPUT_ERROR);
                }
            }
        } catch (DaoException e) {
            log.error(e);
        }
        return products;
    }

    /**
     * @param offset        the index in the table, from which the output begins
     * @param numberRecords the number of records that will be reflected
     * @return list with products
     */
    public static List<Product> printProductsForUser(int offset, int numberRecords) {
        List<Product> products = null;
        try {
            products = ProductDAO.getInstance().getProductsForUser(offset, numberRecords);
        } catch (DaoException e) {
            log.error(e);
        }
        return products;
    }

    /**
     * @param offset        the index in the table, from which the output begin
     * @param numberRecords the number of records that will be reflected
     * @return list with products
     */
    public static List<Product> printProductsForAdmin(int offset, int numberRecords) {
        List<Product> products = null;
        try {
            products = ProductDAO.getInstance().getPartForPagination(offset, numberRecords);
        } catch (DaoException e) {
            log.error(e);
        }
        return products;
    }

    /**
     * @param nameProduct name of product which will be find
     * @return list with find products
     */
    public static List<Product> searchProductsByName(String nameProduct) {
        List<Product> products = null;
        try {
            products = ProductDAO.getInstance().search(nameProduct);
        } catch (DaoException e) {
            log.error(e);
        }
        return products;
    }

    /**
     * @param offset        the index in the table, from which the output begins
     * @param numberRecords the number of records that will be reflected
     * @param sortingType   type of sorting. Type can be asc and desc
     * @return list of sorted products
     */
    public static List<Product> sortProductsForUser(int offset, int numberRecords, String sortingType) {
        List<Product> products = null;
        try {
            products = ProductDAO.getInstance().sort(offset, numberRecords, sortingType);
        } catch (DaoException e) {
            log.error(e);
        }
        return products;
    }

    /**
     * @param offset        the index in the table, from which the output begins
     * @param numberRecords the number of records that will be reflected
     * @param sortingType   type of sorting. Type can be asc and desc
     * @return list of sorted products
     */
    public static List<Product> sortProductsForAdmin(int offset, int numberRecords, String sortingType) {
        List<Product> products = null;
        try {
            products = ProductDAO.getInstance().sortByShelfLife(offset, numberRecords, sortingType);
        } catch (DaoException e) {
            log.error(e);
        }
        return products;
    }

    /**
     * @param productId id of product which will be printed
     * @return product
     */
    public static Product getFullDataProduct(String productId) {
        int key = Integer.parseInt(productId);
        Product product = null;
        try {
            product = ProductDAO.getInstance().getFullProductData(key);
        } catch (DaoException e) {
            log.error(e);
        }
        return product;
    }

    /**
     * @return list wirh data for user statistics
     */
    public static List<UserChart> getDataForUserChart() {
        List<UserChart> data = null;
        try {
            data = ProductDAO.getInstance().getDataForUserChart();
        } catch (DaoException e) {
            log.error(e);
        }
        return data;
    }

    /**
     * @param productId id of product which will be discounted
     * @param discount  new discount of product
     * @return flag. Flag can be success or error.
     */
    public static boolean setDiscount(int productId, int discount) {
        boolean flag = false;
        try {
            flag = ProductDAO.getInstance().isThereProductsById(productId);
            if (flag) {
                ProductDAO.getInstance().setDiscount(productId, discount);
            }
        } catch (DaoException e) {
            log.error(e);
        }
        return flag;
    }

    /**
     *
     * @return number of records
     */
    public static int getNumberRecords() {
        return ProductDAO.getInstance().getNumberRecords();
    }
}
