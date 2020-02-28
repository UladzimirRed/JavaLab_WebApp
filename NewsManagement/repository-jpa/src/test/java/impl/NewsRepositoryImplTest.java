package impl;

import com.epam.lab.model.News;
import com.epam.lab.repository.NewsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

public class NewsRepositoryImplTest {
    private EmbeddedDatabase embeddedDatabase;
    private JdbcTemplate jdbcTemplate;
    private NewsRepository newsRepository;

    @BeforeEach
    void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .setType(H2)
                .addScripts("/news/ddl.sql", "/news/dcl.sql")
                .build();
    }

    @AfterEach
    void tearDown() {
        embeddedDatabase.shutdown();
    }

//    @Test
    void createEntity() {
        News news = new News();
        news.setTitle("testTitle");
        news.setShortText("testShortText");
        news.setFullText("testFullText");
        news.setCreationDate(Timestamp.from(Instant.now()));
        news.setModificationDate(Timestamp.from(Instant.now()));
        assertTrue(newsRepository.createEntity(news));
    }

//    @Test
    void getAllEntities() {
        int countOfRowInTable = 3;
        assertNotNull(newsRepository.getAllEntities());
        assertEquals(countOfRowInTable, newsRepository.getAllEntities().size());
    }

//    @Test
    void getEntityById() {
        assertNotNull(newsRepository.getEntityById(1L));
    }

//    @Test
    void updateEntity() {
        News news = newsRepository.getEntityById(1L);
        assertNotNull(news);
        assertEquals("testTitle", news.getTitle());
        assertEquals("testShortText", news.getShortText());
        assertEquals("testFullText", news.getFullText());
    }

//    @Test
    void deleteEntity() {
        assertTrue(newsRepository.deleteEntity(1L));
    }
}

