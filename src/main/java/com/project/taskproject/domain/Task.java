package com.project.taskproject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private Long id;
    private Long author_id;
    private String author_name;
    private String title;
    private String password;
    private LocalDateTime createTime;
    private LocalDateTime editTIme;

}

