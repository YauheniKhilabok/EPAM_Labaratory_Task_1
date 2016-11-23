package com.epam.onlineshop.entity.orders;

import com.epam.onlineshop.entity.DeepCloning;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Orders It is the class responsible for list of orders.
 * Implements Cloneable and Serializable.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class Orders implements Cloneable, Serializable {
    /**
     * Property - log, which need to logging code
     */
    private static final Logger log = Logger.getLogger(Orders.class);
    /**
     * Property - list of orders
     */
    private List<Order> orders;

    /**
     * Create new empty object and initialize orders
     */
    public Orders() {
        orders = new ArrayList<>();
    }

    /**
     * Method to get the value field {@link Orders#orders}
     *
     * @return Return list of orders
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * @param orders new list of orders
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String with all fields of the class.
     */
    @Override
    public String toString() {
        String resultList = "Orders:" + '\n';
        for (Order order : orders) {
            resultList += order.toString() + "\n";
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
        Orders copy = (Orders) super.clone();
        Object object = null;
        try {
            object = DeepCloning.getDeepCloning(copy);
        } catch (Exception e) {
            log.error(e);
        }
        return object;
    }
}
