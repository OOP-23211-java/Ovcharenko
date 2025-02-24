package com.dovcharenko;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvParser {

    static int counter = 0; // Количество слов в .txt

    // Метод конвертирования списка строк в список слов
    public static List<String> convertTextLinesToWordsList(List<String> lines) {
        List<String> words = new ArrayList<>();

        for (String line : lines) {
            String cleanedLine = line.replaceAll("[^a-zA-Zа-яА-ЯёЁ0-9\\s]", " ");
            String trimmed = cleanedLine.trim();
            String[] splitWords = trimmed.split("\\s+");
            for (String word : splitWords) {
                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
        }

        return words;
    }

    // Метод конвертирования списка слов в хеш мапу
    public static Map<String, Integer> convertWordsListToMapWithWordsCount(List<String> words) {
        Map<String, Integer> wordsWithCount = new HashMap<>();
        for (String word : words) {
            if (wordsWithCount.containsKey(word)) {
                wordsWithCount.put(word, wordsWithCount.get(word) + 1);
            } else {
                wordsWithCount.put(word, 1);
            }
            counter++;
        }
        return wordsWithCount;
    }

    // Сортировка
    public static Map<String, Integer> sortByValueDescending(Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue())) // Сортировка по убыванию
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    // Создание csv файла
    public static void createCsvFile(Map<String, Integer> wordCountMap, int totalWords, String outputFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.println("Слово, Частота, Частота (%)");

            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                String word = entry.getKey();
                int frequency = entry.getValue();
                double percentage = (frequency * 100.0) / totalWords;

                writer.printf("%s, %d, %.2f%%%n", word, frequency, percentage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
