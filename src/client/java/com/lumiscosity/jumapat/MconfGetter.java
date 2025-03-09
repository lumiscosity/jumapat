package com.lumiscosity.jumapat;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class MconfGetter {
    @NotNull
    public static String get(File file, String key) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while (line != null) {
                if (!line.startsWith("#")) {
                    String[] split = line.split("=", 2);
                    if (Objects.equals(split[0], key)) {
                        reader.close();
                        return split[1];
                    }
                }
                line = reader.readLine();
            }

            reader.close();

            return "TRANSPARENT";
        } catch (IOException e) {
            return "TRANSPARENT";
        }
    }
}