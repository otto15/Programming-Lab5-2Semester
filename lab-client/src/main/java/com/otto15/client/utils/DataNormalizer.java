package com.otto15.client.utils;

public final class DataNormalizer {

    private DataNormalizer() {

    }

    public static String[] normalize(String data) {
        if ("".equals(data)) {
            return new String[0];
        }
        String[] args = data.trim().split("\\s+");
        for (String str: args
             ) {
            str = str.trim();
        }
        return args;
    }
}
