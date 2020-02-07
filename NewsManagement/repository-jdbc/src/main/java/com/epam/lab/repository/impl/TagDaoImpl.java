package com.epam.lab.repository.impl;

import com.epam.lab.mapper.TagRowMapper;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.SqlRequest;
import com.epam.lab.repository.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag getEntityById(Long id) {
        return jdbcTemplate.query(SqlRequest.SQL_FIND_TAG_BY_ID, new Object[] {id}, new TagRowMapper()).stream().findAny().orElse(null);
    }

    @Override
    public List<Tag> getAllEntities() {
        return jdbcTemplate.query(SqlRequest.SQL_FIND_ALL_TAGS, new TagRowMapper());
    }

    @Override
    public boolean deleteEntity(Long id) {
        return jdbcTemplate.update(SqlRequest.SQL_DELETE_TAG, id) > 0;
    }

    @Override
    public boolean updateEntity(Tag tag) {
        return jdbcTemplate.update(SqlRequest.SQL_UPDATE_TAG, tag.getTagName(), tag.getTagId()) > 0;
    }

    @Override
    public boolean createEntity(Tag tag) {
        return jdbcTemplate.update(SqlRequest.SQL_INSERT_TAG, tag.getTagName()) > 0;
    }

    @Override
    public List<Tag> getTagsByNewsId(Long newsId) {
        return jdbcTemplate.query(SqlRequest.SQL_FIND_TAGS_BY_NEWS_ID, new Object[]{newsId}, new TagRowMapper());
    }
}
