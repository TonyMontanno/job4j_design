package ru.job4j.regex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final Random random = new Random();
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> phrases = readPhrases();

        try (Scanner scanner = new Scanner(System.in)) {
            String userInput = scanner.nextLine();
            log.add(userInput);
            boolean isBotActive = true;

            while (!OUT.equals(userInput)) {
                if (STOP.equals(userInput)) {
                    isBotActive = false;
                } else if (CONTINUE.equals(userInput)) {
                    isBotActive = true;
                } else {
                    if (isBotActive) {
                        String currentPhrase = randomPhrase(phrases);
                        log.add(currentPhrase);
                        System.out.println(currentPhrase);
                    }
                }
                userInput = scanner.nextLine();
                log.add(userInput);
            }
            saveLog(log);
        }
    }

    private List<String> readPhrases() {
        List<String> phrasesList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            String phrase = reader.readLine();

            while (phrase != null) {
                if (!phrase.isEmpty()) {
                    phrasesList.add(phrase);
                }
                phrase = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return phrasesList;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            for (String line : log) {
                writer.println(line);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String randomPhrase(List<String> phrases) {
        if (phrases.isEmpty()) {
            throw new IllegalArgumentException("Список фраз пустой");
        }

        int index = random.nextInt(phrases.size());
        return phrases.get(index);
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/log.txt", "data/botAnswers.txt");
        consoleChat.run();
    }
}