package com.epam.lab.repository;

import com.epam.lab.model.Author;

import java.util.List;

public interface AuthorDao extends BaseDao<Author> {
    Author getEntityById(Long authorId);

    List<Author> getAllEntities();

    boolean deleteEntity(Long authorId);

    boolean updateEntity(Author author);

    boolean createEntity(Author author);

    Author getAuthorByNewsId(Long newsId);
}
