package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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

    public void saveTo(String out) {
        var data = filter();

        try (PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {

            for (String line : data) {
                output.println(line);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}
