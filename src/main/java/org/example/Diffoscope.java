package org.example;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.io.FileWriter;
import java.io.FileReader;
import java.lang.InterruptedException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import static org.example.Constants.ROOT;

public class Diffoscope {
    public static final FileWriter f0 = Constants.getFileForFurtherProcessing("diffoscope.csv");

    public static void main(String[] args) throws IOException, InterruptedException {
        Path paths = ROOT.resolve("paths.csv");
        Reader reader = new FileReader(paths.toFile());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);


        int workers = Runtime.getRuntime().availableProcessors();
        ExecutorService pool= Executors.newFixedThreadPool(workers);

        for (CSVRecord record : records) {
            pool.submit(new DiffoscopeDiff(record));

        }
        f0.close();
    }

}
