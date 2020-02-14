package com.epam.lab.controller;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.exception.DaoException;
import com.epam.lab.exception.ErrorMessage;
import com.epam.lab.exception.ServiceException;
import com.epam.lab.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public boolean createAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.saveDto(authorDto);
    }

    @GetMapping(value = "/{authorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorDto getAuthor(@PathVariable("authorId") Long authorId) {
        AuthorDto authorDto = authorService.showDtoById(authorId);
        if (authorDto == null) {
            throw new RuntimeException();
        }
        return authorDto;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AuthorDto> getAllAuthors() {
        List<AuthorDto> authorDtos = authorService.showAllDto();
        if (authorDtos == null) {
            throw new RuntimeException();
        }
        return authorDtos;
    }

    @PutMapping(value = "{id}")
    public AuthorDto updateAuthor(@RequestBody AuthorDto authorDto) throws ServiceException {
        AuthorDto updatedAuthorDto = authorService.editDto(authorDto);
        if (updatedAuthorDto == null) {
            throw new RuntimeException();
        }
        return updatedAuthorDto;
    }

    @DeleteMapping(value = "{authorId}")
    public boolean deleteAuthor(@PathVariable("authorId") Long authorId) {
        if (authorService.removeDto(authorId)) {
            return true;
        } else throw new RuntimeException();
    }

    @ExceptionHandler(DaoException.class)
    public ResponseEntity<ErrorMessage> handle(DaoException ex) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
