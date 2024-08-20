package org.example;

import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.example.Diffoscope.diffoscopeMap;

public class DiffoscopeDiff implements Runnable {
    private static final Path DIFFOSCOPE = Constants.DIFFOSCOPE_EXECUTABLE;


    private final CSVRecord record;

    public DiffoscopeDiff(CSVRecord record) {
        this.record = record;

    }

    public void run() {
        Path class1 = Paths.get(record.get(0));
        Path class2 = Paths.get(record.get(1));

        ProcessBuilder pb = new ProcessBuilder(DIFFOSCOPE.toString(), class1.toString(), class2.toString());
        pb.directory(class1.getParent().getParent().toFile());


        String recordNumber = class1.getParent().getParent().getFileName().toString();
        try {
            Process p = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int exitCode = p.waitFor();
            StringBuilder sb = new StringBuilder();
            for (String line; (line = br.readLine()) != null; ) {
                sb.append(line).append("\n");
            }
            diffoscopeMap.put(recordNumber, Map.entry(String.valueOf(exitCode), sb.toString()));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
