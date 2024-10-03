package com.project.taskproject.service;

import com.project.taskproject.domain.Task;
import com.project.taskproject.dto.request.TaskReqDto;
import com.project.taskproject.dto.response.TaskResDto;
import com.project.taskproject.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResDto createTask(TaskReqDto taskReqDto) {
        Task task = new Task();

        task.setTitle(taskReqDto.getTitle());
        task.setAuthor_id(taskReqDto.getAuthor_id());
        task.setAuthor_name(taskReqDto.getAuthor_name() == null ? "" : taskReqDto.getAuthor_name());
        task.setPassword(taskReqDto.getPassword());
        task.setCreateTime(LocalDateTime.now());

        taskRepository.createTask(task);  // Data Access Layer 호출

        return new TaskResDto(task);
    }

    public List<TaskResDto> getAllTasks() {
        return taskRepository.findAll();
    }

    public TaskResDto getTaskById(long id) {
        return new TaskResDto(taskRepository.findById(id));
    }

    public List<TaskResDto> getTasksByFilter(String author_name, String editDate) {
        return taskRepository.findAllByFilters(author_name, editDate);
    }

    public TaskResDto updateTask(Long id, TaskReqDto taskReqDto) {
        Task findedTask = taskRepository.findById(id);

        if (!findedTask.getPassword().equals(taskReqDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }

        findedTask.setTitle(taskReqDto.getTitle());
        findedTask.setAuthor_id(taskReqDto.getAuthor_id());

        taskRepository.updateTask(findedTask);
        return new TaskResDto(findedTask);
    }

    public TaskResDto deleteTask(Long id, TaskReqDto taskReqDto ) {
        Task findedTask = taskRepository.findById(id);
        if (!findedTask.getPassword().equals(taskReqDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
        taskRepository.deleteTask(id);

        return new TaskResDto(findedTask);
    }

    public List<TaskResDto> getTasksWithPaging(int page, int size) {
        return taskRepository.findAllWithPaging(page, size);
    }


}
