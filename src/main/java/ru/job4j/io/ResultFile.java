package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        new java.io.File("data").mkdirs();

        try (FileOutputStream output = new FileOutputStream("data/dataresult.txt")) {

            output.write("×\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10\n".getBytes());

            for (int i = 1; i <= 10; i++) {
                StringBuilder line = new StringBuilder(i + "\t");
                for (int j = 1; j <= 10; j++) {
                    line.append(i * j).append("\t");
                }
                line.append("\n");
                output.write(line.toString().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}