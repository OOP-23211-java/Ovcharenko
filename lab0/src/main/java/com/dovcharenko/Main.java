package com.dovcharenko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Укажите имя файла в аргументах программы");
            return;
        }

        String fileName = args[0];
        List<String> strings = readFileFromResources(fileName);
        if (strings == null || strings.isEmpty()) {
            System.out.println("Файл пустой или не удалось считать");
            return;
        }

        // Преобразование списка строк в список слов
        List<String> words = CsvParser.convertTextLinesToWordsList(strings);

        // Подсчет частоты слов
        Map<String, Integer> wordCountMap = CsvParser.convertWordsListToMapWithWordsCount(words);
        int totalWords = CsvParser.counter; // Количество клов в .txt

        // Сортировка по убыванию частоты
        Map<String, Integer> sortedMap = wordCountMap.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue())) // Сортировка по значению (по убыванию)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, _) -> e1, // Объединение дубликатов (не используется)
                        LinkedHashMap::new // Сохраняем порядок сортировки
                ));

        String outputFile = "word_frequency.csv";

        // Запись в CSV
        CsvParser.createCsvFile(sortedMap, totalWords, outputFile);
    }

    private static List<String> readFileFromResources(String fileName) throws IOException {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
             return reader.lines().toList();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

}
