package com.epam.lab.repository.impl;

import com.epam.lab.exception.DaoException;
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
    public Tag getEntityById(Long tagId) {
        Tag tag = jdbcTemplate.query(SqlRequest.SQL_FIND_TAG_BY_ID, new Object[]{tagId}, new TagRowMapper()).stream().findAny().orElse(null);
        if (tag == null) {
            throw new DaoException("Tag with id: " + tagId + " was not found");
        }
        return tag;
    }

    @Override
    public List<Tag> getAllEntities() {
        List<Tag> tags = jdbcTemplate.query(SqlRequest.SQL_FIND_ALL_TAGS, new TagRowMapper());
        if (tags.stream().findAny().orElse(null) == null) {
            throw new DaoException("List of tags was not found");
        }
        return tags;
    }

    @Override
    public boolean deleteEntity(Long tagId) {
        if (jdbcTemplate.update(SqlRequest.SQL_DELETE_TAG, tagId) > 0) {
            return true;
        } else throw new DaoException("Tag with id: " + tagId + " was not delete");
    }

    @Override
    public boolean updateEntity(Tag tag) {
        if (jdbcTemplate.update(SqlRequest.SQL_UPDATE_TAG, tag.getTagName(), tag.getTagId()) > 0) {
            return true;
        } else throw new DaoException("Tag was not update");
    }

    @Override
    public boolean createEntity(Tag tag) {
        if (jdbcTemplate.update(SqlRequest.SQL_INSERT_TAG, tag.getTagName()) > 0) {
            return true;
        } else throw new DaoException("Tag was not create");
    }

    @Override
    public List<Tag> getTagsByNewsId(Long newsId) {
        List<Tag> tags = jdbcTemplate.query(SqlRequest.SQL_FIND_TAGS_BY_NEWS_ID, new Object[]{newsId}, new TagRowMapper());
        if (tags.stream().findAny().orElse(null) == null) {
            throw new DaoException("The tags of news with ID: " + newsId + "were not found");
        }
        return tags;
    }
}
