package com.epam.lab.repository.impl;

import com.epam.lab.mapper.NewsRowMapper;
import com.epam.lab.model.News;
import com.epam.lab.repository.NewsDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

public class NewsDaoImplTest {
    private EmbeddedDatabase embeddedDatabase;
    private JdbcTemplate jdbcTemplate;
    private NewsDao newsDao;

    @BeforeEach
    void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .setType(H2)
                .addScripts("/news/ddl.sql", "/news/dcl.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        newsDao = new NewsDaoImpl(jdbcTemplate);
    }

    @AfterEach
    void tearDown() {
        embeddedDatabase.shutdown();
    }

    @Test
    void createEntity() {
        News news = new News();
        news.setTitle("testTitle");
        news.setShortText("testShortText");
        news.setFullText("testFullText");
        news.setCreationDate(Timestamp.from(Instant.now()));
        news.setModificationDate(Timestamp.from(Instant.now()));
        assertTrue(newsDao.createEntity(news));
    }

    @Test
    void getAllEntities() {
        int countOfRowInTable = 3;
        assertNotNull(newsDao.getAllEntities());
        assertEquals(countOfRowInTable, newsDao.getAllEntities().size());
    }

    @Test
    void getEntityById() {
        assertNotNull(newsDao.getEntityById(1L));
    }

    @Test
    void updateEntity() {
        News news = jdbcTemplate.queryForObject("select * from news where id = 2", new NewsRowMapper());
        Objects.requireNonNull(news).setTitle("testTitle");
        Objects.requireNonNull(news).setShortText("testShortText");
        Objects.requireNonNull(news).setFullText("testFullText");
        Objects.requireNonNull(news).setCreationDate(Timestamp.from(Instant.now()));
        Objects.requireNonNull(news).setModificationDate(Timestamp.from(Instant.now()));
        news = newsDao.getEntityById(1L);
        assertNotNull(news);
        assertEquals("testTitle", news.getTitle());
        assertEquals("testShortText", news.getShortText());
        assertEquals("testFullText", news.getFullText());
    }

    @Test
    void deleteEntity() {
        assertTrue(newsDao.deleteEntity(1L));
    }
}

