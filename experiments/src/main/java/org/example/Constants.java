package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Constants {
    public static final Properties PROPERTIES;

    static {
        PROPERTIES = new Properties();
        try {
            // load a properties file from class path, inside static method
            PROPERTIES.load(Constants.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static final Path ROOT =
            Paths.get(PROPERTIES.getProperty("ROOT")).toAbsolutePath().normalize();
    public static final Path DIFFOSCOPE_EXECUTABLE = Paths.get(PROPERTIES.getProperty("DIFFOSCOPE_EXECUTABLE"));

    public static Logger getLogger(Class<?> clazz, String logFileName) {
        try {
            Logger logger = Logger.getLogger(clazz.getName());
            FileHandler fh = new FileHandler(ROOT.resolve(logFileName).toString());
            fh.setFormatter(new SimpleFormatter());
            logger.setLevel(Level.ALL);
            logger.addHandler(fh);
            return logger;
        } catch (Exception e) {
            throw new RuntimeException("Error creating logger", e);
        }
    }

    public static FileWriter getFileForFurtherProcessing(String fileName) {
        try {
            return new FileWriter(ROOT.resolve(fileName).toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
