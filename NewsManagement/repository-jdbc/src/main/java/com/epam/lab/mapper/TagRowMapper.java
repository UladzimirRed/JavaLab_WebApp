package com.epam.lab.mapper;

import com.epam.lab.model.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagRowMapper implements RowMapper<Tag> {

    @Override
    public Tag mapRow(ResultSet resultSet, int i) throws SQLException {
        Tag tag = new Tag();
        tag.setTagId(resultSet.getLong("id"));
        tag.setTagName(resultSet.getString("tag_name"));
        return tag;
    }
}
