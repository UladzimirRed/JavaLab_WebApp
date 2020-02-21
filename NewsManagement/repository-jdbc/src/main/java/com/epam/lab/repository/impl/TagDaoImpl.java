package com.epam.lab.repository.impl;

import com.epam.lab.exception.DaoException;
import com.epam.lab.mapper.TagRowMapper;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.SqlRequest;
import com.epam.lab.repository.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public class TagDaoImpl implements TagDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag getEntityById(Long tagId) {
        try {
            return jdbcTemplate.query(SqlRequest.SQL_FIND_TAG_BY_ID, new Object[]{tagId}, new TagRowMapper()).stream().findAny().orElse(null);
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public List<Tag> getAllEntities() {
        try {
            return jdbcTemplate.query(SqlRequest.SQL_FIND_ALL_TAGS, new TagRowMapper());
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean deleteEntity(Long tagId) {
        try {
            return jdbcTemplate.update(SqlRequest.SQL_DELETE_TAG, tagId) > 0;
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean updateEntity(Tag tag) {
        try {
            return jdbcTemplate.update(SqlRequest.SQL_UPDATE_TAG, tag.getTagName(), tag.getId()) > 0;
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean createEntity(Tag tag) {
        try {
            return jdbcTemplate.update(SqlRequest.SQL_INSERT_TAG, tag.getTagName()) > 0;
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public List<Tag> getTagsByNewsId(Long newsId) {
        try {
            return jdbcTemplate.query(SqlRequest.SQL_FIND_TAGS_BY_NEWS_ID, new Object[]{newsId}, new TagRowMapper());
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }
}
