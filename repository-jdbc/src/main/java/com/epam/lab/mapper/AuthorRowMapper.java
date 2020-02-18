package com.epam.lab.mapper;

import com.epam.lab.model.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getLong("id"));
        author.setAuthorName(resultSet.getString("author_name"));
        author.setAuthorSurname(resultSet.getString("author_surname"));
        return author;
    }
}
