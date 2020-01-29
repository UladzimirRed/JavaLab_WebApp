package com.epam.lab.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsMapper implements RowMapper<News> {
    @Override
    public News mapRow(ResultSet resultSet, int i) throws SQLException {
        News news = new News();
        news.setNewsId(resultSet.getLong("id"));
        news.setTitle(resultSet.getString("title"));
        news.setShortText(resultSet.getString("short_text"));
        news.setFullText(resultSet.getString("full_text"));
        news.setCreationDate(resultSet.getTimestamp("creation_date"));
        news.setModificationDate(resultSet.getTimestamp("modification_date"));
        return news;
    }
}
