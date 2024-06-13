package com.rafaelsantos.authors_works.custom;

import com.rafaelsantos.authors_works.custom.validator.CountryValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CountryValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CountryValidation {
    String message() default "País de origem inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}