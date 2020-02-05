package com.epam.lab.repository.impl;

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

    JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getEntityById(Long id) {
        return jdbcTemplate.queryForObject(SqlRequest.SQL_FIND_AUTHOR_BY_ID, new Object[] { id }, new AuthorRowMapper());
    }

    @Override
    public List<Author> getAllEntities() {
        return jdbcTemplate.query(SqlRequest.SQL_FIND_ALL_AUTHORS, new AuthorRowMapper());
    }

    @Override
    public boolean deleteEntity(Long id) {
        return jdbcTemplate.update(SqlRequest.SQL_DELETE_AUTHOR, id) > 0;
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
