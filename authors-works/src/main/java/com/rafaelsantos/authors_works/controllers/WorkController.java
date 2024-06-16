package com.rafaelsantos.authors_works.controllers;

import com.rafaelsantos.authors_works.entities.DTO.WorkDTO;
import com.rafaelsantos.authors_works.services.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/works")
public class WorkController {

    @Autowired
    private WorkService workService;

    @GetMapping("/search/by-name")
    public Page<WorkDTO> findAllByName(@RequestParam String name, Pageable pageable) {
        return workService.findAllByName(name, pageable);
    }

    @GetMapping("/search/by-description")
    public Page<WorkDTO> findAllByDescription(@RequestParam String description, Pageable pageable) {
        return workService.findAllByDescription(description, pageable);
    }
}
