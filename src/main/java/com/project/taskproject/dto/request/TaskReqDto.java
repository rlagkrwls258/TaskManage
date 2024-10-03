package com.project.taskproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class TaskReqDto {
    private Long author_id;
    private String author_name;
    private String title;
    private String password;
//    private LocalDateTime createTime;
//    private LocalDateTime editTIme;
}
