package com.rafaelsantos.authors_works.services;

import com.rafaelsantos.authors_works.entities.Author;
import com.rafaelsantos.authors_works.entities.DTO.AuthorDTO;
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
        Page<Work> list = workRepository.searchByName(description, pageable);
        return list.map(WorkDTO::new);
    }


    private void copyDtoToEntity(WorkDTO dto, Work entity){
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setExhibitionDate(dto.getExhibitionDate());
        entity.setPublicationDate(dto.getPublicationDate());

        entity.getAuthors().clear();
        for (AuthorDTO authorDTO : dto.getAuthors()){
            Author author = new Author();
            author.setId(author.getId());
            entity.getAuthors().add(author);
        }
    }
}
