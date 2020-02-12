package com.epam.lab.service.impl;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.dto.NewsDto;
import com.epam.lab.dto.NewsSearchCriteria;
import com.epam.lab.exception.ServiceException;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class NewsServiceImpl implements NewsService {
    private NewsDao newsDao;
    private AuthorDao authorDao;
    private TagDao tagDao;
    private ModelMapper modelMapper;


    @Autowired
    public NewsServiceImpl(NewsDao newsDao, AuthorDao authorDao, TagDao tagDao, ModelMapper modelMapper) {
        this.newsDao = newsDao;
        this.authorDao = authorDao;
        this.tagDao = tagDao;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public List<NewsDto> showAllNews() {
        List<News> news = newsDao.getAllEntities();
        List<NewsDto> newsDtos = news.stream().map(source -> modelMapper.map(source, NewsDto.class)).collect(Collectors.toList());
        newsDtos.forEach(newsDto -> {
            assignAuthorForNews(newsDto);
            assignTagsForNews(newsDto);
        });
        return newsDtos;
    }

    @Override
    @Transactional
    public NewsDto showNewsById(Long newsId) {
        News news = newsDao.getEntityById(newsId);
        NewsDto newsDto = modelMapper.map(news, NewsDto.class);
        assignAuthorForNews(newsDto);
        assignTagsForNews(newsDto);
        return newsDto;
    }

    @Override
    @Transactional
    public boolean saveNews(NewsDto newsDto) {
        boolean isCreated = newsDao.createEntity(modelMapper.map(newsDto, News.class));
        Long authorId = newsDto.getAuthorDto().getAuthorId();
        Author author = authorDao.getEntityById(authorId);
        if (author == null) {
            AuthorDto authorDto = newsDto.getAuthorDto();
            authorDao.createEntity(modelMapper.map(authorDto, Author.class));
        }
        newsDao.linkAuthorWithNews(authorId, newsDto.getNewsId());
        List<Tag> tags = newsDto.getTags();
        tags.forEach(tag -> {
            Tag exitsTag = tagDao.getEntityById(tag.getTagId());
            if (exitsTag == null) {
                tagDao.createEntity(tag);
            }
            newsDao.linkTagWithNews(tag.getTagId(), newsDto.getNewsId());
        });
        return isCreated;
    }

    @Override
    @Transactional
    public NewsDto editNews(NewsDto newsDto) throws ServiceException {
        News news = modelMapper.map(newsDto, News.class);
        boolean isTitleEdited = true;
        boolean isShortTextEdited = true;
        boolean isFullTextEdited = true;
        if (news.getTitle() != null) {
            isTitleEdited = newsDao.updateTitle(newsDto.getTitle(), newsDto.getNewsId());
        }
        if (news.getShortText() != null){
            isShortTextEdited = newsDao.updateShortText(newsDto.getShortText(), newsDto.getNewsId());
        }
        if (news.getFullText() != null){
            isFullTextEdited = newsDao.updateFullText(newsDto.getFullText(), newsDto.getNewsId());
        }
        if (isTitleEdited & isShortTextEdited & isFullTextEdited){
            return modelMapper.map(newsDao.getEntityById(newsDto.getNewsId()), NewsDto.class);
        } else {
            throw new ServiceException("Entity was not updated");
        }
    }

    @Override
    @Transactional
    public boolean removeNews(Long newsId) {
        newsDao.unlinkAuthorIdFromNewsId(newsId);
        newsDao.unlinkTagIdFromNewsId(newsId);
        return newsDao.deleteEntity(newsId);
    }

    @Override
    @Transactional
    public List<NewsDto> searchByCriteria(NewsSearchCriteria newsSearchCriteria){
        Long authorId = newsSearchCriteria.getAuthorId();
        List<Long> tagsId = newsSearchCriteria.getTagsId();
        String forTag= "";
        String havingForTags = "";
        String forAuthor = "";
        if (authorId != null){
            forAuthor = "and author_id = " + authorId.toString();
        }
        if (tagsId != null){
            forTag = " join news_tag on news_tag.news_id = id where tag_id in (" + tagsId.stream().map(Object::toString).collect(Collectors.joining(",")) + ") ";
            havingForTags = " having count(tag_id) = " + tagsId.size();
        }
        String sql = "select id, title, short_text, full_text, creation_date, modification_date from news " +
                "join news_author on news_id = id " + forTag + forAuthor +
                " group by news.id, author_id" + havingForTags;
        List<News> news = newsDao.getEntityBySearchCriteria(sql);
        List<NewsDto> newsDtos = news.stream().map(source -> modelMapper.map(source, NewsDto.class)).collect(Collectors.toList());
        if(newsSearchCriteria.getAuthorId() != null) {
            newsDtos.forEach(newsDto -> newsDto.setAuthorDto(modelMapper.map(authorDao.getEntityById(newsSearchCriteria.getAuthorId()), AuthorDto.class)));
        }
        if (newsSearchCriteria.getTagsId() != null) {
            newsDtos.forEach(newsDto -> newsDto.setTags(tagDao.getTagsByNewsId(newsDto.getNewsId())));
        }
        return newsDtos;
    }

    public boolean assignTagsForNews(NewsDto newsDto) {
        List<Tag> tags = tagDao.getTagsByNewsId(newsDto.getNewsId());
        newsDto.setTags(tags);
        return true;
    }

    public boolean assignAuthorForNews(NewsDto newsDto){
        AuthorDto authorDto = modelMapper.map(authorDao.getAuthorByNewsId(newsDto.getNewsId()), AuthorDto.class);
        newsDto.setAuthorDto(authorDto);
        return true;
    }
}


