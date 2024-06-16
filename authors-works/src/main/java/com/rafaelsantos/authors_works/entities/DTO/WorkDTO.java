package com.rafaelsantos.authors_works.entities.DTO;

import com.rafaelsantos.authors_works.entities.Author;
import com.rafaelsantos.authors_works.entities.Work;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WorkDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 240, message = "A descrição deve conter no máximo 240 caracteres")
    private String description;

    private LocalDate publicationDate;

    private LocalDate exhibitionDate;

    private List<AuthorDTO> authors = new ArrayList<>();

    public WorkDTO(Work entity){
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        publicationDate = entity.getExhibitionDate();
        exhibitionDate = entity.getExhibitionDate();

        for (Author aut : entity.getAuthors()){
            authors.add(new AuthorDTO(aut));
        }
    }
}
