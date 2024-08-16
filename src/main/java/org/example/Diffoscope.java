package org.example;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;
import java.io.FileWriter;
import java.io.FileReader;
import java.lang.InterruptedException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import static org.example.Main.ROOT;

public class Diffoscope {
    private static final Path DIFFOSCOPE = Paths.get("/home/aman/Desktop/personal/diffoscope/bin/diffoscope");
    private static final Logger logger;

    static {
        logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler(ROOT.resolve("diffoscope.log").toString());
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Path datasetEQ = ROOT.resolve("paths.csv");
        Reader reader = new FileReader(datasetEQ.toFile());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);


        FileWriter f0 = new FileWriter(ROOT.resolve("diffoscope.csv").toString());
        String newLine = System.getProperty("line.separator");
        for (CSVRecord record : records) {
            Path class1 = Paths.get(record.get(0));
            Path class2 = Paths.get(record.get(1));
           
            ProcessBuilder pb = new ProcessBuilder(DIFFOSCOPE.toString(), "--text", "diffoscope.diff", class1.toString(), class2.toString());
            pb.directory(class1.getParent().getParent().toFile());

            String recordNumber = class1.getParent().getParent().getFileName().toString();
            Process p = pb.start();
            int exitCode = p.waitFor();

            f0.write(recordNumber + "," + exitCode + newLine);
            logger.info("Record: " + recordNumber + " Exit Code: " + exitCode);

        }
        f0.close();
    }

}
