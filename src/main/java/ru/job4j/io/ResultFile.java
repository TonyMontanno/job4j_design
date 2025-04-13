package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ResultFile {
    public static void main(String[] args) {
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("data/dataresult.txt")
                ))) {
            for (int i = 1; i <= 10; i++) {
                StringBuilder line = new StringBuilder(i + "\t");
                for (int j = 1; j <= 10; j++) {
                    line.append(i * j).append("\t");
                }
                line.append("\n");
                output.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}