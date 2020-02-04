package com.epam.lab.service;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> showAllAuthors ();
    Author showAuthorById(long id);
    Author saveAuthor(Author author);
}
