package com.project.taskproject.dto.response;

import com.project.taskproject.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class TaskResDto {
    private Long id;
    private Long author_id;
    private String author_name;
    private String title;
    private LocalDateTime createTime;
    private LocalDateTime editTIme;

    public TaskResDto(Task task) {
        this.id = task.getId();
        this.author_id = task.getAuthor_id();
        this.author_name = task.getAuthor_name();
        this.title = task.getTitle();
        this.createTime = task.getCreateTime();
        this.editTIme = task.getEditTIme();
    }

}
