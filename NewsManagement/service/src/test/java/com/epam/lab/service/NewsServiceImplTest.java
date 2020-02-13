package com.epam.lab.service;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.dto.NewsDto;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.AuthorDao;
import com.epam.lab.repository.NewsDao;
import com.epam.lab.repository.TagDao;
import com.epam.lab.service.impl.NewsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class NewsServiceImplTest {
    NewsService newsService;
    @Mock
    NewsDao newsDao;
    @Mock
    AuthorDao authorDao;
    @Mock
    TagDao tagDao;
    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        modelMapper = new ModelMapper();
        newsService = new NewsServiceImpl(newsDao, authorDao, tagDao, modelMapper);
    }

    @Test
    void saveDto() {
        NewsDto newsDto = new NewsDto();
        AuthorDto authorDto = new AuthorDto();
        List<Tag> tags = new ArrayList<>();
        newsDto.setAuthorDto(authorDto);
        newsDto.setTags(tags);

        News news = new News();

        when(newsDao.createEntity(news)).thenReturn(true);
        assertTrue(newsService.saveDto(newsDto));
    }

    @Test
    void editTitle(){
        Long newsId = 1L;
        String title = "newTitle";

        News news = new News();
        news.setNewsId(newsId);
        news.setTitle(title);

        when(newsDao.updateTitle(title, newsId)).thenReturn(true);
        assertTrue(newsService.editTitle(news));
    }

    @Test
    void editShortText(){
        Long newsId = 1L;
        String shortText = "newShortText";

        News news = new News();
        news.setNewsId(newsId);
        news.setShortText(shortText);

        when(newsDao.updateShortText(shortText, newsId)).thenReturn(true);
        assertTrue(newsService.editShortText(news));
    }

    @Test
    void editFullText(){
        Long newsId = 1L;
        String fullText = "newFullText";

        News news = new News();
        news.setNewsId(newsId);
        news.setFullText(fullText);

        when(newsDao.updateFullText(fullText, newsId)).thenReturn(true);
        assertTrue(newsService.editFullText(news));
    }

    @Test
    void removeDto() {
        Long newsId = 1L;
        when(newsDao.deleteEntity(newsId)).thenReturn(true);
        assertTrue(newsService.removeDto(newsId));
    }
}
