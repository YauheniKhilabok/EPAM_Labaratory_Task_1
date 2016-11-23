package com.epam.onlineshop.entity.reviews;

import com.epam.onlineshop.entity.DeepCloning;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Reviews It is the class responsible for list of reviews.
 * Implements Cloneable and Serializable.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class Reviews implements Cloneable, Serializable {
    /**
     * Property - log, which need to logging code
     */
    private static final Logger log = Logger.getLogger(Reviews.class);
    /**
     * Property - list of reviews
     */
    private List<Comment> reviews;

    /**
     * Create new empty object and initialize reviews
     */
    public Reviews() {
        reviews = new ArrayList<>();
    }

    /**
     * Method to get the value field {@link Reviews#reviews}
     *
     * @return Return list of reviews
     */
    public List<Comment> getReviews() {
        return reviews;
    }

    /**
     * @param reviews new list of reviews
     */
    public void setReviews(List<Comment> reviews) {
        this.reviews = reviews;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String with all fields of the class.
     */
    @Override
    public String toString() {
        String resultList = "Reviews:" + '\n';
        for (Comment comment : reviews) {
            resultList += comment.toString() + "\n";
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
        Reviews copy = (Reviews) super.clone();
        Object object = null;
        try {
            object = DeepCloning.getDeepCloning(copy);
        } catch (Exception e) {
            log.error(e);
        }
        return object;
    }
}
