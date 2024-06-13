package com.rafaelsantos.authors_works.services;

import com.rafaelsantos.authors_works.entities.Author;
import com.rafaelsantos.authors_works.entities.DTO.AuthorDTO;
import com.rafaelsantos.authors_works.factories.AuthorFactory;
import com.rafaelsantos.authors_works.repositories.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepository authorRepository;

    private Author author;
    private List<Author> list;

    @BeforeEach
    void setUp() throws Exception{
        author = AuthorFactory.createAuthor();

        list = new ArrayList<>();
        list.add(author);

        Mockito.when(authorRepository.findAll()).thenReturn(list);
    }

    @Test
    public void findAllShouldReturnListAuthorDTO(){
        List<AuthorDTO> result = authorService.findAll();

        Assertions.assertEquals(result.size(), 1);
        Assertions.assertEquals(result.get(0).getId(), author.getId());
        Assertions.assertEquals(result.get(0).getName(), author.getName());
    }
}
