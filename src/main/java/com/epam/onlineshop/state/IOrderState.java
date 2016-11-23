package com.epam.onlineshop.state;

/**
 * The IOrderState interface declares a method
 * that will take some actions based on the order status.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public interface IOrderState {
    /**
     * Perform certain actions on the basis of state order
     *
     * @param context the context in which the action takes place
     * @param orderId Id order with which certain actions are performed
     */
    void doAction(OrderContext context, int orderId);
}
