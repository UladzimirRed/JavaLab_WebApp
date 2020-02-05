package com.epam.lab.service.impl;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.mapper.NewsModelMapper;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.repository.AuthorDao;
import com.epam.lab.repository.NewsDao;
import com.epam.lab.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private NewsModelMapper newsModelMapper;


    @Override
    public List<NewsDto> showAllNews() {
        return null;
    }

    @Override
    public NewsDto showNewsById(Long id) {
        News news = newsDao.getEntityById(id);
        Long authorId = newsDao.findAuthorIdByNewsId(id);
        Author author = authorDao.getEntityById(authorId);
        return newsModelMapper.convertToDto(news, author);
    }

    @Override
    public boolean saveNews(NewsDto newsDto) {
        return false;
    }

    @Override
    public boolean editNews(NewsDto newsDto) {
        return false;
    }

    @Override
    public boolean removeNews(Long id) {
        return false;
    }
}
