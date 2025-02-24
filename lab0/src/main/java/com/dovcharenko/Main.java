package com.dovcharenko;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Укажите имя файла в аргументах программы.");
            return;
        } else if (args.length > 1) {
            System.out.println("Программа использует только первый аргумент. Остальные аргументы игнорируются.");
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
