import org.example.CsvParser;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class CsvParserTest {

    @Test
    public void testReadFile() {
        List<String> lines = CsvParser.readFile("lab0/sample1.txt");
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

        assertEquals(Optional.of(3), wordCountMap.get("кот"));
        assertEquals(Optional.of(2), wordCountMap.get("собака"));
        assertEquals(Optional.of(1), wordCountMap.get("птица"));
    }

    @Test
    public void testCreateCsvFile() {
        Map<String, Integer> wordCountMap = Map.of("apple", 3, "banana", 2, "cherry", 1);
        String outputFile = "test_output.csv";
        CsvParser.createCsvFile(wordCountMap, 6, outputFile);

        assertTrue("Файл должен быть создан", Files.exists(Paths.get(outputFile)));
    }

}
