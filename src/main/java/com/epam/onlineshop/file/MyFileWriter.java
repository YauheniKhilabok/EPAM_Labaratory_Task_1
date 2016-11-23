package com.epam.onlineshop.file;

import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The MyFileWriter class is responsible for write data in the file.
 *
 * @author Evgeniy Khilabok
 * @version 1.0
 * @since 2016-09-01
 */
public class MyFileWriter {
    /**
     * Property - log, which is made using logging code.
     * Should be private static final because
     * not to be changed throughout the course of the program.
     *
     * @see Logger
     */
    private static final Logger log = Logger.getLogger(MyFileWriter.class);

    /**
     * Method need for write data in the file
     *
     * @param path      the path to the file, which will be used to write
     * @param data      the data to be written to the file
     * @param writeFlag flag responsible for the fact that there will be overwritten
     *                  or the data file will be appended to the end of the file
     * @return return true if the data is recorded and false otherwise
     */
    public static boolean writeInFile(String path, String data, boolean writeFlag) {
        boolean flag;
        try {
            FileWriter writer = new FileWriter(path, writeFlag);
            writer.write(data);
            writer.flush();
            flag = true;
        } catch (IOException e) {
            flag = false;
            log.error(e);
        }
        return flag;
    }
}
