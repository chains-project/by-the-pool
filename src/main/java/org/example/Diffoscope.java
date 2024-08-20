package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.example.Constants.ROOT;

public class Diffoscope {

    public static final Map<String, Map.Entry<String, String>> diffoscopeMap = new ConcurrentHashMap<>();


    public static void main(String[] args) throws IOException, InterruptedException {
        Path paths = ROOT.resolve("try.csv");
        Reader reader = new FileReader(paths.toFile());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);


        int workers = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(workers);

        for (CSVRecord record : records) {
            pool.submit(new DiffoscopeDiff(record));
        }
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.HOURS);

        for (Map.Entry<String, Map.Entry<String, String>> entry : diffoscopeMap.entrySet()) {
            Files.writeString(ROOT.resolve("output").resolve(entry.getKey()).resolve("diffoscope.diff"), entry.getValue().getValue());
            Files.writeString(ROOT.resolve("output").resolve(entry.getKey()).resolve(".exitcode"), entry.getValue().getKey());
        }
    }
}
