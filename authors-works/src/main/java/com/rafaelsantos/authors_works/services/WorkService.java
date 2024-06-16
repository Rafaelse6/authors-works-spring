package com.rafaelsantos.authors_works.services;

import com.rafaelsantos.authors_works.entities.DTO.WorkDTO;
import com.rafaelsantos.authors_works.entities.Work;
import com.rafaelsantos.authors_works.repositories.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Transactional(readOnly = true)
    public Page<WorkDTO> findAllByName(String name, Pageable pageable){
        Page<Work> list = workRepository.searchByName(name, pageable);
        return list.map(WorkDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<WorkDTO> findAllByDescription(String description, Pageable pageable){
        Page<Work> list = workRepository.searchByDescription(description, pageable);
        return list.map(WorkDTO::new);
    }
}
