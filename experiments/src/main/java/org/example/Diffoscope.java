package org.example;

import static org.example.Constants.ROOT;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class Diffoscope {

    public static void main(String[] args) throws IOException, InterruptedException {
        Path paths = ROOT.resolve("paths.csv");
        Reader reader = new FileReader(paths.toFile());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);

        int workers;
        if (args.length == 0) {
            System.out.printf(
                    "Are you sure you want to use %d cores? [y/n]%n",
                    Runtime.getRuntime().availableProcessors());
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();

            if ("y".equals(answer)) {
                workers = Runtime.getRuntime().availableProcessors();
            } else {
                System.out.println("Enter number of cores: ");
                workers = Integer.parseInt(scanner.nextLine());
            }
        } else {
            workers = Integer.parseInt(args[0]);
        }
        ExecutorService pool = Executors.newFixedThreadPool(workers);

        for (CSVRecord record : records) {
            pool.submit(new DiffoscopeDiff(record));
        }
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.HOURS);
    }
}
