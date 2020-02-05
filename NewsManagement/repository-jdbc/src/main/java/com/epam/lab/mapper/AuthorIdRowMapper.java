package com.epam.lab.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorIdRowMapper implements RowMapper {
    @Override
    public Long mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getLong("author_id");
    }
}
