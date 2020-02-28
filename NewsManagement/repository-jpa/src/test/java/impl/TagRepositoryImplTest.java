package impl;

import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

public class TagRepositoryImplTest {
    private EmbeddedDatabase embeddedDatabase;
    private JdbcTemplate jdbcTemplate;
    private TagRepository tagRepository;

    @BeforeEach
    void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .setType(H2)
                .addScripts("/tag/ddl.sql", "/tag/dcl.sql")
                .build();
    }

    @AfterEach
    void tearDown() {
        embeddedDatabase.shutdown();
    }

//    @Test()
    void createEntity() {
        Tag tag = new Tag();
        tag.setTagName("politics");
        tagRepository.createEntity(tag);
        assertTrue(tagRepository.createEntity(tag));
    }

//    @Test
    void getAllEntities() {
        int countOfRowInTable = 10;
        assertNotNull(tagRepository.getAllEntities());
        assertEquals(countOfRowInTable, tagRepository.getAllEntities().size());
    }

//    @Test
    void getEntityById() {
        assertNotNull(tagRepository.getEntityById(1L));
    }

//    @Test
    void updateEntity() {
        Tag tag = tagRepository.getEntityById(1L);
        assertNotNull(tag);
        assertEquals("politics", tag.getTagName());
    }

//    @Test
    void deleteEntity() {
        assertTrue(tagRepository.deleteEntity(1L));
    }
}
