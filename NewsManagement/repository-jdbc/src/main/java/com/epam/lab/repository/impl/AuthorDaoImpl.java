package com.epam.lab.repository.impl;

import com.epam.lab.model.Author;
import com.epam.lab.model.AuthorMapper;
import com.epam.lab.repository.AuthorDao;
import com.epam.lab.repository.SqlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public Author getAuthorById(long id) {
        return jdbcTemplate.queryForObject(SqlRequest.SQL_FIND_AUTHOR, new Object[] { id }, new AuthorMapper());
    }

    public List<Author> getAllPerson() {
        return jdbcTemplate.query(SqlRequest.SQL_FIND_ALL_AUTHORS, new AuthorMapper());
    }

    public boolean deleteAuthor(Author author) {
        return false;
    }

    public boolean updateAuthor(Author author) {
        return false;
    }

    public boolean createAuthor(Author author) {
        return false;
    }
}
