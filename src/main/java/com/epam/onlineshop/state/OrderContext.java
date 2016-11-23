package com.epam.onlineshop.state;

/**
 * The OrderContext - entity class responsible for state of order.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class OrderContext {
    /**
     * Property - state of order
     */
    private IOrderState state;

    /**
     * Create new object
     */
    public OrderContext() {
        state = null;
    }

    /**
     * Method to get the value field {@link OrderContext#state}
     *
     * @return Return state of order
     */
    public IOrderState getState() {
        return state;
    }

    /**
     * @param state new state of order
     */
    public void setState(IOrderState state) {
        this.state = state;
    }
}
