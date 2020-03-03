package impl;

import com.epam.lab.model.News;
import com.epam.lab.repository.NewsRepository;
import config.RepositoryTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryTestConfig.class})
@ComponentScan("com.epam.lab")
public class NewsRepositoryImplTest {
    @Autowired
    private NewsRepository newsRepository;
    public static final String TEST_TITLE = "testTitle";
    public static final String TEST_SHORT_TEXT = "testShortText";
    public static final String TEST_FULL_TEXT = "testFullText";
    public static final String UPDATED_TEST_TITLE = "updatedTestTitle";
    public static final String UPDATED_TEST_SHORT_TEXT = "updatedTestShortText";
    public static final String UPDATED_TEST_FULL_TEXT = "updatedTestFullText";


    @Test
    public void createEntity() {
        News news = new News();
        news.setTitle(TEST_TITLE);
        news.setShortText(TEST_SHORT_TEXT);
        news.setFullText(TEST_FULL_TEXT);
        news.setCreationDate(Timestamp.from(Instant.now()));
        news.setModificationDate(Timestamp.from(Instant.now()));
        assertTrue(newsRepository.createEntity(news));
    }

    @Test
    public void getAllEntities() {
        int countOfRowInTable = 3;
        assertNotNull(newsRepository.getAllEntities());
        assertEquals(countOfRowInTable, newsRepository.getAllEntities().size());
    }

    @Test
    public void getEntityById() {
        News news = newsRepository.getEntityById(1L);
        assertNotNull(news);
        assertEquals(TEST_TITLE, news.getTitle());
    }

    @Test
    public void updateEntity() {
        News news = new News();
        news.setId(1L);
        news.setTitle(UPDATED_TEST_TITLE);
        news.setShortText(UPDATED_TEST_SHORT_TEXT);
        news.setFullText(UPDATED_TEST_FULL_TEXT);
        assertTrue(newsRepository.updateEntity(news));
        News updatedNews = newsRepository.getEntityById(1L);
        assertEquals(UPDATED_TEST_TITLE, updatedNews.getTitle());
        assertEquals(UPDATED_TEST_SHORT_TEXT, updatedNews.getShortText());
        assertEquals(UPDATED_TEST_FULL_TEXT, updatedNews.getFullText());
    }

    @Test
    public void deleteEntity() {
        int countOfRowInTableAfterDelete = 2;
        assertTrue(newsRepository.deleteEntity(1L));
        assertEquals(countOfRowInTableAfterDelete, newsRepository.getAllEntities().size());
    }
}

