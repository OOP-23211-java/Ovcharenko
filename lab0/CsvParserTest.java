package lab0;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CsvParserTest {

    @Test
    void testReadFile() {
        List<String> lines = CsvParser.readFile("lab0/sample1.txt");
        assertNotNull(lines, "Файл должен быть считан");
        assertFalse(lines.isEmpty(), "Файл не должен быть пустым");
    }

    @Test
    void testConvertTextLinesToWordsList() {
        List<String> lines = List.of("Привет, мир!", "Тестовая строка.");
        List<String> words = CsvParser.convertTextLinesToWordsList(lines);

        assertEquals(4, words.size());
        assertTrue(words.contains("Привет"));
        assertTrue(words.contains("мир"));
        assertTrue(words.contains("Тестовая"));
        assertTrue(words.contains("строка"));
    }

    @Test
    void testConvertWordsListToMapWithWordsCount() {
        List<String> words = List.of("кот", "кот", "собака", "кот", "собака", "птица");
        Map<String, Integer> wordCountMap = CsvParser.convertWordsListToMapWithWordsCount(words);

        assertEquals(3, wordCountMap.get("кот"));
        assertEquals(2, wordCountMap.get("собака"));
        assertEquals(1, wordCountMap.get("птица"));
    }

    @Test
    void testCreateCsvFile() {
        Map<String, Integer> wordCountMap = Map.of("apple", 3, "banana", 2, "cherry", 1);
        String outputFile = "test_output.csv";
        CsvParser.createCsvFile(wordCountMap, 6, outputFile);

        assertTrue(Files.exists(Paths.get(outputFile)), "Файл должен быть создан");
    }
}
