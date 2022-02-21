package com.otto15.client.entities.validators;

import com.otto15.client.entities.Coordinates;
import com.otto15.client.entities.Location;
import com.otto15.client.entities.enums.Color;
import com.otto15.client.entities.enums.Country;


/**
 * Class for validation of Person class.
 * @author Rakhmatullin R.
 */
public final class PersonValidator {

    private PersonValidator() {

    }

    public static String getValidatedName(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Name field can not be empty.");
        }
        if (args.length > 1) {
            throw new IllegalArgumentException("Provide one argument, use \"\" for several words.");
        }
        return args[0];
    }

    public static Coordinates getValidatedCoordinates(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Coordinates field can not be empty.");
        }
        if (args.length != 2) {
            throw new IllegalArgumentException("Coordinates field must have 2 arguments.");
        }
        return new Coordinates(CoordinatesValidator.getValidatedX(args[0]), CoordinatesValidator.getValidatedY(args[1]));
    }

    public static long getValidatedHeight(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Height field can not be empty.");
        } else if (args.length != 1) {
            throw new IllegalArgumentException("Height implies 1 number.");
        }
        try {
            long height = Long.parseLong(args[0]);
            if (height <= 0) {
                throw new IllegalArgumentException("Height field must be greater than zero.");
            }
            return height;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input of height.");
        }
    }

    public static Color getValidatedColor(String[] args) {
        if (args.length == 0) {
            return null;
        }
        if (args.length != 1) {
            throw new IllegalArgumentException("Color implies 1 value.");
        }
        return ColorValidator.getValidatedColor(args[0].toUpperCase());
    }

    public static Country getValidatedCountry(String[] args) {
        if (args.length == 0) {
            return null;
        }
        if (args.length != 1) {
            throw new IllegalArgumentException("Country implies 1 value.");
        }
        return CountryValidator.getValidatedColor(args[0]);
    }

    public static Location getValidatedLocation(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Location field can not be empty.");
        }
        if (args.length != Location.class.getDeclaredFields().length) {
            throw new IllegalArgumentException("Location implies 3 values.");
        }
        return new Location(LocationValidator.getValidatedX(args[0]),
                LocationValidator.getValidatedY(args[1]),
                LocationValidator.getValidatedZ(args[2]));
    }
}
