package com.rafaelsantos.authors_works.custom.validator;

import com.rafaelsantos.authors_works.custom.CPFValidation;
import com.rafaelsantos.authors_works.entities.DTO.AuthorDTO;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintValidator;

public class CPFValidator implements ConstraintValidator<CPFValidation, AuthorDTO> {

    @Override
    public boolean isValid(AuthorDTO dto, ConstraintValidatorContext context) {
        if ("Brasil".equalsIgnoreCase(dto.getCountry())) {
            return dto.getCpf() != null && isValidCPF(dto.getCpf());
        }
        return true;
    }

    private boolean isValidCPF(String cpf) {
        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }
}