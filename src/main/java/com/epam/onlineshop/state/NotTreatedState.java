package com.epam.onlineshop.state;

import com.epam.onlineshop.constants.StateConstants;
import com.epam.onlineshop.services.StateService;

/**
 * The NotTreatedState class implements IOrderState
 * and is responsible for the execution of operations corresponding to its state.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class NotTreatedState implements IOrderState {
    /**
     * @param context the context in which the action takes place
     * @param orderId Id order with which certain actions are performed
     */
    @Override
    public void doAction(OrderContext context, int orderId) {
        context.setState(this);
        StateService.changeOrderStatus(orderId, StateConstants.NOT_TREATED_STATE);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String with state of order.
     */
    public String toString() {
        return StateConstants.NOT_TREATED_STATE;
    }
}
