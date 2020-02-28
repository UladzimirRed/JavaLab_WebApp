package com.epam.lab.controller;

import com.epam.lab.dto.AuthorDto;

import com.epam.lab.service.impl.AuthorServiceJpaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private AuthorServiceJpaImpl authorService;

    @Autowired
    public AuthorController(AuthorServiceJpaImpl authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public boolean createAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.saveDto(authorDto);
    }

    @GetMapping(value = "/{authorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorDto getAuthor(@PathVariable("authorId") Long authorId) {
        return authorService.showDtoById(authorId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<AuthorDto> getAllAuthors() {
        return authorService.showAllDto();
    }

    @PutMapping(value = "{authorId}")
    public AuthorDto updateAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.editDto(authorDto);
    }

    @DeleteMapping(value = "{authorId}")
    public boolean deleteAuthor(@PathVariable("authorId") Long authorId) {
        return authorService.removeDto(authorId);
    }
}
