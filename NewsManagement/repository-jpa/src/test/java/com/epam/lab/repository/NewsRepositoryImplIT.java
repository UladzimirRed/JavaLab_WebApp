package com.epam.lab.repository;

import com.epam.lab.exception.DaoException;
import com.epam.lab.model.News;
import com.epam.lab.model.NewsSearchCriteria;
import com.epam.lab.configuration.RepositoryTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryTestConfig.class})
@ComponentScan("com.epam.lab")
public class NewsRepositoryImplIT {
    @Autowired
    private NewsRepository newsRepository;
    private static final String TEST_TITLE = "testTitle";
    private static final String TEST_SHORT_TEXT = "testShortText";
    private static final String TEST_FULL_TEXT = "testFullText";
    private static final String UPDATED_TEST_TITLE = "updatedTestTitle";
    private static final String UPDATED_TEST_SHORT_TEXT = "updatedTestShortText";
    private static final String UPDATED_TEST_FULL_TEXT = "updatedTestFullText";
    private static final Long EXISTENT_ID = 1L;
    private static final Long NONEXISTENT_ID = 999L;


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

    @Test(expected = DaoException.class)
    public void createEntityThrowsException() {
        News news = new News();
        newsRepository.createEntity(news);
    }

    @Test
    public void getAllEntities() {
        int countOfRowInTable = 3;
        assertNotNull(newsRepository.getAllEntities());
        assertEquals(countOfRowInTable, newsRepository.getAllEntities().size());
    }

    @Test(expected = DaoException.class)
    public void getAllEntitiesThrowsException() {
        Set<News> authors = newsRepository.getAllEntities();
        throw new DaoException();
    }

    @Test
    public void getEntityById() {
        News news = newsRepository.getEntityById(EXISTENT_ID);
        assertNotNull(news);
        assertEquals(TEST_TITLE, news.getTitle());
    }

    @Test(expected = DaoException.class)
    public void getEntityByIdThrowsException() throws DaoException {
        newsRepository.getEntityById(NONEXISTENT_ID);
    }

    @Test
    public void updateEntity() {
        News news = new News();
        news.setId(EXISTENT_ID);
        news.setTitle(UPDATED_TEST_TITLE);
        news.setShortText(UPDATED_TEST_SHORT_TEXT);
        news.setFullText(UPDATED_TEST_FULL_TEXT);
        assertTrue(newsRepository.updateEntity(news));
        News updatedNews = newsRepository.getEntityById(EXISTENT_ID);
        assertEquals(UPDATED_TEST_TITLE, updatedNews.getTitle());
        assertEquals(UPDATED_TEST_SHORT_TEXT, updatedNews.getShortText());
        assertEquals(UPDATED_TEST_FULL_TEXT, updatedNews.getFullText());
    }

    @Test
    public void deleteEntity() {
        int countOfRowInTableAfterDelete = 2;
        assertTrue(newsRepository.deleteEntity(EXISTENT_ID));
        assertEquals(countOfRowInTableAfterDelete, newsRepository.getAllEntities().size());
    }

    @Test
    public void getEntityBySearchCriteria() {
        int sizeOfNewsBySearchCriteriaSet = 0;
        List<Long> tagId = Collections.singletonList(EXISTENT_ID);
        NewsSearchCriteria newsSearchCriteria = new NewsSearchCriteria();
        newsSearchCriteria.setAuthorId(EXISTENT_ID);
        newsSearchCriteria.setTagsId(tagId);
        Set<News> newsBySearchCriteria = newsRepository.getEntityBySearchCriteria(newsSearchCriteria);
        assertEquals(sizeOfNewsBySearchCriteriaSet, newsBySearchCriteria.size());
    }
}

