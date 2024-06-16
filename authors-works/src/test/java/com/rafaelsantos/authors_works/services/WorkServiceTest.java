package com.rafaelsantos.authors_works.services;

import com.rafaelsantos.authors_works.entities.DTO.WorkDTO;
import com.rafaelsantos.authors_works.entities.Work;
import com.rafaelsantos.authors_works.repositories.WorkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class WorkServiceTest {

    @InjectMocks
    private WorkService workService;

    @Mock
    private WorkRepository workRepository;

    private Work work;
    private String workName;
    private PageImpl<Work> page;

    @BeforeEach
    void setUp() throws Exception {
        workName = "Obra um";
        work = new Work();
        work.setId(1L);
        work.setName(workName);
        work.setDescription("Descrição da obra um");

        page = new PageImpl<>(List.of(work));
        Mockito.when(workRepository.searchByName(any(), (Pageable) any())).thenReturn(page);

        Mockito.when(workRepository.searchByDescription(any(), (Pageable) any())).thenReturn(page);
    }

    @Test
    public void findAllByNameShouldReturnPageWorkDTO() {
        Pageable pageable = PageRequest.of(0, 12);
        String name = "Obra Um";

        Page<WorkDTO> result = workService.findAllByName(name, pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals(workName, result.iterator().next().getName());

        Mockito.verify(workRepository, Mockito.times(1)).searchByName(name, pageable);
        Mockito.verifyNoMoreInteractions(workRepository);
    }

    @Test
    public void findAllByDescriptionShouldReturnPageWorkDTO() {
        Pageable pageable = PageRequest.of(0, 12);
        String description = "Livro";

        Page<WorkDTO> result = workService.findAllByDescription(description, pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());
        Assertions.assertEquals(workName, result.iterator().next().getName());

        Mockito.verify(workRepository, Mockito.times(1)).searchByDescription(description, pageable);
        Mockito.verifyNoMoreInteractions(workRepository);
    }
}
