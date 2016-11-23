package com.epam.onlineshop.entity;

import com.epam.onlineshop.constants.DefaultValueConstants;

import java.io.Serializable;

/**
 * The Entity It is the base class for all classes of entities.
 * Implements Cloneable and Serializable.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public abstract class Entity implements Cloneable, Serializable {
    /**
     * Property - id
     */
    private int id;

    /**
     * Create a new empty object
     *
     * @see Entity#Entity(int)
     */
    public Entity() {
        this.id = DefaultValueConstants.DEFAULT_ID;
    }

    /**
     * It creates a new object with the specified values
     *
     * @param id - unique id for entity
     * @see Entity#Entity()
     */
    public Entity(int id) {
        this.id = id;
    }

    /**
     * Method to get the value field {@link Entity#id}
     *
     * @return Return id of entity
     */
    public int getId() {
        return id;
    }

    /**
     * @param id new id of entity
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return clone of object
     * @throws CloneNotSupportedException An exception occurs when you try to clone the object
     */
    @Override
    public Entity clone() throws CloneNotSupportedException {
        return (Entity) super.clone();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String with all fields of the class.
     */
    @Override
    public String toString() {
        return " ID: " + this.id + '\n';
    }
}
