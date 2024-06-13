package com.rafaelsantos.authors_works.custom;

import com.rafaelsantos.authors_works.custom.validator.CPFValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CPFValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CPFValidation {
    String message() default "CPF inválido ou não permitido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
