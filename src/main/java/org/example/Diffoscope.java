package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.example.Constants.ROOT;

public class Diffoscope {

    public static void main(String[] args) throws IOException, InterruptedException {
        Path paths = ROOT.resolve("paths.csv");
        Reader reader = new FileReader(paths.toFile());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);


        int workers = args.length > 0 ? Integer.parseInt(args[0]) : Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(workers);

        for (CSVRecord record : records) {
            pool.submit(new DiffoscopeDiff(record));
        }
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.HOURS);
    }
}
