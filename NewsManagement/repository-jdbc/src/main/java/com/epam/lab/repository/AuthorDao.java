package com.epam.lab.repository;

import com.epam.lab.model.Author;

import java.util.List;

public interface AuthorDao {
    Author getAuthorById(long id);
    List<Author> getAllAuthors();
    boolean deleteAuthor(Author author);
    boolean updateAuthor(Author author);
    boolean createAuthor(Author author);
}
