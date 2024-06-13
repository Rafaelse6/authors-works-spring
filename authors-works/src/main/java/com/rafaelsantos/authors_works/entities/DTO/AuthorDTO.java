package com.rafaelsantos.authors_works.entities.DTO;

import com.rafaelsantos.authors_works.custom.CPFValidation;
import com.rafaelsantos.authors_works.custom.CountryValidation;
import com.rafaelsantos.authors_works.entities.Author;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@CPFValidation
public class AuthorDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    private String gender;

    @Email(message = "O e-mail deve ser válido")
    private String email;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser no passado")
    private LocalDate birthdate;

    @NotBlank(message = "O país de origem é obrigatório")
    @CountryValidation
    private String country;

    private String cpf;

    public AuthorDTO(Author entity){
        id = entity.getId();
        name = entity.getName();
        gender = entity.getGender();
        email = entity.getEmail();
        birthdate = entity.getBirthdate();
        country = entity.getCountry();
        cpf = entity.getCpf();
    }
}
