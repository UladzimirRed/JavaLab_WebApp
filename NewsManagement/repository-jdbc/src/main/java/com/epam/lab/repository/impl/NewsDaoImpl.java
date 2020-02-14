package com.epam.lab.repository.impl;

import com.epam.lab.exception.DaoException;
import com.epam.lab.model.News;
import com.epam.lab.mapper.NewsRowMapper;
import com.epam.lab.repository.NewsDao;
import com.epam.lab.repository.SqlRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
        News news = jdbcTemplate.query(SqlRequest.SQL_FIND_NEWS_BY_ID, new Object[]{newsId},
                new NewsRowMapper()).stream().findAny().orElse(null);
        if (news == null) {
            throw new DaoException("News with ID: " + newsId + " was not found");
        }
        return news;
    }

    @Override
    public List<News> getAllEntities() {
        List<News> news = jdbcTemplate.query(SqlRequest.SQL_FIND_ALL_NEWS, new NewsRowMapper());
        if (news.stream().findAny().orElse(null) == null) {
            throw new DaoException("List of news was not founded");
        }
        return news;
    }

    @Override
    public boolean deleteEntity(Long newsId) {
        if (jdbcTemplate.update(SqlRequest.SQL_DELETE_NEWS, newsId) > 0) {
            return true;
        } else throw new DaoException("News with ID: " + newsId + " was not delete");
    }

    @Override
    public boolean updateEntity(News news) {
        if (jdbcTemplate.update(SqlRequest.SQL_UPDATE_NEWS,
                news.getTitle(), news.getShortText(), news.getFullText(),
                news.getModificationDate(), news.getNewsId()) > 0) {
            return true;
        } else throw new DaoException("News was not update");
    }

    @Override
    public boolean createEntity(News news) {
        if (jdbcTemplate.update(SqlRequest.SQL_INSERT_NEWS,
                news.getTitle(), news.getShortText(), news.getFullText()) > 0) {
            return true;
        } else throw new DaoException("News was not create");

    }

    @Override
    public boolean updateTitle(String title, Long newsId) {
        if (jdbcTemplate.update(SqlRequest.SQL_UPDATE_TITLE, title, newsId) > 0) {
            return true;
        } else throw new DaoException("Title of news with ID " + newsId + " was not updated");
    }

    @Override
    public boolean updateShortText(String shortText, Long newsId) {
        if (jdbcTemplate.update(SqlRequest.SQL_UPDATE_SHORT_TEXT, shortText, newsId) > 0) {
            return true;
        } else throw new DaoException("Short text of news with ID " + newsId + " was not updated");
    }

    @Override
    public boolean updateFullText(String fullText, Long newsId) {
        if (jdbcTemplate.update(SqlRequest.SQL_UPDATE_FULL_TEXT, fullText, newsId) > 0) {
            return true;
        } else throw new DaoException("Full text of news with ID " + newsId + " was not updated");
    }


    @Override
    public boolean linkAuthorWithNews(Long authorId, Long newsId) {
        if (jdbcTemplate.update(SqlRequest.SQL_LINK_AUTHOR_ID_WITH_NEWS_ID, authorId, newsId) > 0) {
            return true;
        } else
            throw new DaoException("The news with ID: " + newsId + " was not linked with the author with ID: " + authorId);
    }

    @Override
    public boolean linkTagWithNews(Long tagId, Long newsId) {
        if (jdbcTemplate.update(SqlRequest.SQL_LINK_TAGS_ID_WITH_NEWS_ID, tagId, newsId) > 0) {
            return true;
        } else
            throw new DaoException("The news with ID: " + newsId + " was not linked with the tags with ID: " + tagId);
    }

    @Override
    public List<News> getEntityBySearchCriteria(String sql) {
        List<News> news = jdbcTemplate.query(sql, new NewsRowMapper());

        if (news.stream().findAny().orElse(null) == null) {
            throw new DaoException("No entities were found by this criterion.");
        } return news;
    }

    @Override
    public boolean unlinkAuthorIdFromNewsId(Long newsId) {
        return jdbcTemplate.update(SqlRequest.SQL_UNLINK_AUTHOR_ID_WITH_NEWS_ID, newsId) > 0;
    }

    @Override
    public boolean unlinkTagIdFromNewsId(Long newsId) {
        return jdbcTemplate.update(SqlRequest.SQL_UNLINK_TAG_ID_WITH_NEWS_ID, newsId) > 0;
    }
}
