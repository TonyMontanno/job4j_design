package ru.job4j.io;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {

        String path = argsName.get("path");
        File file = new File(path);
        String delimiter = argsName.get("delimiter");
        String[] filters = argsName.get("filter").split(",");
        String out = argsName.get("out");

        try (Scanner scanner = new Scanner(file);
             PrintStream output = "stdout".equals(out) ? System.out : new PrintStream(out)) {

            String[] headers = scanner.nextLine().split(delimiter);

            List<Integer> filterIndexes = new ArrayList<>();
            for (String columName : filters) {
                for (int i = 0; i < headers.length; i++) {
                    if (headers[i].equals(columName)) {
                        filterIndexes.add(i);
                        break;
                    }
                }
            }

            StringJoiner headerJoiner = new StringJoiner(delimiter);
            for (int index : filterIndexes) {
                headerJoiner.add(headers[index]);
            }
            output.println(headerJoiner);

            while (scanner.hasNextLine()) {
                String[] row = scanner.nextLine().split(delimiter);
                StringJoiner lineJoiner = new StringJoiner(delimiter);
                for (int index : filterIndexes) {
                    lineJoiner.add(row[index]);
                }
                output.println(lineJoiner);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        if (args.length != 4) {
            throw new IllegalArgumentException("Передано неверное количество аргументов. Требуется 4 параметра");
        }
        ArgsName argsName = ArgsName.of(args);
        String path = argsName.get("path");

        if (path.isBlank()) {
            throw new IllegalArgumentException("Путь не должен быть пустым");
        }

        if (!path.endsWith(".csv")) {
            throw new IllegalArgumentException("Файл должен иметь расширение .csv.");
        }

        String delimiter = argsName.get("delimiter");
        if (!delimiter.equals(";")) {
            throw new IllegalArgumentException("Разделитель должен быть ';'");
        }

        String out = argsName.get("out");
        if (out.isBlank()) {
            throw new IllegalArgumentException("Out параметр не может быть пустым");
        }

        String filter = argsName.get("filter");
        String[] columns = filter.split(",");

        if (columns.length == 0) {
            throw new IllegalArgumentException("Фильтр должен содержать хотя бы один столбец");
        }

        handle(argsName);
    }
}