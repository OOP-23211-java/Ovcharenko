package com.dovcharenko;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    @Test
    void testReadFileFromResources() throws IOException {
        String fileName = "test.txt";
        Stream<String> wordsStream = FileReader.readFileFromResources(fileName);

        long wordCount = wordsStream.count();
        assertEquals(8, wordCount); // В файле 8 слов
    }

    @Test
    void testReadFileFromResources_FileNotFound() {
        String fileName = "non_existent_file.txt";
        assertThrows(IOException.class, () -> FileReader.readFileFromResources(fileName));
    }
}