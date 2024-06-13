package com.rafaelsantos.authors_works.controllers;

import com.rafaelsantos.authors_works.entities.DTO.AuthorDTO;
import com.rafaelsantos.authors_works.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> findAll(){
        List<AuthorDTO> list = authorService.findAll();
        return ResponseEntity.ok(list);
    }
}
