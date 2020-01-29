package com.epam.lab.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        Author author = new Author();
        author.setAuthorId(resultSet.getLong("id"));
        author.setAuthorName(resultSet.getString("author_name"));
        author.setAuthorSurname(resultSet.getString("author_surname"));
        return author;
    }
}
