package com.epam.onlineshop.entity.ordersproducts;

import com.epam.onlineshop.constants.DefaultValueConstants;
import com.epam.onlineshop.entity.Entity;
import com.epam.onlineshop.entity.orders.Order;
import com.epam.onlineshop.entity.products.Product;

import java.io.Serializable;

/**
 * The OrderProduct It is the class responsible for table OrderProduct in database.
 * Implements Cloneable and Serializable.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class OrderProduct extends Entity implements Cloneable, Serializable {
    /**
     * Property - object Order
     */
    private Order order;
    /**
     * Property - object Product
     */
    private Product product;
    /**
     * Property - number of packages in order
     */
    private int numberOfPackages;
    /**
     * Property - cost of order
     */
    private double cost;

    /**
     * Create a new empty object
     *
     * @see OrderProduct#OrderProduct(int, Order, Product, int, double)
     */
    public OrderProduct() {
        super();
        this.order = new Order();
        this.product = new Product();
        this.numberOfPackages = DefaultValueConstants.START_INDEX;
        this.cost = DefaultValueConstants.DEFAULT_DOUBLE_VALUE;
    }

    /**
     * It creates a new object with the specified values
     *
     * @param id               unique id for entity
     * @param order            order
     * @param product          product who ordered
     * @param numberOfPackages number of packages in order
     * @param cost             cost of order
     * @see OrderProduct#OrderProduct()
     */
    public OrderProduct(int id, Order order, Product product, int numberOfPackages, double cost) {
        super(id);
        this.order = order;
        this.product = product;
        this.numberOfPackages = numberOfPackages;
        this.cost = cost;
    }

    /**
     * Method to get the value field {@link OrderProduct#order}
     *
     * @return Return order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order new order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Method to get the value field {@link OrderProduct#product}
     *
     * @return Return product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product new product in order
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Method to get the value field {@link OrderProduct#numberOfPackages}
     *
     * @return Return number of packages
     */
    public int getNumberOfPackages() {
        return numberOfPackages;
    }

    /**
     * @param numberOfPackages new number of packages
     */
    public void setNumberOfPackages(int numberOfPackages) {
        this.numberOfPackages = numberOfPackages;
    }

    /**
     * Method to get the value field {@link OrderProduct#cost}
     *
     * @return Return cost of order
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost new cost of order
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return clone of object
     * @throws CloneNotSupportedException An exception occurs when you try to clone the object
     */
    @Override
    public OrderProduct clone() throws CloneNotSupportedException {
        OrderProduct orderProduct = (OrderProduct) super.clone();
        orderProduct.order = order.clone();
        orderProduct.product = product.clone();
        return orderProduct;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String with all fields of the class.
     */
    @Override
    public String toString() {
        return super.toString() + " Order: " + this.order.toString() + '\n' + " Product: " + this.product.toString() + '\n' +
                " Number of packages: " + this.numberOfPackages + "pcs." + '\n' + " Cost: " + this.cost + "rub." + '\n';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj object to be checked for equivalence
     * @return return true if objects is equal and false in otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (obj instanceof OrderProduct) {
            OrderProduct temp = (OrderProduct) obj;
            return this.order.equals(temp.order) &&
                    this.product.equals(temp.product) &&
                    this.numberOfPackages == temp.numberOfPackages &&
                    this.cost == temp.cost;
        } else return false;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return hash code of object
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = numberOfPackages;
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
