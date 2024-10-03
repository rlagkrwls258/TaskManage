package com.project.taskproject.service;

import com.project.taskproject.domain.Author;
import com.project.taskproject.dto.request.AuthorReqDto;
import com.project.taskproject.dto.response.AuthorResDto;
import com.project.taskproject.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorResDto createAuthor(AuthorReqDto authorReqDto) {
        Author author = new Author();

        author.setId(author.getId());
        author.setAuthor_name(authorReqDto.getAuthor_name() == null ? "" : authorReqDto.getAuthor_name());

        authorRepository.createAuthor(author);

        return new AuthorResDto(author);
    }
    public AuthorResDto updateAuthor(Long id,AuthorReqDto authorReqDto) {
        Author findedAuthor = authorRepository.findById(id);

        findedAuthor.setAuthor_name(authorReqDto.getAuthor_name() == null ? "" : authorReqDto.getAuthor_name());
        findedAuthor.setEmail(authorReqDto.getEmail() == null ? "" : authorReqDto.getEmail());

        authorRepository.updateAuthor(findedAuthor);
        return new AuthorResDto(findedAuthor);
    }

    public AuthorResDto deleteAuthor(Long id, AuthorReqDto authorReqDto ) {
        Author findedAuthor = authorRepository.findById(id);

        authorRepository.deleteAuthor(id);

        return new AuthorResDto(findedAuthor);

    }



}
