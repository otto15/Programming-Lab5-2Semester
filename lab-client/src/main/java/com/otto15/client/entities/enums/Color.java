package com.otto15.client.entities.enums;

public enum Color {
    GREEN,
    YELLOW,
    ORANGE,
    WHITE,
    BROWN,
    RED,
    BLACK,
    BLUE;

    public static String getAvailableColorNames() {
        StringBuilder colors = new StringBuilder();
        for (Color color: Color.values()) {
            colors.append(color.name());
            colors.append(" ");
        }
        return colors.toString();
    }
}
