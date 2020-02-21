package com.epam.lab.repository.impl;

import com.epam.lab.mapper.TagRowMapper;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

public class TagDaoImplTest {
    private EmbeddedDatabase embeddedDatabase;
    private JdbcTemplate jdbcTemplate;
    private TagDao tagDao;

    @BeforeEach
    void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .setType(H2)
                .addScripts("/tag/ddl.sql", "/tag/dcl.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        tagDao = new TagDaoImpl(jdbcTemplate);
    }

    @AfterEach
    void tearDown() {
        embeddedDatabase.shutdown();
    }

    @Test()
    void createEntity() {
        Tag tag = new Tag();
        tag.setTagName("politics");
        tagDao.createEntity(tag);
        assertTrue(tagDao.createEntity(tag));
    }

    @Test
    void getAllEntities() {
        int countOfRowInTable = 10;
        assertNotNull(tagDao.getAllEntities());
        assertEquals(countOfRowInTable, tagDao.getAllEntities().size());
    }

    @Test
    void getEntityById() {
        assertNotNull(tagDao.getEntityById(1L));
    }

    @Test
    void updateEntity() {
        Tag tag = jdbcTemplate.queryForObject("select * from tags where id = 1", new TagRowMapper());
        Objects.requireNonNull(tag).setTagName("politics");

        tag = tagDao.getEntityById(1L);
        assertNotNull(tag);
        assertEquals("politics", tag.getTagName());
    }

    @Test
    void deleteEntity() {
        assertTrue(tagDao.deleteEntity(1L));
    }
}
