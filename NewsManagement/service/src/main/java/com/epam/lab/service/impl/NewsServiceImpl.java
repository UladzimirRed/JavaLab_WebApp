package com.epam.lab.service.impl;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.dto.NewsDto;
import com.epam.lab.mapper.NewsModelMapper;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.AuthorDao;
import com.epam.lab.repository.NewsDao;
import com.epam.lab.repository.TagDao;
import com.epam.lab.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class NewsServiceImpl implements NewsService {
    private NewsDao newsDao;
    private AuthorDao authorDao;
    private NewsModelMapper newsModelMapper;
    private ModelMapper modelMapper;
    private TagDao tagDao;

    @Autowired
    public NewsServiceImpl(NewsDao newsDao, AuthorDao authorDao, NewsModelMapper newsModelMapper, TagDao tagDao) {
        this.newsDao = newsDao;
        this.authorDao = authorDao;
        this.newsModelMapper = newsModelMapper;
        this.tagDao = tagDao;
    }

    @Override
    public NewsDto showNewsById(Long id) {
        News news = newsDao.getEntityById(id);
        Long authorId = newsDao.findAuthorIdByNewsId(id);
        Author author = authorDao.getEntityById(authorId);
        List<Long> tagsId = newsDao.findTagsIdByNewsId(id);
        List<Tag> tags = new ArrayList<>();
        for (Long i : tagsId) {
            tags.add(tagDao.getEntityById(i));
        }
        return newsModelMapper.convertToDto(news, author, tags);
    }

    @Override
    public boolean saveNews(NewsDto newsDto) {
        boolean flag = newsDao.createEntity(newsModelMapper.convertToEntity(newsDto));
        Long authorId = newsDto.getAuthorDto().getAuthorId();
        Author author = authorDao.getEntityById(authorId);
        if (author != null) {
            newsDao.linkAuthorIdWithNewsId(authorId, newsDto.getNewsId());
        } else {
            AuthorDto authorDto = newsDto.getAuthorDto();
            authorDao.createEntity(modelMapper.map(authorDto, Author.class));
        }
        List<Tag> tags = newsDto.getTags();
        if (tags != null) {
            for (Tag tag : tags) {
                newsDao.linkTagIdWithNewsId(tag.getTagId(), newsDto.getNewsId());
            }
        }
        return flag;
    }

    @Override
    public boolean editNews(NewsDto newsDto) {
        return false;
    }

    @Override
    public boolean removeNews(Long newsId) {
        newsDao.unlinkAuthorIdFromNewsId(newsId);
        newsDao.unlinkTagIdFromNewsId(newsId);
        return newsDao.deleteEntity(newsId);
    }
}


