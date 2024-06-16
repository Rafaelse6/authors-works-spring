package com.rafaelsantos.authors_works.services;

import com.rafaelsantos.authors_works.entities.Author;
import com.rafaelsantos.authors_works.entities.DTO.AuthorDTO;
import com.rafaelsantos.authors_works.entities.Work;
import com.rafaelsantos.authors_works.factories.AuthorFactory;
import com.rafaelsantos.authors_works.repositories.AuthorRepository;
import com.rafaelsantos.authors_works.repositories.WorkRepository;
import com.rafaelsantos.authors_works.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private WorkRepository workRepository;


    private Author author;
    private AuthorDTO authorDTO;
    private Work work;
    private long existingAuthorId, nonExistingAuthorId;
    private List<Author> list;

    @BeforeEach
    void setUp() throws Exception{
        existingAuthorId  = 1L;
        nonExistingAuthorId = 2L;

        author = AuthorFactory.createAuthor();
        authorDTO = new AuthorDTO(author);


        list = new ArrayList<>();
        list.add(author);

        Mockito.when(authorRepository.findAll()).thenReturn(list);

        Mockito.when(authorRepository.save(any())).thenReturn(author);
        Mockito.when(authorRepository.existsByCpf(anyString())).thenReturn(false);

        Mockito.when(authorRepository.existsById(existingAuthorId)).thenReturn(true);
        Mockito.when(authorRepository.existsById(nonExistingAuthorId)).thenReturn(false);
        Mockito.when(authorRepository.getReferenceById(existingAuthorId)).thenReturn(author);
        Mockito.when(authorRepository.getReferenceById(nonExistingAuthorId)).thenThrow(EntityNotFoundException.class);
    }

    @Test
    public void findAllShouldReturnListAuthorDTO(){
        List<AuthorDTO> result = authorService.findAll();

        Assertions.assertEquals(result.size(), 1);
        Assertions.assertEquals(result.get(0).getId(), author.getId());
        Assertions.assertEquals(result.get(0).getName(), author.getName());
    }

    @Test
    public void insertShouldReturnAuthorDTO(){
        AuthorDTO result = authorService.insert(authorDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), author.getId());
    }

    @Test
    public void insertShouldNotCheckCpfIfCountryIsNotBrazil() {
        authorDTO.setCountry("USA");
        authorDTO.setCpf(null);
        Mockito.when(authorRepository.existsByCpf(anyString())).thenReturn(false);

        AuthorDTO result = authorService.insert(authorDTO);

        Assertions.assertNotNull(result);
        verify(authorRepository, times(0)).existsByCpf(anyString());
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    public void insertShouldThrowIllegalArgumentExceptionWhenDtoIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> authorService.insert(null));
    }

    @Test
    public void updateShouldReturnAuthorDTOWhenIdExists() {
        authorDTO.setCpf("123.456.789-00");

        AuthorDTO result = authorService.update(existingAuthorId, authorDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), author.getId());
        Assertions.assertEquals(result.getName(), author.getName());
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> authorService.update(nonExistingAuthorId, authorDTO));
    }

    @Test
    public void updateShouldNotCheckCpfIfCountryIsNotBrazil() {
        authorDTO.setCountry("USA");
        authorDTO.setCpf(null);
        Mockito.when(authorRepository.getReferenceById(existingAuthorId)).thenReturn(author);

        AuthorDTO result = authorService.update(existingAuthorId, authorDTO);

        Assertions.assertNotNull(result);
        verify(authorRepository, times(0)).existsByCpf(anyString());
        verify(authorRepository, times(1)).save(any(Author.class));
    }
} 