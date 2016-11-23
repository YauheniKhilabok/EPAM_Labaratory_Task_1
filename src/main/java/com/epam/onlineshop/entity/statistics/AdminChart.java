package com.epam.onlineshop.entity.statistics;

/**
 * The AdminChart It is the class responsible
 * for building chart with statistics for admin.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class AdminChart {
    /**
     * Property - type of sport nutrition
     */
    private String type;
    /**
     * Property - count of type
     */
    private int count;

    /**
     * Create a new empty object
     *
     * @see AdminChart#AdminChart(String, int)
     */
    public AdminChart() {

    }

    /**
     * It creates a new object with the specified values
     *
     * @param type  type of sport nutrition
     * @param count count of type
     * @see AdminChart#AdminChart()
     */
    public AdminChart(String type, int count) {
        this.type = type;
        this.count = count;
    }

    /**
     * Method to get the value field {@link AdminChart#type}
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
     * Method to get the value field {@link AdminChart#count}
     *
     * @return Return count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count new count
     */
    public void setCount(int count) {
        this.count = count;
    }
}
