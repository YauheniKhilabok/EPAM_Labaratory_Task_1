package com.epam.onlineshop.file;

import com.epam.onlineshop.constants.DefaultValueConstants;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * The MyFileReader class is responsible for reading data from a file.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class MyFileReader {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(MyFileReader.class);

    /**
     * Method need for read data from file
     *
     * @param path the path to the file, which will be used to read
     * @return String data from file
     */
    public static String readFromFile(String path) {
        File file = new File(path);
        String text = DefaultValueConstants.DEFAULT_EMPTY_STRING;
        try {
            FileReader reader = new FileReader(file);
            char[] buffer = new char[(int) file.length()];
            reader.read(buffer);
            text = new String(buffer);
        } catch (IOException e) {
            log.error(e);
        }
        return text;
    }
}
