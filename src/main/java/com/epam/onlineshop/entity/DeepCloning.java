package com.epam.onlineshop.entity;

import java.io.*;

/**
 * The DeepCloning class responsible for a deep copy of objects.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class DeepCloning {
    /**
     * @param copy the source object to be copied
     * @return copy of object
     * @throws IOException            Signals that an I/O exception of some sort has occurred.
     *                                This class is the general class of exceptions produced by failed or interrupted I/O operations.
     * @throws ClassNotFoundException Thrown when an application tries to load in a class through its string name.
     */
    public static Object getDeepCloning(Object copy) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(copy);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();
    }
}
