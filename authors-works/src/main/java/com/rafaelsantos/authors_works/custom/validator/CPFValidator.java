package com.rafaelsantos.authors_works.custom.validator;

import com.rafaelsantos.authors_works.custom.CPFValidation;
import com.rafaelsantos.authors_works.entities.Author;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintValidator;

public class CPFValidator implements ConstraintValidator<CPFValidation, Author> {

    @Override
    public boolean isValid(Author author, ConstraintValidatorContext context) {
        if (author.getCountry() != null && author.getCountry().equalsIgnoreCase("Brasil")) {
            return author.getCpf() != null && isValidCPF(author.getCpf());
        }
        return true;
    }

    private boolean isValidCPF(String cpf) {
        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }
}