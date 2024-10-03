package com.project.taskproject.controller;

import com.project.taskproject.dto.request.TaskReqDto;
import com.project.taskproject.dto.response.TaskResDto;
import com.project.taskproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public TaskResDto createTask(@RequestBody TaskReqDto taskReqDto) {
        return taskService.createTask(taskReqDto);
    }

    @GetMapping("/tasks")
    public List<TaskResDto> getAllTasks(@RequestParam(required = false) String author_name,
                                        @RequestParam(required = false) String editTime) {
        return taskService.getTasksByFilter(author_name, editTime);
    }

    @GetMapping("/tasks/page")
    public List<TaskResDto> getTasksWithPaging(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return taskService.getTasksWithPaging(page, size);

    }


    @GetMapping("/task/{id}")
    public TaskResDto getTask(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/task/{id}")
    public TaskResDto updateTask(@PathVariable Long id, @RequestBody TaskReqDto taskReqDto) {
        return taskService.updateTask(id,taskReqDto);

    }

    @DeleteMapping("/task/{id}")
    public TaskResDto deleteTask(@PathVariable long id, @RequestBody TaskReqDto taskReqDto) {
        return taskService.deleteTask(id,taskReqDto);
    }

}
