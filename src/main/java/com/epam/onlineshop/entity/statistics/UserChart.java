package com.epam.onlineshop.entity.statistics;

/**
 * The UserChart It is the class responsible
 * for building chart with statistics for user.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class UserChart {
    /**
     * Property - type of sport nutrition
     */
    private String type;
    /**
     * Property - average price
     */
    private double averagePrice;

    /**
     * Create a new empty object
     *
     * @see UserChart#UserChart(String, double)
     */
    public UserChart() {

    }

    /**
     * It creates a new object with the specified values
     *
     * @param type         type of sport nutrition
     * @param averagePrice average price
     * @see UserChart#UserChart()
     */
    public UserChart(String type, double averagePrice) {
        this.type = type;
        this.averagePrice = averagePrice;
    }

    /**
     * Method to get the value field {@link UserChart#type}
     *
     * @return Return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Method to get the value field {@link UserChart#averagePrice}
     *
     * @return Return averagePrice
     */
    public double getAveragePrice() {
        return averagePrice;
    }

    /**
     * @param averagePrice new average price
     */
    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }
}
