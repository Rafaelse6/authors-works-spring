package com.rafaelsantos.authors_works.factories;

import com.rafaelsantos.authors_works.entities.Author;

import java.time.LocalDate;
import java.util.ArrayList;

public class AuthorFactory {

    public static Author createAuthor() {
        return new Author(
                null,
                "John Doe",
                "Male",
                "john.doe@example.com",
                LocalDate.of(1980, 1, 1),
                "BRA",
                "123.456.789-00",
                new ArrayList<>()
        );
    }

    public static Author createAuthor(Long id, String name, String gender, String email, LocalDate birthdate, String country, String cpf) {
        return new Author(
                id,
                name,
                gender,
                email,
                birthdate,
                country,
                cpf,
                new ArrayList<>()
        );
    }
}
