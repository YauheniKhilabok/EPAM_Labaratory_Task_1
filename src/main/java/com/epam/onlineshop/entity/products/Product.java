package com.epam.onlineshop.entity.products;

import com.epam.onlineshop.constants.DefaultValueConstants;
import com.epam.onlineshop.entity.Entity;
import com.epam.onlineshop.entity.producers.Producer;
import com.epam.onlineshop.entity.types.Type;

import java.io.Serializable;

/**
 * The Product It is the class responsible for table products in database.
 * Implements Cloneable and Serializable.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class Product extends Entity implements Cloneable, Serializable {
    /**
     * Property - object of Type
     */
    private Type type;
    /**
     * Property - object of Producer
     */
    private Producer producer;
    /**
     * Property - date of delivery
     */
    private String dateOfDelivery;
    /**
     * Property - shelf life
     */
    private String shelfLife;
    /**
     * Property - name of product
     */
    private String nameProduct;
    /**
     * Property - number of packages
     */
    private int numberOfPackages;
    /**
     * Property - unit
     */
    private String unit;
    /**
     * Property - number per unit
     */
    private double numberPerUnit;
    /**
     * Property - taste
     */
    private String taste;
    /**
     * Property - discounts
     */
    private int discounts;
    /**
     * Property - purchase price
     */
    private double purchasePrice;
    /**
     * Property - status
     */
    private String status;
    /**
     * Property - product description
     */
    private String productDescription;
    /**
     * Property - path to image with product
     */
    private String pathToImage;

    /**
     * Create a new empty object
     *
     * @see Product#Product(int, Type, Producer, String, String, String, int, String, double, String, int, double, String, String, String)
     */
    public Product() {
        super();
        this.type = new Type();
        this.producer = new Producer();
        this.dateOfDelivery = DefaultValueConstants.DEFAULT_EMPTY_STRING;
        this.shelfLife = DefaultValueConstants.DEFAULT_EMPTY_STRING;
        this.nameProduct = DefaultValueConstants.DEFAULT_EMPTY_STRING;
        this.numberOfPackages = DefaultValueConstants.START_INDEX;
        this.unit = DefaultValueConstants.DEFAULT_EMPTY_STRING;
        this.numberPerUnit = DefaultValueConstants.DEFAULT_DOUBLE_VALUE;
        this.taste = DefaultValueConstants.DEFAULT_EMPTY_STRING;
        this.discounts = DefaultValueConstants.START_INDEX;
        this.purchasePrice = DefaultValueConstants.DEFAULT_DOUBLE_VALUE;
        this.status = DefaultValueConstants.DEFAULT_PRODUCT_STATUS;
        this.productDescription = DefaultValueConstants.DEFAULT_PRODUCT_DESCRIPTION;
        this.pathToImage = DefaultValueConstants.DEFAULT_EMPTY_STRING;
    }

    /**
     * It creates a new object with the specified values
     *
     * @param productId          unique id of entity
     * @param type               type of sport nutrition
     * @param producer           producer of product
     * @param dateOfDelivery     date of delivery
     * @param shelfLife          shelf life
     * @param nameProduct        name of product
     * @param numberOfPackages   number of packages
     * @param unit               unit
     * @param numberPerUnit      number per unit
     * @param taste              taste
     * @param discounts          discounts on product
     * @param purchasePrice      purchase price
     * @param status             status of product
     * @param productDescription product description
     * @param pathToImage        path to image
     * @see Product#Product()
     */
    public Product(int productId, Type type, Producer producer, String dateOfDelivery, String shelfLife,
                   String nameProduct, int numberOfPackages, String unit, double numberPerUnit, String taste,
                   int discounts, double purchasePrice, String status, String productDescription, String pathToImage) {
        super(productId);
        this.type = type;
        this.producer = producer;
        this.type = type;
        this.dateOfDelivery = dateOfDelivery;
        this.shelfLife = shelfLife;
        this.nameProduct = nameProduct;
        this.numberOfPackages = numberOfPackages;
        this.unit = unit;
        this.numberPerUnit = numberPerUnit;
        this.taste = taste;
        this.discounts = discounts;
        this.purchasePrice = purchasePrice;
        this.status = status;
        this.productDescription = productDescription;
        this.pathToImage = pathToImage;
    }

    /**
     * Method to get the value field {@link Product#type}
     *
     * @return Return type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type new type of sport nutrition
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Method to get the value field {@link Product#producer}
     *
     * @return Return type
     */
    public Producer getProducer() {
        return producer;
    }

    /**
     * @param producer new producer
     */
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    /**
     * Method to get the value field {@link Product#dateOfDelivery}
     *
     * @return Return date of delivery
     */
    public String getDateOfDelivery() {
        return dateOfDelivery;
    }

    /**
     * @param dateOfDelivery new date of delivery
     */
    public void setDateOfDelivery(String dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    /**
     * Method to get the value field {@link Product#shelfLife}
     *
     * @return Return shelf life
     */
    public String getShelfLife() {
        return shelfLife;
    }

    /**
     * @param shelfLife new shelf life
     */
    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    /**
     * Method to get the value field {@link Product#nameProduct}
     *
     * @return Return name of product
     */
    public String getNameProduct() {
        return nameProduct;
    }

    /**
     * @param nameProduct new name of product
     */
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    /**
     * Method to get the value field {@link Product#numberOfPackages}
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
     * Method to get the value field {@link Product#unit}
     *
     * @return Return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit new unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Method to get the value field {@link Product#numberPerUnit}
     *
     * @return Return number per unit
     */
    public double getNumberPerUnit() {
        return numberPerUnit;
    }

    /**
     * @param numberPerUnit new number per unit
     */
    public void setNumberPerUnit(double numberPerUnit) {
        this.numberPerUnit = numberPerUnit;
    }

    /**
     * Method to get the value field {@link Product#taste}
     *
     * @return Return taste
     */
    public String getTaste() {
        return taste;
    }

    /**
     * @param taste new taste
     */
    public void setTaste(String taste) {
        this.taste = taste;
    }

    /**
     * Method to get the value field {@link Product#discounts}
     *
     * @return Return discounts
     */
    public int getDiscounts() {
        return discounts;
    }

    /**
     * @param discounts new discounts
     */
    public void setDiscounts(int discounts) {
        this.discounts = discounts;
    }

    /**
     * Method to get the value field {@link Product#purchasePrice}
     *
     * @return Return purchase price
     */
    public double getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * @param purchasePrice new purchase price
     */
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * Method to get the value field {@link Product#status}
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
     * Method to get the value field {@link Product#productDescription}
     *
     * @return Return status
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * @param productDescription new product description
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * Method to get the value field {@link Product#pathToImage}
     *
     * @return Return path to image
     */
    public String getPathToImage() {
        return pathToImage;
    }

    /**
     * @param pathToImage new path to image
     */
    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return clone of object
     * @throws CloneNotSupportedException An exception occurs when you try to clone the object
     */
    @Override
    public Product clone() throws CloneNotSupportedException {
        Product product = (Product) super.clone();
        product.type = type.clone();
        product.producer = producer.clone();
        return product;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String with all fields of the class.
     */
    @Override
    public String toString() {
        return super.toString() + " Producer: " + this.producer.toString() + '\n' + " Type: " + this.type.toString() + '\n' +
                " Date of delivery: " + this.dateOfDelivery + '\n' + " Shelf life: " + this.shelfLife + '\n' +
                " Product name: " + this.nameProduct + '\n' + " Number of packages: " + this.numberOfPackages + " pcs" + '\n' +
                " Unit: " + this.unit + '\n' + " Number per unit: " + this.numberPerUnit + '\n' + " Taste: " + this.taste + '\n' +
                " Discounts: " + this.discounts + "%" + '\n' + " Purchase price: " + this.purchasePrice + " rubles" + '\n' +
                " Status: " + this.status + '\n' + " Product description: " + this.productDescription + '\n' +
                " Path to image: " + this.pathToImage + '\n';
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
        if (obj instanceof Product) {
            Product temp = (Product) obj;
            return this.getId() == temp.getId() &&
                    this.producer.equals(temp.producer) &&
                    this.type.equals(temp.type) &&
                    this.dateOfDelivery.equals(temp.dateOfDelivery) &&
                    this.shelfLife.equals(temp.shelfLife) &&
                    this.nameProduct.equals(temp.nameProduct) &&
                    this.numberOfPackages == temp.numberOfPackages &&
                    this.unit.equals(temp.unit) &&
                    this.numberPerUnit == temp.numberPerUnit &&
                    this.taste.equals(temp.taste) &&
                    this.discounts == temp.discounts &&
                    this.purchasePrice == temp.purchasePrice &&
                    this.status.equals(temp.status) &&
                    this.productDescription.equals(temp.productDescription) &&
                    this.pathToImage.equals(temp.pathToImage);

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
        result = producer.getId();
        result = 31 * result + type.getId();
        result = 31 * result + nameProduct.hashCode();
        return result;
    }
}
