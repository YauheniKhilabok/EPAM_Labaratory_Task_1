package com.epam.onlineshop.entity.products;

import com.epam.onlineshop.entity.DeepCloning;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Products It is the class responsible for list of products.
 * Implements Cloneable and Serializable.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class Products implements Cloneable, Serializable {
    /**
     * Property - log, which need to logging code
     */
    private static final Logger log = Logger.getLogger(Products.class);
    /**
     * Property - list of products
     */
    private List<Product> products;

    /**
     * Create new empty object and initialize products
     */
    public Products() {
        products = new ArrayList<>();
    }

    /**
     * Method to get the value field {@link Products#products}
     *
     * @return Return list of products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * @param products new list of products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String with all fields of the class.
     */
    @Override
    public String toString() {
        String resultList = "Products:" + '\n';
        for (Product product : products) {
            resultList += product.toString() + "\n";
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
        Products copy = (Products) super.clone();
        Object object = null;
        try {
            object = DeepCloning.getDeepCloning(copy);
        } catch (Exception e) {
            log.error(e);
        }
        return object;
    }
}

