package com.epam.lab.repository.impl;

import com.epam.lab.exception.DaoException;
import com.epam.lab.mapper.NewsRowMapper;
import com.epam.lab.model.News;
import com.epam.lab.repository.NewsDao;
import com.epam.lab.repository.SqlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDaoImpl implements NewsDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public NewsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public News getEntityById(Long newsId) {
        try {
            return jdbcTemplate.query(SqlRequest.SQL_FIND_NEWS_BY_ID, new Object[]{newsId},
                    new NewsRowMapper()).stream().findAny().orElse(null);
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public List<News> getAllEntities() {
        try {
            return jdbcTemplate.query(SqlRequest.SQL_FIND_ALL_NEWS, new NewsRowMapper());
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean deleteEntity(Long newsId) {
        try {
            return jdbcTemplate.update(SqlRequest.SQL_DELETE_NEWS, newsId) > 0;
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean updateEntity(News news) {
        try {
            return jdbcTemplate.update(SqlRequest.SQL_UPDATE_NEWS,
                    news.getTitle(), news.getShortText(), news.getFullText(),
                    news.getModificationDate(), news.getId()) > 0;
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean createEntity(News news) {
        try {
            return jdbcTemplate.update(SqlRequest.SQL_INSERT_NEWS,
                    news.getTitle(), news.getShortText(), news.getFullText()) > 0;
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean updateTitle(String title, Long newsId) {
        try {
            return jdbcTemplate.update(SqlRequest.SQL_UPDATE_TITLE, title, newsId) > 0;
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean updateShortText(String shortText, Long newsId) {
        try {
            return jdbcTemplate.update(SqlRequest.SQL_UPDATE_SHORT_TEXT, shortText, newsId) > 0;
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean updateFullText(String fullText, Long newsId) {
        try {
            return jdbcTemplate.update(SqlRequest.SQL_UPDATE_FULL_TEXT, fullText, newsId) > 0;
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean linkAuthorWithNews(Long authorId, Long newsId) {
        try {
            return jdbcTemplate.update(SqlRequest.SQL_LINK_AUTHOR_ID_WITH_NEWS_ID, authorId, newsId) > 0;
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean linkTagWithNews(Long tagId, Long newsId) {
        try {
            return jdbcTemplate.update(SqlRequest.SQL_LINK_TAGS_ID_WITH_NEWS_ID, tagId, newsId) > 0;
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public List<News> getEntityBySearchCriteria(String sql) {
        try {
            return jdbcTemplate.query(sql, new NewsRowMapper());
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean unlinkAuthorIdFromNewsId(Long newsId) {
        try {
            return jdbcTemplate.update(SqlRequest.SQL_UNLINK_AUTHOR_ID_WITH_NEWS_ID, newsId) > 0;
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean unlinkTagIdFromNewsId(Long newsId) {
        try {
            return jdbcTemplate.update(SqlRequest.SQL_UNLINK_TAG_ID_WITH_NEWS_ID, newsId) > 0;
        } catch (DataAccessException exception) {
            throw new DaoException(exception);
        }
    }
}
