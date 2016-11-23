package com.epam.onlineshop.entity.types;

import com.epam.onlineshop.constants.DefaultValueConstants;
import com.epam.onlineshop.entity.Entity;

import java.io.Serializable;

/**
 * The Type It is the class responsible for table types in database.
 * Implements Cloneable and Serializable.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class Type extends Entity implements Cloneable, Serializable {
    /**
     * Property - type of sport nutrition
     */
    private String type;
    /**
     * Property - description of description
     */
    private String typeDescription;

    /**
     * Create a new empty object
     *
     * @see Type#Type(int, String, String)
     */
    public Type() {
        super();
        this.type = DefaultValueConstants.DEFAULT_EMPTY_STRING;
        this.typeDescription = DefaultValueConstants.DEFAULT_EMPTY_STRING;
    }

    /**
     * It creates a new object with the specified values
     *
     * @param typeId          id of type
     * @param type            type of sport nutrition
     * @param typeDescription description of type
     * @see Type#Type()
     */
    public Type(int typeId, String type, String typeDescription) {
        super(typeId);
        this.type = type;
        this.typeDescription = typeDescription;
    }

    /**
     * Method to get the value field {@link Type#type}
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
     * Method to get the value field {@link Type#typeDescription}
     *
     * @return Return type description
     */
    public String getTypeDescription() {
        return typeDescription;
    }

    /**
     * @param typeDescription new type description
     */
    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return clone of object
     * @throws CloneNotSupportedException An exception occurs when you try to clone the object
     */
    @Override
    public Type clone() throws CloneNotSupportedException {
        return (Type) super.clone();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String with all fields of the class.
     */
    @Override
    public String toString() {
        return super.toString() + " Type: " + this.type + '\n' + " Type description: " + this.typeDescription + '\n';
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
        if (obj instanceof Type) {
            Type temp = (Type) obj;
            return this.getId() == temp.getId() &&
                    this.type.equals(temp.type) &&
                    this.typeDescription.equals(temp.typeDescription);
        } else return false;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return hash code of object
     */
    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + typeDescription.hashCode();
        return result;
    }
}
