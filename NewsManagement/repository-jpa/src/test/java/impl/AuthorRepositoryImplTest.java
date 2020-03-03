package impl;

import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import config.RepositoryTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryTestConfig.class})
@ComponentScan("com.epam.lab")
public class AuthorRepositoryImplTest {
    @Autowired
    private AuthorRepository authorRepository;

    public static final String TEST_NAME = "Zoe";
    public static final String TEST_SURNAME = "Roberts";
    public static final String UPDATED_TEST_NAME = "ZoeUPD";
    public static final String UPDATED_TEST_SURNAME = "RobertsUPD";

    @Test
    public void createEntity() {
        Author author = new Author();
        author.setAuthorName(TEST_NAME);
        author.setAuthorSurname(TEST_SURNAME);
        assertTrue(authorRepository.createEntity(author));
    }

    @Test
    public void getAllEntities() {
        int countOfRowInTable = 10;
        assertNotNull(authorRepository.getAllEntities());
        assertEquals(countOfRowInTable, authorRepository.getAllEntities().size());
    }

    @Test
    public void getEntityById() {
        Author author = authorRepository.getEntityById(1L);
        assertNotNull(author);
        assertEquals(TEST_NAME, author.getAuthorName());
        assertEquals(TEST_SURNAME, author.getAuthorSurname());
    }

    @Test
    public void updateEntity() {
        Author author = new Author();
        author.setId(1L);
        author.setAuthorName(UPDATED_TEST_NAME);
        author.setAuthorSurname(UPDATED_TEST_SURNAME);
        assertTrue(authorRepository.updateEntity(author));
        Author updatedAuthor = authorRepository.getEntityById(1L);
        assertEquals(UPDATED_TEST_NAME, updatedAuthor.getAuthorName());
        assertEquals(UPDATED_TEST_SURNAME, updatedAuthor.getAuthorSurname());
    }

    @Test
    public void deleteEntity() {
        int countOfRowInTableAfterDelete = 9;
        assertTrue(authorRepository.deleteEntity(1L));
        assertEquals(countOfRowInTableAfterDelete, authorRepository.getAllEntities().size());
    }
}
