package service;

import com.epam.lab.repository.NewsRepository;
import com.epam.lab.service.NewsService;
import com.epam.lab.service.impl.NewsServiceJpaImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class NewsServiceImplTest {
    NewsService newsService;
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
    void removeDto() {
        Long newsId = 1L;
        when(newsRepository.deleteEntity(newsId)).thenReturn(true);
        assertTrue(newsService.removeDto(newsId));
    }
}
