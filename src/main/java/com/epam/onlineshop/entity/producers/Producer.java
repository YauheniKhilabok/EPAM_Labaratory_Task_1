package com.epam.onlineshop.entity.producers;

import com.epam.onlineshop.constants.DefaultValueConstants;
import com.epam.onlineshop.entity.Entity;

import java.io.Serializable;

/**
 * The Producer It is the class responsible for table producers in database.
 * Implements Cloneable and Serializable.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class Producer extends Entity implements Cloneable, Serializable {
    /**
     * Property - production region
     */
    private String productionRegion;
    /**
     * Property - brand
     */
    private String brand;

    /**
     * Create a new empty object
     *
     * @see Producer#Producer(int, String, String)
     */
    public Producer() {
        super();
        this.productionRegion = DefaultValueConstants.DEFAULT_EMPTY_STRING;
        this.brand = DefaultValueConstants.DEFAULT_EMPTY_STRING;
    }

    /**
     * It creates a new object with the specified values
     *
     * @param producerId       unique id for entity
     * @param productionRegion production region
     * @param brand            brand of product
     * @see Producer#Producer()
     */
    public Producer(int producerId, String productionRegion, String brand) {
        super(producerId);
        this.productionRegion = productionRegion;
        this.brand = brand;
    }

    /**
     * Method to get the value field {@link Producer#productionRegion}
     *
     * @return Return production region
     */
    public String getProductionRegion() {
        return productionRegion;
    }

    /**
     * @param productionRegion new production region
     */
    public void setProductionRegion(String productionRegion) {
        this.productionRegion = productionRegion;
    }

    /**
     * Method to get the value field {@link Producer#brand}
     *
     * @return Return brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand new brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return clone of object
     * @throws CloneNotSupportedException An exception occurs when you try to clone the object
     */
    @Override
    public Producer clone() throws CloneNotSupportedException {
        return (Producer) super.clone();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String with all fields of the class.
     */
    @Override
    public String toString() {
        return super.toString() + " Brand: " + this.brand + '\n' + " Production region: " + this.productionRegion + '\n';
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
        if (obj instanceof Producer) {
            Producer temp = (Producer) obj;
            return this.getId() == temp.getId() &&
                    this.brand.equals(temp.brand) &&
                    this.productionRegion.equals(temp.productionRegion);
        } else return false;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return hash code of object
     */
    @Override
    public int hashCode() {
        int result = brand.hashCode();
        result = 31 * result + productionRegion.hashCode();
        return result;
    }
}
