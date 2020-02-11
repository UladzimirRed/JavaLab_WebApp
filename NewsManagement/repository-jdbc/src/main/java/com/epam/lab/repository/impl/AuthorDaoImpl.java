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
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getEntityById(Long authorId) {
        return jdbcTemplate.query(SqlRequest.SQL_FIND_AUTHOR_BY_ID, new Object[] {authorId},
                new AuthorRowMapper()).stream().findAny().orElse(null);
    }

    @Override
    public List<Author> getAllEntities() {
        return jdbcTemplate.query(SqlRequest.SQL_FIND_ALL_AUTHORS, new AuthorRowMapper());
    }

    @Override
    public boolean deleteEntity(Long authorId) {
        return jdbcTemplate.update(SqlRequest.SQL_DELETE_AUTHOR, authorId) > 0;
    }

    @Override
    public boolean updateEntity(Author author) {
        return jdbcTemplate.update(SqlRequest.SQL_UPDATE_AUTHOR, author.getAuthorName(), author.getAuthorSurname(), author.getAuthorId()) > 0;
    }

    @Override
    public boolean createEntity(Author author) {
        return jdbcTemplate.update(SqlRequest.SQL_INSERT_AUTHOR, author.getAuthorName(), author.getAuthorSurname()) > 0;
    }

    @Override
    public Author getAuthorByNewsId(Long newsId) {
        return jdbcTemplate.queryForObject(SqlRequest.SQL_FIND_AUTHOR_BY_NEWS_ID, new Object[]{newsId}, new AuthorRowMapper());
    }
}
