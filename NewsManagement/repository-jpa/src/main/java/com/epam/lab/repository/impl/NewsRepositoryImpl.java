package com.epam.lab.repository.impl;

import com.epam.lab.exception.DaoException;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.NewsSearchCriteria;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Repository
public class NewsRepositoryImpl implements NewsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public static final String NEWS_ID = "id";
    public static final String NEWS_TITLE = "title";
    public static final String NEWS_SHORT_TEXT = "shortText";
    public static final String NEWS_FULL_TEXT = "fullText";
    public static final String NEWS_MODIFICATION_DATE = "modificationDate";

    @Autowired
    public NewsRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public News getEntityById(Long id) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
            Root<News> newsRoot = criteriaQuery.from(News.class);
            criteriaQuery.where(criteriaBuilder.equal(newsRoot.get(NEWS_ID), id));
            TypedQuery<News> query = entityManager.createQuery(criteriaQuery);
            return query.getSingleResult();
        } catch (Exception ex) {
            throw new DaoException(ex);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Set<News> getAllEntities() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
            Root<News> newsRoot = criteriaQuery.from(News.class);
            criteriaQuery.select(newsRoot);
            TypedQuery<News> query = entityManager.createQuery(criteriaQuery);
            return new HashSet<>(query.getResultList());
        } catch (Exception ex) {
            throw new DaoException(ex);
        } finally {
            entityManager.close();
        }
    }

    @Override
    @Transactional
    public boolean deleteEntity(Long id) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaDelete<News> criteriaDelete = criteriaBuilder.createCriteriaDelete(News.class);
            Root<News> newsRoot = criteriaDelete.from(News.class);
            criteriaDelete.where(criteriaBuilder.equal(newsRoot.get(NEWS_ID), id));
            return entityManager.createQuery(criteriaDelete).executeUpdate() > 0;
        } catch (Exception ex) {
            throw new DaoException(ex);
        } finally {
            entityManager.close();
        }
    }

    @Override
    @Transactional
    public boolean updateEntity(News news) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<News> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(News.class);
            Root<News> newsRoot = criteriaUpdate.from(News.class);
            if (news.getTitle() != null) {
                criteriaUpdate.set(NEWS_TITLE, news.getTitle());
            }
            if (news.getShortText() != null) {
                criteriaUpdate.set(NEWS_SHORT_TEXT, news.getShortText());
            }
            if (news.getFullText() != null) {
                criteriaUpdate.set(NEWS_FULL_TEXT, news.getFullText());
            }
            criteriaUpdate.set(NEWS_MODIFICATION_DATE, Timestamp.from(Instant.now()));
            criteriaUpdate.where(criteriaBuilder.equal(newsRoot.get(NEWS_ID), news.getId()));
            return entityManager.createQuery(criteriaUpdate).executeUpdate() > 0;
        } catch (Exception ex) {
            throw new DaoException(ex);
        } finally {
            entityManager.close();
        }
    }

    @Override
    @Transactional
    public boolean createEntity(News news) {
        try {
            entityManager.persist(news);
            return true;
        } catch (PersistenceException ex) {
            throw new DaoException(ex);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Set<News> getEntityBySearchCriteria(NewsSearchCriteria newsSearchCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> newsRoot = criteriaQuery.from(News.class);
        if (newsSearchCriteria.getAuthorId() != null) {
            Join<News, Author> author = newsRoot.join("authors");
            criteriaQuery.where(criteriaBuilder.equal(author.get("id"), newsSearchCriteria.getAuthorId()));
        }
        if (newsSearchCriteria.getTagsId() != null) {
            Join<News, Tag> tags = newsRoot.join("tags");
            newsSearchCriteria.getTagsId().forEach(tagId ->
                    criteriaQuery.where(criteriaBuilder.equal(tags.get("id"), tagId))
            );
        }
        criteriaQuery.select(newsRoot);
        TypedQuery<News> query = entityManager.createQuery(criteriaQuery);
        return new HashSet<>(query.getResultList());
    }
}
