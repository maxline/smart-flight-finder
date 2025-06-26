package com.seleon.flightscanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

public class FileSaver {
    public static final String PATH = "data/";

    public static void save(String content, String fileName) {
        try {
            Files.write(Paths.get(PATH + fileName),
                    Collections.singletonList(content),
                    StandardOpenOption.CREATE,     // create file if it doesn't exist
                    StandardOpenOption.TRUNCATE_EXISTING);  // overwrite file if it exists
            System.out.println("String saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
