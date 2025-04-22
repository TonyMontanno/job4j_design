package ru.job4j.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> filesMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(attrs.size(), file.getFileName().toString());
        filesMap.computeIfAbsent(fileProperty, k -> new ArrayList<>()).add(file.toAbsolutePath());
        return FileVisitResult.CONTINUE;
    }

    public void printDuplicates() {
        for (Map.Entry<FileProperty, List<Path>> entry : filesMap.entrySet()) {
            List<Path> pathList = entry.getValue();
            if (pathList.size() > 1) {
                System.out.println(entry.getKey().getName() + " - " + entry.getKey().getSize() + " bytes");
                for (Path path : pathList) {
                    System.out.println("    " + path);
                }
            }
        }
    }
}