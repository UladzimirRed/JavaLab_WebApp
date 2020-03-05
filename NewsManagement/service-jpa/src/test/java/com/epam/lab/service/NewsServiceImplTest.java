package com.epam.lab.service;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.NewsSearchCriteria;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.service.impl.NewsServiceJpaImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class NewsServiceImplTest {
    private NewsService newsService;
    private static final String TITLE = "testTitle";
    private static final String SHORT_TEXT = "testShortText";
    private static final String FULL_TEXT = "testFullText";
    private static final String UPDATED_TITLE = "updatedTestTitle";
    private static final String UPDATED_SHORT_TEXT = "updatedTestShortText";
    private static final String UPDATED_FULL_TEXT = "updatedTestFullText";
    private static final String AUTHOR_NAME = "Zoe";
    private static final String AUTHOR_SURNAME = "Roberts";
    private static final String TAG_NAME = "politics";
    private static final Long EXISTENT_ID = 1L;

    @Mock
    NewsRepository newsRepository;
    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        modelMapper = new ModelMapper();
        newsService = new NewsServiceJpaImpl(newsRepository, modelMapper);
    }

    @Test
    void showAllDto() {
        Tag tag = new Tag(TAG_NAME);
        tag.setId(EXISTENT_ID);
        Set<Tag> tags = new HashSet<>(Collections.singleton(tag));
        Author author = new Author(AUTHOR_NAME, AUTHOR_SURNAME);
        author.setId(EXISTENT_ID);
        Set<Author> authors = new HashSet<>(Collections.singletonList(author));

        Set<News> newsSet = new HashSet<>();
        News news = new News();
        news.setTitle(TITLE);
        news.setShortText(SHORT_TEXT);
        news.setFullText(FULL_TEXT);
        news.setCreationDate(Timestamp.from(Instant.now()));
        news.setModificationDate(Timestamp.from(Instant.now()));
        news.setAuthors(authors);
        news.setTags(tags);
        News news2 = new News();
        news2.setTitle(UPDATED_TITLE);
        news2.setShortText(UPDATED_SHORT_TEXT);
        news2.setFullText(UPDATED_FULL_TEXT);
        news2.setCreationDate(Timestamp.from(Instant.now()));
        news2.setModificationDate(Timestamp.from(Instant.now()));
        news2.setAuthors(authors);
        news2.setTags(tags);

        newsSet.add(news);
        newsSet.add(news2);

        Integer expectedSetSize = 2;

        when(newsRepository.getAllEntities()).thenReturn(newsSet);
        Set<NewsDto> newsDtos = newsService.showAllDto();

        assertEquals(expectedSetSize, newsDtos.size());
    }

    @Test
    void showDtoById() {
        Tag tag = new Tag(TAG_NAME);
        tag.setId(EXISTENT_ID);
        Set<Tag> tags = new HashSet<>(Collections.singleton(tag));
        Author author = new Author(AUTHOR_NAME, AUTHOR_SURNAME);
        author.setId(EXISTENT_ID);
        Set<Author> authors = new HashSet<>(Collections.singletonList(author));
        News news = new News();
        news.setTitle(TITLE);
        news.setShortText(SHORT_TEXT);
        news.setFullText(FULL_TEXT);
        news.setCreationDate(Timestamp.from(Instant.now()));
        news.setModificationDate(Timestamp.from(Instant.now()));
        news.setAuthors(authors);
        news.setTags(tags);

        when(newsRepository.getEntityById(EXISTENT_ID)).thenReturn(news);
        NewsDto newsDto = newsService.showDtoById(EXISTENT_ID);
        assertEquals(TITLE, newsDto.getTitle());
        assertEquals(SHORT_TEXT, newsDto.getShortText());
    }

    @Test
    void saveDto() {
        Tag tag = new Tag(TAG_NAME);
        tag.setId(EXISTENT_ID);
        Set<Tag> tags = new HashSet<>(Collections.singleton(tag));
        Author author = new Author(AUTHOR_NAME, AUTHOR_SURNAME);
        author.setId(EXISTENT_ID);
        Set<Author> authors = new HashSet<>(Collections.singletonList(author));

        NewsDto newsDto = new NewsDto();
        newsDto.setId(EXISTENT_ID);
        newsDto.setTitle(TITLE);
        newsDto.setShortText(SHORT_TEXT);
        newsDto.setFullText(FULL_TEXT);
        newsDto.setCreationDate(Timestamp.from(Instant.now()));
        newsDto.setModificationDate(Timestamp.from(Instant.now()));
        newsDto.setAuthor(authors);
        newsDto.setTags(tags);

        News news = new News();
        news.setId(EXISTENT_ID);
        news.setTitle(TITLE);
        news.setShortText(SHORT_TEXT);
        news.setFullText(FULL_TEXT);
        news.setCreationDate(Timestamp.from(Instant.now()));
        news.setModificationDate(Timestamp.from(Instant.now()));
        news.setAuthors(authors);
        news.setTags(tags);

        when(newsRepository.createEntity(news)).thenReturn(true);
        assertTrue(newsService.saveDto(newsDto));
    }

    @Test
    void saveDtoThrowsException() {
        News news = new News();
        NewsDto newsDto = new NewsDto();
        when(newsRepository.createEntity(news)).thenReturn(true);
        assertThrows(NullPointerException.class, () -> newsService.saveDto(newsDto));
    }

    @Test
    void editDto() {
        News news = new News();
        news.setId(EXISTENT_ID);
        news.setTitle(TITLE);
        news.setShortText(SHORT_TEXT);
        news.setFullText(FULL_TEXT);
        news.setCreationDate(Timestamp.from(Instant.now()));
        news.setModificationDate(Timestamp.from(Instant.now()));
        Tag tag = new Tag(TAG_NAME);
        tag.setId(EXISTENT_ID);
        Set<Tag> tags = new HashSet<>(Collections.singleton(tag));
        Author author = new Author(AUTHOR_NAME, AUTHOR_SURNAME);
        author.setId(EXISTENT_ID);
        Set<Author> authors = new HashSet<>(Collections.singletonList(author));
        news.setAuthors(authors);
        news.setTags(tags);

        NewsDto newsDto = new NewsDto();
        newsDto.setId(EXISTENT_ID);
        newsDto.setTitle(TITLE);
        newsDto.setShortText(SHORT_TEXT);
        newsDto.setFullText(FULL_TEXT);
        newsDto.setCreationDate(Timestamp.from(Instant.now()));
        newsDto.setModificationDate(Timestamp.from(Instant.now()));
        newsDto.setAuthor(authors);
        newsDto.setTags(tags);

        when(newsRepository.updateEntity(news)).thenReturn(true);
        when(newsRepository.getEntityById(EXISTENT_ID)).thenReturn(news);
        NewsDto updatedNewsDto = newsService.editDto(newsDto);
        assertEquals(TITLE, updatedNewsDto.getTitle());
    }

    @Test
    void removeDto() {
        when(newsRepository.deleteEntity(EXISTENT_ID)).thenReturn(true);
        assertTrue(newsService.removeDto(EXISTENT_ID));
    }

    @Test
    void searchByCriteria() {
        Integer sizeOfNewsBySearchCriteriaSet = 1;
        List<Long> tagId = Collections.singletonList(EXISTENT_ID);
        NewsSearchCriteria newsSearchCriteria = new NewsSearchCriteria();
        newsSearchCriteria.setAuthorId(EXISTENT_ID);
        newsSearchCriteria.setTagsId(tagId);

        Set<News> newsBySearchCriteria = new HashSet<>();
        News news = new News();
        news.setId(EXISTENT_ID);
        news.setTitle(TITLE);
        news.setShortText(SHORT_TEXT);
        news.setFullText(FULL_TEXT);
        news.setCreationDate(Timestamp.from(Instant.now()));
        news.setModificationDate(Timestamp.from(Instant.now()));
        Tag tag = new Tag(TAG_NAME);
        tag.setId(EXISTENT_ID);
        Set<Tag> tags = new HashSet<>(Collections.singleton(tag));
        Author author = new Author(AUTHOR_NAME, AUTHOR_SURNAME);
        author.setId(EXISTENT_ID);
        Set<Author> authors = new HashSet<>(Collections.singletonList(author));
        news.setAuthors(authors);
        news.setTags(tags);
        newsBySearchCriteria.add(news);
        when(newsRepository.getEntityBySearchCriteria(newsSearchCriteria)).thenReturn(newsBySearchCriteria);
        newsService.searchByCriteria(newsSearchCriteria);
        assertEquals(sizeOfNewsBySearchCriteriaSet, newsBySearchCriteria.size());
    }
}
