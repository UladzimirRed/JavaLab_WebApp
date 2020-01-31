package com.epam.lab.repository.impl;

import com.epam.lab.model.News;
import com.epam.lab.mapper.NewsMapper;
import com.epam.lab.repository.NewsDao;
import com.epam.lab.repository.SqlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class NewsDaoImpl implements NewsDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public NewsDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public News getEntityById(long id) {
        return jdbcTemplate.queryForObject(SqlRequest.SQL_FIND_NEWS_BY_ID, new Object[] {id}, new NewsMapper());
    }

    @Override
    public List<News> getAllEntities() {
        return jdbcTemplate.query(SqlRequest.SQL_FIND_ALL_NEWS, new NewsMapper());
    }

    @Override
    public boolean deleteEntity(News news) {
        return jdbcTemplate.update(SqlRequest.SQL_DELETE_NEWS, news.getNewsId()) > 0;
    }

    @Override
    public boolean updateEntity(News news) {
        return jdbcTemplate.update(SqlRequest.SQL_UPDATE_NEWS,
                news.getTitle(), news.getShortText(), news.getFullText(),
                news.getModificationDate(), news.getNewsId()) > 0;
    }

    @Override
    public boolean createEntity(News news) {
        return jdbcTemplate.update(SqlRequest.SQL_INSERT_NEWS,
                news.getTitle(), news.getShortText(), news.getFullText(), news.getCreationDate(), news.getModificationDate()) > 0;
    }
}
