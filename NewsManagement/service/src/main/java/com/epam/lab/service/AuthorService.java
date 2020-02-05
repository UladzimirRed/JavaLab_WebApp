package com.epam.lab.service;

import com.epam.lab.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> showAllAuthors ();
    AuthorDto showAuthorById(Long id);
    boolean saveAuthor(AuthorDto authorDto);
    boolean editAuthor(AuthorDto authorDto);
    boolean removeAuthor(Long id);
}
