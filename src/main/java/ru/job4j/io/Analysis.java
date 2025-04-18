package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String> periods = new ArrayList<>();
        String start = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ", 2);
                String status = parts[0];
                String time = parts[1];

                if ("400".equals(status) || "500".equals(status)) {
                    if (start == null) {
                        start = time;
                    }
                } else {
                    if (start != null) {
                        periods.add(start + ";" + time + ";");
                        start = null;
                    }
                }
            }
            if (start != null) {
                periods.add(start + ";");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(target))) {
            for (String line : periods) {
                writer.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}