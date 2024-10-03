package com.project.taskproject.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class AuthorReqDto {
    private Long id;
    private String author_name;
    private String email;
    private LocalDateTime joinTime;
    private LocalDateTime editTIme;
}
