package com.dovcharenko;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CsvParserTest {

    @Test
    public void testReadFile() throws IOException {
        List<String> lines = FileReader.readFileFromResources("sample.txt");
        assertNotNull(String.valueOf(lines), "Файл должен быть считан");
        assertFalse("Файл не должен быть пустым", lines.isEmpty());
    }

    @Test
    public void testConvertTextLinesToWordsList() {
        List<String> lines = List.of("Привет, мир!", "Тестовая строка.");
        List<String> words = CsvParser.convertTextLinesToWordsList(lines);

        assertEquals(4, words.size());
        assertTrue(words.contains("Привет"));
        assertTrue(words.contains("мир"));
        assertTrue(words.contains("Тестовая"));
        assertTrue(words.contains("строка"));
    }

    @Test
    public void testConvertWordsListToMapWithWordsCount() {
        List<String> words = List.of("кот", "кот", "собака", "кот", "собака", "птица");
        Map<String, Integer> wordCountMap = CsvParser.convertWordsListToMapWithWordsCount(words);

        assertEquals(3, (int) wordCountMap.get("кот"));
        assertEquals(2, (int) wordCountMap.get("собака"));
        assertEquals(1, (int) wordCountMap.get("птица"));
    }

    @Test
    public void testCreateCsvFile() {
        Map<String, Integer> wordCountMap = Map.of("apple", 3, "banana", 2, "cherry", 1);
        String outputFile = "test_output.csv";
        CsvParser.createCsvFile(wordCountMap, 6, outputFile);

        assertTrue("Файл должен быть создан", Files.exists(Paths.get(outputFile)));
    }

}
