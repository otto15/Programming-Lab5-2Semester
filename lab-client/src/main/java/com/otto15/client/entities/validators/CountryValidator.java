package com.otto15.client.entities.validators;

import com.otto15.client.entities.enums.Country;

/**
 * Class for validation of Country enum.
 *
 * @author Rakhmatullin R.
 */
public final class CountryValidator {

    private CountryValidator() {

    }

    public static Country getValidatedColor(String colorArg) {
        try {
            return Country.valueOf(colorArg.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid country.");
        }
    }
}
