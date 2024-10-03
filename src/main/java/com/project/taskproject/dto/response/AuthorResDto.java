package com.project.taskproject.dto.response;


import com.project.taskproject.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class AuthorResDto {
    private Long id;
    private String author_name;
    private String email;
    private LocalDateTime joinTime;
    private LocalDateTime editTIme;

    public AuthorResDto(Author author) {
        this.id = author.getId();
        this.author_name = author.getAuthor_name();
        this.email = author.getEmail();
        this.joinTime = author.getJoinTime();
        this.editTIme = author.getEditTIme();
    }
}
