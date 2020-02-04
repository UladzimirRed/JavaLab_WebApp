package com.epam.lab.controller;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.model.Author;
import com.epam.lab.service.AuthorService;
import com.epam.lab.mapper.AuthorModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public AuthorModelMapper authorModelMapper;

    @PostMapping(value = "/create")
    public AuthorDto createAuthor (@RequestBody AuthorDto authorDto){
        Author author = authorModelMapper.convertToEntity(authorDto);
        Author authorCreated = authorService.saveAuthor(author);
        return authorModelMapper.convertToDto(authorCreated);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorDto getAuthor(@PathVariable("id") long id){
        return authorModelMapper.convertToDto(authorService.showAuthorById(id));
    }

//    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Author> getAll() {
//        List<AuthorDto> creatingAuthors = null;
//        creatingAuthors.add(authorModelMapper.convertToDto(authorService.showAllAuthors());
//        return authorModelMapper.convertToDto(authorService.showAllAuthors());
//    }

}
