package com.otto15.client.entities;

import com.otto15.client.entities.enums.Color;
import com.otto15.client.entities.enums.Country;
import com.otto15.client.entities.validators.PersonValidator;
import com.otto15.client.utils.DataNormalizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Loader for Person class
 *
 * @author Rakhmatullin R.
 */
public final class PersonLoader {

    private PersonLoader() {

    }

    public static String loadName(Reader reader, String currentValue) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        while (true) {
            System.out.print("Enter name(e.g \"Hasbulla\", name has not to be empty)");
            if (!"".equals(currentValue)) {
                System.out.print(", current value - " + currentValue);
            }
            System.out.print(": ");
            String data = in.readLine();
            try {
                String[] normalizedData = DataNormalizer.normalize(data);
                return PersonValidator.getValidatedName(normalizedData);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static String loadName(Reader reader) throws IOException {
        return loadName(reader, "");
    }

    public static long loadHeight(Reader reader, String currentValue) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        while (true) {
            System.out.print("Enter height(enter integer number)");
            if (!"".equals(currentValue)) {
                System.out.print(", current value - " + currentValue);
            }
            System.out.print(": ");
            String data = in.readLine();
            try {
                String[] normalizedData = DataNormalizer.normalize(data);
                return PersonValidator.getValidatedHeight(normalizedData);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static long loadHeight(Reader reader) throws IOException {
        return loadHeight(reader, "");
    }


    public static Coordinates loadCoordinates(Reader reader, String currentValue) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        while (true) {
            System.out.print("Enter coordinates(enter x and y separated by space,e.g \"15.5 12\")");
            if (!"".equals(currentValue)) {
                System.out.print(", current value - " + currentValue);
            }
            System.out.print(": ");
            String data = in.readLine();
            try {
                String[] normalizedData = DataNormalizer.normalize(data);
                return PersonValidator.getValidatedCoordinates(normalizedData);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Coordinates loadCoordinates(Reader reader) throws IOException {
        return loadCoordinates(reader, "");
    }


    public static Color loadEyeColor(Reader reader, String currentValue) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        while (true) {
            System.out.print("Enter eye color(choose color from the list below, field can be empty)");
            if (!"".equals(currentValue)) {
                System.out.print(", current value - " + currentValue);
            }
            System.out.println();
            System.out.println(Color.getAvailableColorNames());
            String data = in.readLine();
            try {
                String[] normalizedData = DataNormalizer.normalize(data);
                return PersonValidator.getValidatedColor(normalizedData);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Color loadEyeColor(Reader reader) throws IOException {
        return loadEyeColor(reader, "");
    }


    public static Color loadHairColor(Reader reader, String currentValue) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        while (true) {
            System.out.print("Enter hair color(choose color from the list below, field can be empty)");
            if (!"".equals(currentValue)) {
                System.out.print(", current value - " + currentValue);
            }
            System.out.println();
            System.out.println(Color.getAvailableColorNames());
            String data = in.readLine();
            try {
                String[] normalizedData = DataNormalizer.normalize(data);
                return PersonValidator.getValidatedColor(normalizedData);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Color loadHairColor(Reader reader) throws IOException {
        return loadHairColor(reader, "");
    }

    public static Country loadNationality(Reader reader, String currentValue) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        while (true) {
            System.out.print("Enter nationality(choose country from the list below, field can not be empty)");
            if (!"".equals(currentValue)) {
                System.out.print(", current value - " + currentValue);
            }
            System.out.println();
            System.out.println(Country.getAvailableCountryNames());
            String data = in.readLine();
            try {
                String[] normalizedData = DataNormalizer.normalize(data);
                return PersonValidator.getValidatedCountry(normalizedData);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Country loadNationality(Reader reader) throws IOException {
        return loadNationality(reader, "");
    }

    public static Location loadLocation(Reader reader, String currentValue) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        while (true) {
            System.out.print("Enter location(enter x, y, z separated by space,e.g \"15.5 99.99 12\")");
            if (!"".equals(currentValue)) {
                System.out.print(", current value - " + currentValue);
            }
            System.out.print(": ");
            String data = in.readLine();
            try {
                String[] normalizedData = DataNormalizer.normalize(data);
                return PersonValidator.getValidatedLocation(normalizedData);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Location loadLocation(Reader reader) throws IOException {
        return loadLocation(reader, "");
    }

    public static Person loadPerson(Reader reader) throws IOException {
        return new Person(
                loadName(reader),
                loadCoordinates(reader),
                loadHeight(reader),
                loadEyeColor(reader),
                loadHairColor(reader),
                loadNationality(reader),
                loadLocation(reader)
        );
    }
}
