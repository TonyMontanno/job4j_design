package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines()
                    .filter(line -> !line.trim().isEmpty() && !line.startsWith("#"))
                    .forEach(line -> {

                        if (!line.contains("=")) {
                            throw new IllegalArgumentException("Line must contain '=': " + line);
                        }

                        String[] parts = line.split("=", 2);

                        if (parts[0].trim().isEmpty()) {
                            throw new IllegalArgumentException("Key cannot be empty: " + line);
                        }
                        if (parts[1].trim().isEmpty()) {
                            throw new IllegalArgumentException("Value cannot be empty: " + line);
                        }

                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        values.put(key, value);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
