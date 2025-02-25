package com.dovcharenko;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvParser {

    static int counter = 0; // Количество слов в .txt

    // Метод конвертирования списка слов в хеш мапу
    public static Map<String, Integer> convertWordsListToMapWithWordsCount(Stream<String> wordsStream) {
        Map<String, Integer> wordsWithCount = new HashMap<>();
        wordsStream.forEach(word -> {
            wordsWithCount.put(word, wordsWithCount.getOrDefault(word, 0) + 1);
            counter++;
        });
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
