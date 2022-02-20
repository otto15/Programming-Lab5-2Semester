package com.otto15.client.entities.validators;

import com.otto15.client.entities.enums.Color;

public final class ColorValidator {

    private ColorValidator() {

    }

    public static Color getValidatedColor(String colorArg) {
        try {
            return Color.valueOf(colorArg);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid color.");
        }
    }
}
