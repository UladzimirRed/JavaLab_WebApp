package com.epam.lab.repository;

import com.epam.lab.model.Author;

import java.util.List;

public interface AuthorRepository {
    Author findById (Long id);
    List<Author> findAll();
}
