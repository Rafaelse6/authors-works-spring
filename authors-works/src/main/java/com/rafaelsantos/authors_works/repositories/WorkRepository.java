package com.rafaelsantos.authors_works.repositories;

import com.rafaelsantos.authors_works.entities.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WorkRepository extends JpaRepository<Work, Long> {

    @Query("SELECT obj FROM Work obj " +
            "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Work> searchByName(String name, Pageable pageable);

    @Query("SELECT obj FROM Work obj " +
            "WHERE UPPER(obj.description) LIKE UPPER(CONCAT('%', :description, '%'))")
    Page<Work> searchByDescription(String description, Pageable pageable);
}
