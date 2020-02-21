package com.epam.lab.repository.impl;

import com.epam.lab.exception.DaoException;
import com.epam.lab.model.Author;
import com.epam.lab.mapper.AuthorRowMapper;
import com.epam.lab.repository.AuthorDao;
import com.epam.lab.repository.SqlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public class AuthorDaoImpl implements AuthorDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getEntityById(Long authorId) {
        try {
            return jdbcTemplate.query(SqlRequest.SQL_FIND_AUTHOR_BY_ID, new Object[]{authorId},
                    new AuthorRowMapper()).stream().findAny().orElse(null);
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public List<Author> getAllEntities() {
        try {
            return jdbcTemplate.query(SqlRequest.SQL_FIND_ALL_AUTHORS, new AuthorRowMapper());
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean deleteEntity(Long authorId) {
        try {
            return jdbcTemplate.update(SqlRequest.SQL_DELETE_AUTHOR, authorId) > 0;
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean updateEntity(Author author) {
        try {
            return jdbcTemplate.update(SqlRequest.SQL_UPDATE_AUTHOR, author.getAuthorName(), author.getAuthorSurname(), author.getId()) > 0;
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean createEntity(Author author) {
        try {
            return jdbcTemplate.update(SqlRequest.SQL_INSERT_AUTHOR, author.getAuthorName(), author.getAuthorSurname()) > 0;
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public Author getAuthorByNewsId(Long newsId) {
        try {
            return jdbcTemplate.queryForObject(SqlRequest.SQL_FIND_AUTHOR_BY_NEWS_ID, new Object[]{newsId}, new AuthorRowMapper());
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }
}
