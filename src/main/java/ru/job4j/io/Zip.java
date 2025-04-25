package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) throws IOException {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                String zipEntryName = source.toString();
                zip.putNextEntry(new ZipEntry(zipEntryName));
                try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(input.readAllBytes());
                }
            }
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );

        ArgsName arguments = ArgsName.of(args);
        validate(arguments);

        Path sourceDir = Paths.get(arguments.get("d"));
        String excludeExt = arguments.get("e");
        File target = new File(arguments.get("o"));

        List<Path> sources = Search.search(sourceDir, path -> !path.toFile().getName().endsWith(excludeExt));

        Zip zip2 = new Zip();
        zip2.packFiles(sources, target);
    }

    private static void validate(ArgsName arguments) {
        Path directory = Paths.get(arguments.get("d"));

        if (!Files.exists(directory) || !Files.isDirectory(directory)) {
            throw new IllegalArgumentException("Указанная директория '" + directory + "' не существует или не является директорией.");
        }

        String exclude = arguments.get("e");
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException("Расширение для исключения должно начинаться с точки: '" + exclude + "'");
        }

        String output = arguments.get("o");
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException("Имя выходного файла должно заканчиваться на '.zip': '" + output + "'");
        }
    }
}