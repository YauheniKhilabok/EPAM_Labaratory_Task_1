package com.epam.onlineshop.entity.producers;

import com.epam.onlineshop.entity.DeepCloning;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Producers It is the class responsible for list of producers.
 * Implements Cloneable and Serializable.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class Producers implements Cloneable, Serializable {
    /**
     * Property - log, which need to logging code
     */
    private static final Logger log = Logger.getLogger(Producers.class);
    /**
     * Property - list of producers
     */
    private List<Producer> producers;

    /**
     * Create new empty object and initialize producers
     */
    public Producers() {
        producers = new ArrayList<>();
    }

    /**
     * Method to get the value field {@link Producers#producers}
     *
     * @return Return list of producer
     */
    public List<Producer> getProducers() {
        return producers;
    }

    /**
     * @param producers new list of producers
     */
    public void setProducers(List<Producer> producers) {
        this.producers = producers;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String with all fields of the class.
     */
    @Override
    public String toString() {
        String resultList = "Producers:" + '\n';
        for (Producer producer : producers) {
            resultList += producer.toString() + "\n";
        }
        return resultList;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return clone of object
     * @throws CloneNotSupportedException An exception occurs when you try to clone the object
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Producers copy = (Producers) super.clone();
        Object object = null;
        try {
            object = DeepCloning.getDeepCloning(copy);
        } catch (Exception e) {
            log.error(e);
        }
        return object;
    }
}
