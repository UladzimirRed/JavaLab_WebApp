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
    public static final String WAS_NOT_UPDATED = " was not updated";

    @Autowired
    public NewsServiceImpl(NewsDao newsDao, AuthorDao authorDao, TagDao tagDao, ModelMapper modelMapper) {
        this.newsDao = newsDao;
        this.authorDao = authorDao;
        this.tagDao = tagDao;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public List<NewsDto> showAllDto() {
        List<News> news = newsDao.getAllEntities();
        if (news.stream().findAny().orElse(null) == null) {
            throw new ServiceException("List of news was not founded");
        }
        List<NewsDto> newsDtos = news.stream().map(this::convertToDto).collect(Collectors.toList());
        newsDtos.forEach(newsDto -> {
            assignAuthorForNews(newsDto);
            assignTagsForNews(newsDto);
        });
        return newsDtos;
    }

    @Override
    @Transactional
    public NewsDto showDtoById(Long newsId) {
        News news = newsDao.getEntityById(newsId);
        if (news == null) {
            throw new ServiceException("News with ID: " + newsId + " was not found");
        } else {
            NewsDto newsDto = convertToDto(news);
            assignAuthorForNews(newsDto);
            assignTagsForNews(newsDto);
            return newsDto;
        }
    }

    @Override
    @Transactional
    public boolean saveDto(NewsDto newsDto) {
        News news = convertToEntity(newsDto);
        if (newsDao.createEntity(news)) {
            AuthorDto authorDto = newsDto.getAuthorDto();
            connectAuthorWithNews(authorDto, newsDto);
            List<Tag> tags = newsDto.getTags();
            tags.forEach(tag -> connectTagWithNews(tag, newsDto));
            return true;
        } else {
            throw new ServiceException("News was not create");
        }
    }

    @Transactional
    public void connectAuthorWithNews(AuthorDto authorDto, NewsDto newsDto) {
        Author author = authorDao.getEntityById(authorDto.getId());
        if (author == null) {
            authorDao.createEntity(modelMapper.map(authorDto, Author.class));
        }
        newsDao.linkAuthorWithNews(authorDto.getId(), newsDto.getId());
    }

    @Transactional
    public void connectTagWithNews(Tag tag, NewsDto newsDto) {
        Tag exitsTag = tagDao.getEntityById(tag.getId());
        if (exitsTag == null) {
            tagDao.createEntity(tag);
        }
        newsDao.linkTagWithNews(tag.getId(), newsDto.getId());
    }

    @Override
    @Transactional
    public NewsDto editDto(NewsDto newsDto) {
        News news = convertToEntity(newsDto);
        boolean isTitleEdited = editTitle(news);
        boolean isShortTextEdited = editShortText(news);
        boolean isFullTextEdited = editFullText(news);
        if (isTitleEdited && isShortTextEdited && isFullTextEdited) {
            return convertToDto(newsDao.getEntityById(newsDto.getId()));
        } else {
            throw new ServiceException("News was not updated");
        }
    }

    @Override
    public boolean editTitle(News news) {
        if (newsDao.updateTitle(news.getTitle(), news.getId())) {
            return true;
        } else throw new ServiceException("Title of news with ID " + news.getId() + WAS_NOT_UPDATED);
    }

    @Override
    public boolean editShortText(News news) {
        if (newsDao.updateShortText(news.getShortText(), news.getId())) {
            return true;
        } else throw new ServiceException("Short text of news with ID " + news.getId() + WAS_NOT_UPDATED);
    }

    @Override
    public boolean editFullText(News news) {
        if (newsDao.updateFullText(news.getFullText(), news.getId())) {
            return true;
        } else throw new ServiceException("Full text of news with ID " + news.getId() + WAS_NOT_UPDATED);
    }

    @Override
    @Transactional
    public boolean removeDto(Long newsId) {
        newsDao.unlinkAuthorIdFromNewsId(newsId);
        newsDao.unlinkTagIdFromNewsId(newsId);
        if (newsDao.deleteEntity(newsId)) {
            return true;
        } else throw new ServiceException("News with ID: " + newsId + " was not delete");
    }

    @Override
    @Transactional
    public List<NewsDto> searchByCriteria(NewsSearchCriteria newsSearchCriteria) {
        String requestForCurrentCriteria = makeRequestForCurrentCriteria(newsSearchCriteria);

        List<News> news = newsDao.getEntityBySearchCriteria(requestForCurrentCriteria);
        if (news.stream().findAny().orElse(null) == null) {
            throw new ServiceException("No entities were found by this criterion");
        }
        List<NewsDto> newsDtos = news.stream().map(this::convertToDto).collect(Collectors.toList());

        if (newsSearchCriteria.getAuthorId() != null) {
            newsDtos.forEach(this::assignAuthorForNews);
        }
        if (newsSearchCriteria.getTagsId() != null) {
            newsDtos.forEach(this::assignTagsForNews);
        }
        return newsDtos;
    }


    private String makeRequestForCurrentCriteria(NewsSearchCriteria newsSearchCriteria) {
        Long authorId = newsSearchCriteria.getAuthorId();
        List<Long> tagsId = newsSearchCriteria.getTagsId();

        String partForTag = "";
        String havingPartForTags = "";
        String partForAuthor = "";

        if (authorId != null) {
            partForAuthor = "and author_id = " + authorId.toString();
        }
        if (tagsId != null) {
            partForTag = " join news_tag on news_tag.news_id = id where tag_id in (" + tagsId.stream().map(Object::toString)
                    .collect(Collectors.joining(",")) + ") ";
            havingPartForTags = " having count(tag_id) = " + tagsId.size();
        }
        return "select id, title, short_text, full_text, creation_date, modification_date from news " +
                "join news_author on news_id = id " + partForTag + partForAuthor +
                " group by news.id, author_id" + havingPartForTags;
    }

    private void assignTagsForNews(NewsDto newsDto) {
        List<Tag> tags = tagDao.getTagsByNewsId(newsDto.getId());
        if (tags.stream().findAny().orElse(null) == null) {
            throw new ServiceException("The tags of news with ID: " + newsDto.getId() + " were not found");
        } else {
            newsDto.setTags(tags);
        }
    }

    private void assignAuthorForNews(NewsDto newsDto) {
        AuthorDto authorDto = modelMapper.map(authorDao.getAuthorByNewsId(newsDto.getId()), AuthorDto.class);
        if (authorDto == null) {
            throw new ServiceException("The author of news with ID: " + newsDto.getId() + " were not found");
        } else {
            newsDto.setAuthorDto(authorDto);
        }
    }

    public News convertToEntity(NewsDto newsDto) {
        return modelMapper.map(newsDto, News.class);
    }

    public NewsDto convertToDto(News news) {
        return modelMapper.map(news, NewsDto.class);
    }
}


