package com.epam.onlineshop.entity.users;

import com.epam.onlineshop.entity.DeepCloning;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Users It is the class responsible for list of users.
 * Implements Cloneable and Serializable.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class Users implements Cloneable, Serializable {
    /**
     * Property - log, which need to logging code
     */
    private static final Logger log = Logger.getLogger(Users.class);
    /**
     * Property - list of users
     */
    private List<User> users;

    /**
     * Create new empty object and initialize users
     */
    public Users() {
        users = new ArrayList<>();
    }

    /**
     * Method to get the value field {@link Users#users}
     *
     * @return Return list of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users new list of users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return String with all fields of the class.
     */
    @Override
    public String toString() {
        String resultList = "Users:" + '\n';
        for (User user : users) {
            resultList += user.toString() + "\n";
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
        Users copy = (Users) super.clone();
        Object object = null;
        try {
            object = DeepCloning.getDeepCloning(copy);
        } catch (Exception e) {
            log.error(e);
        }
        return object;
    }
}
