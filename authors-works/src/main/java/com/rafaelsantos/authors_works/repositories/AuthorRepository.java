package com.rafaelsantos.authors_works.repositories;

import com.rafaelsantos.authors_works.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
