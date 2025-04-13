package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> list;
        try (BufferedReader input = new BufferedReader(new FileReader("data/log.txt"))) {
            list = input.lines()
                    .filter(s -> {
                        String[] parts = s.split(" ");
                        return parts.length >= 2 && "404".equals(parts[parts.length - 2]);
                    })
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);

    }
}
