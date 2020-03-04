package impl;

import com.epam.lab.exception.DaoException;
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
public class AuthorRepositoryImplIT {
    @Autowired
    private AuthorRepository authorRepository;

    private static final String TEST_NAME = "Zoe";
    private static final String TEST_SURNAME = "Roberts";
    private static final String UPDATED_TEST_NAME = "ZoeUPD";
    private static final String UPDATED_TEST_SURNAME = "RobertsUPD";
    private static final Long EXISTENT_ID = 1L;
    private static final Long NONEXISTENT_ID = 999L;

    @Test
    public void createEntity() {
        Author author = new Author();
        author.setAuthorName(TEST_NAME);
        author.setAuthorSurname(TEST_SURNAME);
        assertTrue(authorRepository.createEntity(author));
    }

    @Test(expected = DaoException.class)
    public void createEntityThrowsException() {
        Author author = new Author();
        authorRepository.createEntity(author);
    }

    @Test
    public void getAllEntities() {
        int countOfRowInTable = 10;
        assertNotNull(authorRepository.getAllEntities());
        assertEquals(countOfRowInTable, authorRepository.getAllEntities().size());
    }

    @Test
    public void getEntityById() {
        Author author = authorRepository.getEntityById(EXISTENT_ID);
        assertNotNull(author);
        assertEquals(TEST_NAME, author.getAuthorName());
        assertEquals(TEST_SURNAME, author.getAuthorSurname());
    }

    @Test(expected = DaoException.class)
    public void getEntityByIdThrowsException() throws DaoException {
        authorRepository.getEntityById(NONEXISTENT_ID);
    }

    @Test
    public void updateEntity() {
        Author author = new Author();
        author.setId(EXISTENT_ID);
        author.setAuthorName(UPDATED_TEST_NAME);
        author.setAuthorSurname(UPDATED_TEST_SURNAME);
        assertTrue(authorRepository.updateEntity(author));
        Author updatedAuthor = authorRepository.getEntityById(EXISTENT_ID);
        assertEquals(UPDATED_TEST_NAME, updatedAuthor.getAuthorName());
        assertEquals(UPDATED_TEST_SURNAME, updatedAuthor.getAuthorSurname());
    }

    @Test(expected = DaoException.class)
    public void updateEntityThrowsException() throws DaoException {
        Author author = new Author();
        authorRepository.updateEntity(author);
    }

    @Test
    public void deleteEntity() {
        int countOfRowInTableAfterDelete = 9;
        assertTrue(authorRepository.deleteEntity(EXISTENT_ID));
        assertEquals(countOfRowInTableAfterDelete, authorRepository.getAllEntities().size());
    }
}
