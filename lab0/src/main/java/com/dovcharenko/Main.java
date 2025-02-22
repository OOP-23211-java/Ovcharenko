package com.dovcharenko;

import java.io.IOException;
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
        List<String> strings = FileReader.readFileFromResources(fileName);
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
        Map<String, Integer> sortedMap = CsvParser.sortByValueDescending(wordCountMap);

        String outputFile = "word_frequency.csv";

        // Запись в CSV
        CsvParser.createCsvFile(sortedMap, totalWords, outputFile);
    }

}
