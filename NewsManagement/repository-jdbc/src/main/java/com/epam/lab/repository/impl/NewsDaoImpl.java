package com.epam.lab.repository.impl;

import com.epam.lab.model.News;
import com.epam.lab.mapper.NewsRowMapper;
import com.epam.lab.repository.NewsDao;
import com.epam.lab.repository.SqlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class NewsDaoImpl implements NewsDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public NewsDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public News getEntityById(Long id) {
        return jdbcTemplate.queryForObject(SqlRequest.SQL_FIND_NEWS_BY_ID, new Object[]{id}, new NewsRowMapper());
    }

    @Override
    public List<News> getAllEntities() {
        return jdbcTemplate.query(SqlRequest.SQL_FIND_ALL_NEWS, new NewsRowMapper());
    }

    @Override
    public boolean deleteEntity(Long id) {
        return jdbcTemplate.update(SqlRequest.SQL_DELETE_NEWS, id) > 0;
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
                news.getTitle(), news.getShortText(), news.getFullText()) > 0;
    }

    @Override
    public boolean updateTitle(String title, Long newsId) {
        return jdbcTemplate.update(SqlRequest.SQL_UPDATE_TITLE, title, newsId) > 0;
    }

    @Override
    public boolean updateShortText(String shortText, Long newsId) {
        return jdbcTemplate.update(SqlRequest.SQL_UPDATE_SHORT_TEXT, shortText, newsId) > 0;
    }

    @Override
    public boolean updateFullText(String fullText, Long newsId) {
        return jdbcTemplate.update(SqlRequest.SQL_UPDATE_FULL_TEXT, fullText, newsId) > 0;
    }


    @Override
    public boolean linkAuthorWithNews(Long authorId, Long newsId) {
        return jdbcTemplate.update(SqlRequest.SQL_LINK_AUTHOR_ID_WITH_NEWS_ID, authorId, newsId) > 0;
    }

    @Override
    public boolean linkTagWithNews(Long tagId, Long newsId) {
        return jdbcTemplate.update(SqlRequest.SQL_LINK_TAGS_ID_WITH_NEWS_ID, tagId, newsId) > 0;
    }

    @Override
    public List<News> getEntityBySearchCriteria(Long authorId, List<Long> tagsId) {
        String forTag= "";
        String forAuthor = "";

        if (authorId != null){
            forAuthor = "on news_id = id ";
        }
        if (tagsId != null){
            forTag = "join news_tag on news_tag.news_id = id where tag_id in (" + tagsId.stream().map(Object::toString).collect(Collectors.joining(",")) + ")";
        }
        return jdbcTemplate.query("select id, title, short_text, full_text, creation_date, modification_date from news " +
                "join news_author " + forAuthor + " " + forTag + " and author_id = " + authorId.toString() +
                " group by news.id, author_id", new NewsRowMapper());
    }

    @Override
    public boolean unlinkAuthorIdFromNewsId(Long newsId) {
        return jdbcTemplate.update(SqlRequest.SQL_UNLINK_AUTHOR_ID_WITH_NEWS_ID, newsId) > 0;
    }

    @Override
    public boolean unlinkTagIdFromNewsId (Long newsId){
        return jdbcTemplate.update(SqlRequest.SQL_UNLINK_TAG_ID_WITH_NEWS_ID, newsId) > 0;
    }
}
