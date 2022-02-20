package com.otto15.client.entities.enums;

public enum Country {
    SPAIN("Spain"),
    CHINA("China"),
    ITALY("Italy");

    private final String name;

    Country(String name) {
        this.name = name;
    }

    public String getRepresentation() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static String getAvailableCountryNames() {
        StringBuilder countries = new StringBuilder();
        for (Country country: Country.values()) {
            countries.append(country.name());
            countries.append(" ");
        }
        return countries.toString();
    }
}
