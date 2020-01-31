package com.epam.lab.repository.impl;

import com.epam.lab.model.Author;
import com.epam.lab.mapper.AuthorMapper;
import com.epam.lab.repository.AuthorDao;
import com.epam.lab.repository.SqlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    JdbcTemplate jdbcTemplate;
    @Autowired
    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getEntityById(long id) {
        return jdbcTemplate.queryForObject(SqlRequest.SQL_FIND_AUTHOR_BY_ID, new Object[] { id }, new AuthorMapper());
    }

    @Override
    public List<Author> getAllEntities() {
        return jdbcTemplate.query(SqlRequest.SQL_FIND_ALL_AUTHORS, new AuthorMapper());
    }

    @Override
    public boolean deleteEntity(Author author) {
        return jdbcTemplate.update(SqlRequest.SQL_DELETE_AUTHOR, author.getAuthorId()) > 0;
    }

    @Override
    public boolean updateEntity(Author author) {
        return jdbcTemplate.update(SqlRequest.SQL_UPDATE_AUTHOR, author.getAuthorName(), author.getAuthorSurname(), author.getAuthorId()) > 0;
    }

    @Override
    public boolean createEntity(Author author) {
        return jdbcTemplate.update(SqlRequest.SQL_INSERT_AUTHOR, author.getAuthorName(), author.getAuthorSurname()) > 0;
    }
}
