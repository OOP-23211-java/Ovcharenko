package com.dovcharenko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Stream;

public class FileReader {
    public static Stream<String> readFileFromResources(String fileName) throws IOException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IOException("Файл не найден: " + fileName);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        return reader.lines()
                .flatMap(line -> Arrays.stream(
                        line.replaceAll("[^a-zA-Zа-яА-ЯёЁ0-9\\s]", " ")
                                .trim()
                                .split("\\s+"))
                )
                .filter(word -> !word.isEmpty());
    }
}