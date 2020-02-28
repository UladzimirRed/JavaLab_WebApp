package impl;

import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

public class AuthorRepositoryImplTest {
    private EmbeddedDatabase embeddedDatabase;
    private EntityManager entityManager;
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .setType(H2)
                .addScripts("/author/ddl.sql", "/author/dcl.sql")
                .build();
        EntityManagerFactory entityManagerFactory;
    }

    @AfterEach
    void tearDown() {
        embeddedDatabase.shutdown();
    }

//    @Test
    void createEntity() {
        Author author = new Author();
        author.setAuthorName("Joan");
        author.setAuthorSurname("Week");
        assertTrue(authorRepository.createEntity(author));
    }

//    @Test
    void getAllEntities() {
        int countOfRowInTable = 10;
        assertNotNull(authorRepository.getAllEntities());
        assertEquals(countOfRowInTable, authorRepository.getAllEntities().size());
    }

//    @Test
    void getEntityById() {
        assertNotNull(authorRepository.getEntityById(1L));
    }

//    @Test
    void updateEntity() {
        Author author = authorRepository.getEntityById(1L);
        assertNotNull(author);
        assertEquals("Zoe", author.getAuthorName());
        assertEquals("Roberts", author.getAuthorSurname());
    }

//    @Test
    void deleteEntity() {
        assertTrue(authorRepository.deleteEntity(1L));
    }
}
