package com.epam.onlineshop.entity.orders;

import com.epam.onlineshop.constants.DefaultValueConstants;
import com.epam.onlineshop.entity.Entity;
import com.epam.onlineshop.entity.users.User;

import java.io.Serializable;

/**
 * The Order It is the class responsible for order.
 * Implements Cloneable and Serializable.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class Order extends Entity implements Cloneable, Serializable {
    /**
     * Property - object User
     */
    private User user;
    /**
     * Property - lead time
     */
    private String leadTime;
    /**
     * Property - delivery condition
     */
    private String deliveryCondition;
    /**
     * Property - status of order
     */
    private String status;
    /**
     * Property - type of payment
     */
    private String typeOfPayment;
    /**
     * Property - total cost of order
     */
    private double totalCost;

    /**
     * Create a new empty object
     *
     * @see Order#Order(int, User, String, String, String, String, double)
     */
    public Order() {
        super();
        this.user = new User();
        this.leadTime = DefaultValueConstants.DEFAULT_EMPTY_STRING;
        this.deliveryCondition = DefaultValueConstants.DEFAULT_ORDER_DELIVERY_CONDITION;
        this.status = DefaultValueConstants.DEFAULT_ORDER_STATUS;
        this.typeOfPayment = DefaultValueConstants.DEFAULT_ORDER_TYPE_OF_PAYMENT;
        this.totalCost = DefaultValueConstants.DEFAULT_DOUBLE_VALUE;
    }

    /**
     * It creates a new object with the specified values
     *
     * @param orderId           unique id for entity
     * @param user              user which checkout
     * @param leadTime          lead time of order
     * @param deliveryCondition delivery condition for order
     * @param typeOfPayment     type of payment for order
     * @param status            status of order
     * @param totalCost         total cost of order
     * @see Order#Order()
     */
    public Order(int orderId, User user, String leadTime, String deliveryCondition, String status,
                 String typeOfPayment, double totalCost) {
        super(orderId);
        this.user = user;
        this.leadTime = leadTime;
        this.deliveryCondition = deliveryCondition;
        this.status = status;
        this.typeOfPayment = typeOfPayment;
        this.totalCost = totalCost;
    }

    /**
     * Method to get the value field {@link Order#user}
     *
     * @return Return user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user new user of order
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Method to get the value field {@link Order#leadTime}
     *
     * @return Return lead time
     */
    public String getLeadTime() {
        return leadTime;
    }

    /**
     * @param leadTime new lead time for order
     */
    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    /**
     * Method to get the value field {@link Order#deliveryCondition}
     *
     * @return Return deliveryCondition
     */
    public String getDeliveryCondition() {
        return deliveryCondition;
    }

    /**
     * @param deliveryCondition new delivery condition for order
     */
    public void setDeliveryCondition(String deliveryCondition) {
        this.deliveryCondition = deliveryCondition;
    }

    /**
     * Method to get the value field {@link Order#status}
     *
     * @return Return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status new status of order
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Method to get the value field {@link Order#typeOfPayment}
     *
     * @return Return type of payment
     */
    public String getTypeOfPayment() {
        return typeOfPayment;
    }

    /**
     * @param typeOfPayment new type of payment
     */
    public void setTypeOfPayment(String typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    /**
     * Method to get the value field {@link Order#totalCost}
     *
     * @return Return total cost
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost new total cost of order
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return clone of object
     * @throws CloneNotSupportedException An exception occurs when you try to clone the object
     */
    @Override
    public Order clone() throws CloneNotSupportedException {
        Order order = (Order) super.clone();
        order.user = user.clone();
        return order;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String with all fields of the class.
     */
    @Override
    public String toString() {
        return super.toString() + " User: " + this.user.toString() + '\n' + " Lead time: " + this.leadTime + '\n' +
                " Delivery condition: " + this.deliveryCondition + '\n' + " Status: " + this.status + '\n' +
                " Type of payment: " + this.typeOfPayment + '\n' + " Total cost: " + this.totalCost + '\n';
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
        if (obj instanceof Order) {
            Order temp = (Order) obj;
            return this.getId() == temp.getId() &&
                    this.user.equals(temp.user) &&
                    this.leadTime.equals(temp.leadTime) &&
                    this.deliveryCondition.equals(temp.deliveryCondition) &&
                    this.status.equals(temp.status) &&
                    this.typeOfPayment.equals(temp.typeOfPayment) &&
                    this.totalCost == temp.totalCost;
        } else return false;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return hash code of object
     */
    @Override
    public int hashCode() {
        int result = leadTime != null ? leadTime.hashCode() : 0;
        result = 31 * result + (deliveryCondition != null ? deliveryCondition.hashCode() : 0);
        return result;
    }
}
