package com.project.taskproject.controller;

import com.project.taskproject.dto.request.AuthorReqDto;
import com.project.taskproject.dto.response.AuthorResDto;
import com.project.taskproject.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService AuthorService) {
        this.authorService = AuthorService;
    }

    @PostMapping("/Authors")
    public AuthorResDto createAuthor(@RequestBody AuthorReqDto AuthorReqDto) {
        return authorService.createAuthor(AuthorReqDto);
    }

    @PutMapping("/Author/{id}")
    public AuthorResDto updateAuthor(@PathVariable Long id, @RequestBody AuthorReqDto AuthorReqDto) {
        return authorService.updateAuthor(id,AuthorReqDto);

    }

    @DeleteMapping("/Author/{id}")
    public AuthorResDto deleteAuthor(@PathVariable long id, @RequestBody AuthorReqDto AuthorReqDto) {
        return authorService.deleteAuthor(id,AuthorReqDto);
    }
}
