package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArgs(args);

        Path start = Paths.get(args[0]);
        String extension = args[1];

        search(start, path -> path.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validateArgs(String[] args) {
        if (args.length < 2) {
            throw new IllegalStateException("Аргументов должно быть два");
        }

        Path startPath = Paths.get(args[0]);
        if (!Files.exists(startPath) || !Files.isDirectory(startPath)) {
            throw new IllegalStateException("Первый аргумент должен быть существующей директорией");
        }

        String extension = args[1];
        if (!extension.startsWith(".") || !extension.matches("\\.[a-zA-Z0-9]+")) {
            throw new IllegalStateException("Второй аргумент должен быть расширением файла, начинающимся с точки.");

        }
    }
}