package lab0;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // Чтение файла построчно
        List<String> strings = CsvParser.readFile("lab0/sample1.txt");

        // Преобразование списка строк в список слов
        List<String> words = CsvParser.convertTextLinesToWordsList(strings);

        // Подсчет частоты слов
        Map<String, Integer> wordCountMap = CsvParser.convertWordsListToMapWithWordsCount(words);
        int totalWords = CsvParser.counter; // Количество слов в .txt

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


//        int count = map.get("слово");
//        count++;
//        map.put("слово", count);

    }
}
