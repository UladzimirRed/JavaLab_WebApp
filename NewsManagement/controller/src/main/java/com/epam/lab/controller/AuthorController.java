package com.epam.lab.controller;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public boolean createAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.saveAuthor(authorDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorDto getAuthor(@PathVariable("id") Long id) {
        return authorService.showAuthorById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AuthorDto> getAllAuthors() {
        return authorService.showAllAuthors();
    }

    @PutMapping(value = "{id}")
    public boolean updateAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.editAuthor(authorDto);
    }

    @DeleteMapping(value = "{id}")
    public boolean deleteAuthor(@PathVariable("id") Long id) {
        return authorService.removeAuthor(id);
    }
}
