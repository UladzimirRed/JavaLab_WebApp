package com.epam.lab.repository;

import com.epam.lab.model.Author;

public interface AuthorDao extends BaseDao<Author> {

    Author getAuthorByNewsId(Long newsId);
}
