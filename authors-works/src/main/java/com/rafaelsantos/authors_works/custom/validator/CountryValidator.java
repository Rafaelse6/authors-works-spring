package com.rafaelsantos.authors_works.custom.validator;

import com.rafaelsantos.authors_works.custom.CountryValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Locale;

public class CountryValidator implements ConstraintValidator<CountryValidation, String> {

    @Override
    public void initialize(CountryValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String country, ConstraintValidatorContext context) {
        if (country == null) {
            return false;
        }

        String[] isoCountries = Locale.getISOCountries();
        return Arrays.stream(isoCountries)
                .map(code -> new Locale("", code))
                .anyMatch(locale -> locale.getDisplayCountry().equalsIgnoreCase(country));
    }
}