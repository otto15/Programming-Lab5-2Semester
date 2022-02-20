package com.otto15.client.entities.validators;

public final class CoordinatesValidator {

    private CoordinatesValidator() {

    }

    public static double getValidatedX(String xArg) {
        try {
            double x = Double.parseDouble(xArg);
            if (Double.isInfinite(x)) {
                throw new IllegalArgumentException("Too big value for x argument");
            }
            return x;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid coordinates's argument value.");
        }
    }

    public static Double getValidatedY(String yArg) {
        try {
            double y = Double.parseDouble(yArg);
            if (Double.isInfinite(y)) {
                throw new IllegalArgumentException("Too big value for x argument");
            }
            return y;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid coordinates's argument value.");
        }
    }


}
