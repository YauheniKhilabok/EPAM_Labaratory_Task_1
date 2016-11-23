package com.epam.onlineshop.entity.ordersproducts;

import com.epam.onlineshop.entity.DeepCloning;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * The OrdersProducts It is the class responsible for list of ordersProducts.
 * Implements Cloneable and Serializable.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class OrdersProducts implements Cloneable, Serializable {
    /**
     * Property - log, which need to logging code
     */
    private static final Logger log = Logger.getLogger(OrdersProducts.class);
    /**
     * Property - list of ordersProducts
     */
    private List<OrderProduct> ordersProducts;

    /**
     * Create new empty object and initialize ordersProducts
     */
    public OrdersProducts() {
        ordersProducts = new LinkedList<>();
    }

    /**
     * Method to get the value field {@link OrdersProducts#ordersProducts}
     *
     * @return Return list of ordersProducts
     */
    public List<OrderProduct> getOrdersProducts() {
        return ordersProducts;
    }

    /**
     * @param ordersProducts new list of ordersProducts
     */
    public void setOrdersProducts(List<OrderProduct> ordersProducts) {
        this.ordersProducts = ordersProducts;
    }

    /**
     * Add new object to the list
     *
     * @param obj new object
     */
    public void add(OrderProduct obj) {
        this.ordersProducts.add(obj);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String with all fields of the class.
     */
    @Override
    public String toString() {
        String resultList = "OrdersProducts:" + '\n';
        for (OrderProduct orderProduct : ordersProducts) {
            resultList += orderProduct.toString() + "\n";
        }
        return resultList;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return clone of object
     * @throws CloneNotSupportedException An exception occurs when you try to clone the object
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        OrdersProducts copy = (OrdersProducts) super.clone();
        Object object = null;
        try {
            object = DeepCloning.getDeepCloning(copy);
        } catch (Exception e) {
            log.error(e);
        }
        return object;
    }
}
