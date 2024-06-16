package com.rafaelsantos.authors_works.services;

import com.rafaelsantos.authors_works.entities.Author;
import com.rafaelsantos.authors_works.entities.DTO.AuthorDTO;
import com.rafaelsantos.authors_works.repositories.AuthorRepository;
import com.rafaelsantos.authors_works.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public List<AuthorDTO> findAll() {
        List<Author> list = authorRepository.findAll();
        return list.stream().map(AuthorDTO::new).toList();
    }

    @Transactional
    public AuthorDTO insert(AuthorDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("AuthorDTO cannot be null");
        }

        checkIfCPFAlreadyExists(dto);

        Author entity = new Author();
        copyDtoToEntity(dto, entity);
        entity = authorRepository.save(entity);
        return new AuthorDTO(entity);
    }

    @Transactional
    public AuthorDTO update(Long id, AuthorDTO dto) {
        checkIfCPFAlreadyExists(dto);

        try {
            Author entity = authorRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = authorRepository.save(entity);
            return new AuthorDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    private void copyDtoToEntity(AuthorDTO dto, Author entity) {
        entity.setName(dto.getName());
        entity.setGender(dto.getGender());
        entity.setEmail(dto.getEmail());
        entity.setBirthdate(dto.getBirthdate());
        entity.setCountry(dto.getCountry());
        entity.setCpf(dto.getCpf());
    }

    public void checkIfCPFAlreadyExists(AuthorDTO dto) {
        if ("Brasil".equalsIgnoreCase(dto.getCountry())) {
            if (dto.getCpf() != null && authorRepository.existsByCpf(dto.getCpf())) {
                throw new DataIntegrityViolationException("CPF já existe");
            }
        }
    }
}
