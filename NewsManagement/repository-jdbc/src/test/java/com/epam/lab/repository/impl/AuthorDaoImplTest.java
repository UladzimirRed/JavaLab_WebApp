package com.epam.lab.repository.impl;

import com.epam.lab.mapper.AuthorRowMapper;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

public class AuthorDaoImplTest {
    private EmbeddedDatabase embeddedDatabase;
    private JdbcTemplate jdbcTemplate;
    private AuthorDao authorDao;

    @BeforeEach
    void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .setType(H2)
                .addScripts("/author/ddl.sql", "/author/dcl.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(embeddedDatabase);
        authorDao = new AuthorDaoImpl(jdbcTemplate);
    }

    @AfterEach
    void tearDown() {
        embeddedDatabase.shutdown();
    }

    @Test
    void createEntity() {
        Author author = new Author();
        author.setAuthorName("Joan");
        author.setAuthorSurname("Week");
        assertTrue(authorDao.createEntity(author));
    }

    @Test
    void getAllEntities() {
        int countOfRowInTable = 10;
        assertNotNull(authorDao.getAllEntities());
        assertEquals(countOfRowInTable, authorDao.getAllEntities().size());
    }

    @Test
    void getEntityById() {
        assertNotNull(authorDao.getEntityById(1L));
        assertNull(authorDao.getEntityById(0L));
    }

    @Test
    void updateEntity() {
        Author author = jdbcTemplate.queryForObject("select * from authors where id = 1", new AuthorRowMapper());
        Objects.requireNonNull(author).setAuthorName("Zoe");
        Objects.requireNonNull(author).setAuthorSurname("Roberts");

        author = authorDao.getEntityById(1L);
        assertNotNull(author);
        assertEquals("Zoe", author.getAuthorName());
        assertEquals("Roberts", author.getAuthorSurname());
    }

    @Test
    void deleteEntity() {
        assertTrue(authorDao.deleteEntity(1L));
    }
}
