package com.epam.lab.repository.impl;

import com.epam.lab.exception.DaoException;
import com.epam.lab.model.Author;
import com.epam.lab.mapper.AuthorRowMapper;
import com.epam.lab.repository.AuthorDao;
import com.epam.lab.repository.SqlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getEntityById(Long authorId) {
        Author author = jdbcTemplate.query(SqlRequest.SQL_FIND_AUTHOR_BY_ID, new Object[]{authorId},
                new AuthorRowMapper()).stream().findAny().orElse(null);
        if (author == null) {
            throw new DaoException("Author with ID: " + authorId + " was not found");
        }
        return author;
    }

    @Override
    public List<Author> getAllEntities() {
        List<Author> authors = jdbcTemplate.query(SqlRequest.SQL_FIND_ALL_AUTHORS, new AuthorRowMapper());
        if (authors.stream().findAny().orElse(null) == null) {
            throw new DaoException("List of authors was not founded");
        }
        return authors;
    }

    @Override
    public boolean deleteEntity(Long authorId) {
        if (jdbcTemplate.update(SqlRequest.SQL_DELETE_AUTHOR, authorId) > 0) {
            return true;
        } else throw new DaoException("Author with ID: " + authorId + " was not delete");
    }

    @Override
    public boolean updateEntity(Author author) {
        if (jdbcTemplate.update(SqlRequest.SQL_UPDATE_AUTHOR, author.getAuthorName(), author.getAuthorSurname(), author.getAuthorId()) > 0) {
            return true;
        } else throw new DaoException("Author was not update");
    }

    @Override
    public boolean createEntity(Author author) {
        if (jdbcTemplate.update(SqlRequest.SQL_INSERT_AUTHOR, author.getAuthorName(), author.getAuthorSurname()) > 0) {
            return true;
        } else throw new DaoException("Author was not create");
    }

    @Override
    public Author getAuthorByNewsId(Long newsId) {
        Author author = jdbcTemplate.queryForObject(SqlRequest.SQL_FIND_AUTHOR_BY_NEWS_ID, new Object[]{newsId}, new AuthorRowMapper());
        if (author == null) {
            throw new DaoException("The author of news with ID: " + newsId + " was not found");
        }
        return author;
    }
}
