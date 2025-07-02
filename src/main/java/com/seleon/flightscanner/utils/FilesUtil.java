package com.seleon.flightscanner.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesUtil {
    public static final String PATH = "data/ryanair/";

    public static void save(String content, String fileName) {
        try {
            Files.write(Paths.get(PATH + fileName),
                    Collections.singletonList(content),
                    StandardOpenOption.CREATE,     // create file if it doesn't exist
                    StandardOpenOption.TRUNCATE_EXISTING);  // overwrite file if it exists
            System.out.println("String saved to file: " + PATH + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String load(String fileName) {
        Path path = Paths.get(PATH + fileName);

        Stream<String> lines = null;
        String data;
        try {
            lines = Files.lines(path);
            data = lines.collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (lines != null) {
                lines.close();
            }
        }

        return data;
    }
}
