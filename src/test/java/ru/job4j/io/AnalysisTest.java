package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {

    @Test
    void whenUnavailableWithFirstData(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("300 10:59:01");
            output.println("500 11:01:02");
            output.println("200 11:02:02");
        }

        File target = tempDir.resolve("target.txt").toFile();

        Analysis analysis = new Analysis();
        analysis.unavailable(source.toString(), target.toString());

        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(line -> result.append(line).append("\n"));
        }

        String expected = "10:57:01;10:59:01;\n11:01:02;11:02:02;\n";
        assertThat(result.toString()).isEqualTo(expected);
    }

    @Test
    void whenUnavailableWithSecondData(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("500 10:59:01");
            output.println("400 11:01:02");
            output.println("300 11:02:02");
        }

        File target = tempDir.resolve("target.txt").toFile();

        Analysis analysis = new Analysis();
        analysis.unavailable(source.toString(), target.toString());

        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }

        String expected = "10:57:01;11:02:02;";
        assertThat(result.toString()).isEqualTo(expected);
    }

    @Test
    void whenServerIsUnavailableUntilEnd(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("500 10:59:01");
            output.println("400 11:01:02");
            output.println("500 11:02:02");
        }

        File target = tempDir.resolve("target.txt").toFile();

        Analysis analysis = new Analysis();
        analysis.unavailable(source.toString(), target.toString());

        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }

        String expected = "10:57:01;";
        assertThat(result.toString()).isEqualTo(expected);
    }
}