package com.epam.lab.repository;

import com.epam.lab.model.Author;

import java.util.List;

public interface AuthorDao extends BaseDao<Author>{
    Author getEntityById(long id);
    List<Author> getAllEntities();
    boolean deleteEntity(Author author);
    boolean updateEntity(Author author);
    boolean createEntity(Author author);
}
