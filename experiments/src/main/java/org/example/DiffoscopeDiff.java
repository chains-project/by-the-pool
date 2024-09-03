package org.example;

import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class DiffoscopeDiff implements Runnable {
    private static final Path DIFFOSCOPE = Constants.DIFFOSCOPE_EXECUTABLE;

    private static final Logger logger = Constants.getLogger(DiffoscopeDiff.class, "diffoscope.log");

    private final CSVRecord record;

    public DiffoscopeDiff(CSVRecord record) {
        this.record = record;
    }

    public void run() {
        Path class1 = Paths.get(record.get(0));
        Path class2 = Paths.get(record.get(1));

        Path diffRootDirectory = class1.getParent().getParent();

        ProcessBuilder pb = new ProcessBuilder(DIFFOSCOPE.toString(), "--json", "diffoscope.json", diffRootDirectory.relativize(class1).toString(), diffRootDirectory.relativize(class2).toString());
        pb.directory(diffRootDirectory.toFile());

        String recordNumber = class1.getParent().getParent().getFileName().toString();
        try {
            Process p = pb.start();
            int exitCode = p.waitFor();
            logger.info("Record: " + recordNumber + " Exit Code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
