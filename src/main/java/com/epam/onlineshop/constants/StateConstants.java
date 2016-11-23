package com.epam.onlineshop.constants;

/**
 * The StateConstants class announces four states
 * that can accept the order throughout the life cycle.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class StateConstants {
    public static final String NOT_TREATED_STATE = "Не обрабатывается";
    public static final String PROCESSING_STATE = "В обработке";
    public static final String CANCELLATION_STATE = "Отменен";
    public static final String TREATED_STATE = "Обработан";
}
