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
    void saveNews() {
        NewsDto newsDto = new NewsDto();
        AuthorDto authorDto = new AuthorDto();
        List<Tag> tags = new ArrayList<>();
        newsDto.setAuthorDto(authorDto);
        newsDto.setTags(tags);

        News news = new News();

        when(newsDao.createEntity(news)).thenReturn(true);
        assertTrue(newsService.saveNews(newsDto));
    }

    @Test
    void removeNews() {
        Long newsId = 1L;
        when(newsDao.deleteEntity(newsId)).thenReturn(true);
        assertTrue(newsService.removeNews(newsId));
    }
}
