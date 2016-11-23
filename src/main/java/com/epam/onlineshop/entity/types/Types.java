package com.epam.onlineshop.entity.types;

import com.epam.onlineshop.entity.DeepCloning;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Types It is the class responsible for list of types.
 * Implements Cloneable and Serializable.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class Types implements Cloneable, Serializable {
    /**
     * Property - log, which need to logging code
     */
    private static final Logger log = Logger.getLogger(Types.class);
    /**
     * Property - list of types
     */
    private List<Type> types;

    /**
     * Create new empty object and initialize types
     */
    public Types() {
        types = new ArrayList<>();
    }

    /**
     * Method to get the value field {@link Types#types}
     *
     * @return Return list of types
     */
    public List<Type> getTypes() {
        return types;
    }

    /**
     * @param types new list of types
     */
    public void setTypes(List<Type> types) {
        this.types = types;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String with all fields of the class.
     */
    @Override
    public String toString() {
        String resultList = "Types:" + '\n';
        for (Type type : types) {
            resultList += type.toString() + "\n";
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
        Types copy = (Types) super.clone();
        Object object = null;
        try {
            object = DeepCloning.getDeepCloning(copy);
        } catch (Exception e) {
            log.error(e);
        }
        return object;
    }
}
