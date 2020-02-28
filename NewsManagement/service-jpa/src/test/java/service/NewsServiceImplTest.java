package service;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.dto.TagDto;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.service.NewsService;
import com.epam.lab.service.impl.NewsServiceJpaImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class NewsServiceImplTest {
    NewsService newsService;
    @Mock
    NewsRepository newsDao;
    @Mock
    AuthorRepository authorDao;
    @Mock
    TagRepository tagDao;
    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        modelMapper = new ModelMapper();
        newsService = new NewsServiceJpaImpl(newsDao, modelMapper);
    }


    @Test
    void removeDto() {
        Long newsId = 1L;
        when(newsDao.deleteEntity(newsId)).thenReturn(true);
        assertTrue(newsService.removeDto(newsId));
    }
}
