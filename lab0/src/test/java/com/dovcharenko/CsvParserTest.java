package com.dovcharenko;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class CsvParserTest {

    @Test
    void testConvertWordsListToMapWithWordsCount() {
        Stream<String> wordsStream = Stream.of("apple", "banana", "apple", "orange", "banana", "banana");
        Map<String, Integer> result = CsvParser.convertWordsListToMapWithWordsCount(wordsStream);

        assertEquals(3, result.size());
        assertEquals(Optional.of(2), result.get("apple"));
        assertEquals(Optional.of(3), result.get("banana"));
        assertEquals(Optional.of(1), result.get("orange"));
    }

    @Test
    void testSortByValueDescending() {
        Map<String, Integer> map = Map.of("apple", 2, "banana", 3, "orange", 1);
        Map<String, Integer> sortedMap = CsvParser.sortByValueDescending(map);

        assertEquals("banana", sortedMap.keySet().iterator().next());
        assertEquals(Optional.of(3), sortedMap.get("banana"));
        assertEquals(Optional.of(1), sortedMap.get("orange"));
    }

    @Test
    void testCreateCsvFile() throws IOException {
        Map<String, Integer> wordCountMap = Map.of("apple", 2, "banana", 3);
        int totalWords = 5;
        String outputFile = "test_output.csv";

        CsvParser.createCsvFile(wordCountMap, totalWords, outputFile);

        File file = new File(outputFile);
        assertTrue(file.exists());

        String content = Files.readString(Paths.get(outputFile));
        assertTrue(content.contains("apple, 2, 40.00%"));
        assertTrue(content.contains("banana, 3, 60.00%"));

        // Удаляем файл после теста
        file.delete();
    }
}