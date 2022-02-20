package com.otto15.client.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class InputStreamToStringConverter {

    private InputStreamToStringConverter() {

    }

    public static String inputStreamToString(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        bufferedReader.readLine();
        String line = bufferedReader.readLine();
        while (line != null) {
            stringBuilder.append(line.trim());
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }
}
