package com.epam.onlineshop.entity.statistics;

import java.io.Serializable;

/**
 * The Statistics It is the class responsible for table statistics in database.
 * Implements Cloneable and Serializable.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class Statistics implements Cloneable, Serializable {
    /**
     * Property - id of period
     */
    private int periodId;
    /**
     * Property - beginning of report period
     */
    private String beginningReportPeriod;
    /**
     * Property - end of report period
     */
    private String endReportPeriod;
    /**
     * Property - amount of purchased goods
     */
    private double amountPurchasedGoods;
    /**
     * Property - consumption
     */
    private double consumption;
    /**
     * Property - amount of sold goods
     */
    private double amountSoldGoods;
    /**
     * Property - income
     */
    private double income;
    /**
     * Property - profit
     */
    private double profit;
    /**
     * Property - status
     */
    private String status;

    /**
     * Create a new empty object
     *
     * @see Statistics#Statistics(int, String, String, double, double, double, double, double, String)
     */
    public Statistics() {

    }

    /**
     * It creates a new object with the specified values
     *
     * @param periodId              id of period
     * @param beginningReportPeriod date of begin report period
     * @param endReportPeriod       date of end report period
     * @param amountPurchasedGoods  amount of purchased goods
     * @param consumption           consumption
     * @param amountSoldGoods       amount of sold goods
     * @param income                income from period
     * @param profit                profit from period
     * @param status                status of period
     * @see Statistics#Statistics()
     */
    public Statistics(int periodId, String beginningReportPeriod, String endReportPeriod, double amountPurchasedGoods,
                      double consumption, double amountSoldGoods, double income, double profit, String status) {
        this.periodId = periodId;
        this.beginningReportPeriod = beginningReportPeriod;
        this.endReportPeriod = endReportPeriod;
        this.amountPurchasedGoods = amountPurchasedGoods;
        this.consumption = consumption;
        this.amountSoldGoods = amountSoldGoods;
        this.income = income;
        this.profit = profit;
        this.status = status;
    }

    /**
     * Method to get the value field {@link Statistics#periodId}
     *
     * @return Return period id
     */
    public int getPeriodId() {
        return periodId;
    }

    /**
     * @param periodId new period id
     */
    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }

    /**
     * Method to get the value field {@link Statistics#beginningReportPeriod}
     *
     * @return Return beginning of report period
     */
    public String getBeginningReportPeriod() {
        return beginningReportPeriod;
    }

    /**
     * @param beginningReportPeriod new beginning report period
     */
    public void setBeginningReportPeriod(String beginningReportPeriod) {
        this.beginningReportPeriod = beginningReportPeriod;
    }

    /**
     * Method to get the value field {@link Statistics#endReportPeriod}
     *
     * @return Return end of report period
     */
    public String getEndReportPeriod() {
        return endReportPeriod;
    }

    /**
     * @param endReportPeriod new end of report period
     */
    public void setEndReportPeriod(String endReportPeriod) {
        this.endReportPeriod = endReportPeriod;
    }

    /**
     * Method to get the value field {@link Statistics#amountPurchasedGoods}
     *
     * @return Return amountPurchasedGoods
     */
    public double getAmountPurchasedGoods() {
        return amountPurchasedGoods;
    }

    /**
     * @param amountPurchasedGoods new amount of purchased goods
     */
    public void setAmountPurchasedGoods(double amountPurchasedGoods) {
        this.amountPurchasedGoods = amountPurchasedGoods;
    }

    /**
     * Method to get the value field {@link Statistics#consumption}
     *
     * @return Return consumption
     */
    public double getConsumption() {
        return consumption;
    }

    /**
     * @param consumption new consumption
     */
    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    /**
     * Method to get the value field {@link Statistics#amountSoldGoods}
     *
     * @return Return amountSoldGoods
     */
    public double getAmountSoldGoods() {
        return amountSoldGoods;
    }

    /**
     * @param amountSoldGoods new amount of sold goods
     */
    public void setAmountSoldGoods(double amountSoldGoods) {
        this.amountSoldGoods = amountSoldGoods;
    }

    /**
     * Method to get the value field {@link Statistics#income}
     *
     * @return Return income
     */
    public double getIncome() {
        return income;
    }

    /**
     * @param income new income
     */
    public void setIncome(double income) {
        this.income = income;
    }

    /**
     * Method to get the value field {@link Statistics#profit}
     *
     * @return Return profit
     */
    public double getProfit() {
        return profit;
    }

    /**
     * @param profit new profit
     */
    public void setProfit(double profit) {
        this.profit = profit;
    }

    /**
     * Method to get the value field {@link Statistics#status}
     *
     * @return Return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return clone of object
     * @throws CloneNotSupportedException An exception occurs when you try to clone the object
     */
    @Override
    public Statistics clone() throws CloneNotSupportedException {
        return (Statistics) super.clone();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String with all fields of the class.
     */
    @Override
    public String toString() {
        return " Period ID: " + this.periodId + '\n' + " Beginning report period: " + this.beginningReportPeriod + '\n' +
                " End report period: " + this.endReportPeriod + '\n' + " Amount purchased goods: " + this.amountPurchasedGoods + '\n' +
                " Consumption: " + this.consumption + '\n' + " Amount sold goods: " + this.amountSoldGoods + '\n' +
                " Income: " + this.income + '\n' + " Profit: " + this.profit + '\n' + " Status: " + this.status + '\n';
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
        if (obj instanceof Statistics) {
            Statistics temp = (Statistics) obj;
            return this.periodId == temp.periodId &&
                    this.beginningReportPeriod.equals(temp.beginningReportPeriod) &&
                    this.endReportPeriod.equals(temp.endReportPeriod) &&
                    this.amountPurchasedGoods == temp.amountPurchasedGoods &&
                    this.consumption == temp.consumption &&
                    this.amountSoldGoods == temp.amountSoldGoods &&
                    this.income == temp.income &&
                    this.profit == temp.profit &&
                    this.status.equals(temp.status);
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
        result = periodId;
        result = 31 * result + beginningReportPeriod.hashCode();
        result = 31 * result + endReportPeriod.hashCode();
        temp = Double.doubleToLongBits(amountPurchasedGoods);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(consumption);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(amountSoldGoods);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(income);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(profit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + status.hashCode();
        return result;
    }
}
