package com.epam.lab.repository.impl;

import com.epam.lab.exception.DaoException;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class NewsRepositoryImpl implements NewsRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public static final String NEWS_ID = "id";

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
    public List<News> getAllEntities() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
            Root<News> newsRoot = criteriaQuery.from(News.class);
            criteriaQuery.select(newsRoot);
            TypedQuery<News> query = entityManager.createQuery(criteriaQuery);
            return query.getResultList();
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
        return false;
    }

    @Override
    @Transactional
    public boolean createEntity(News news) {
        try {
            this.entityManager.persist(news);
            return true;
        } catch (PersistenceException ex) {
            throw new DaoException(ex);
        } finally {
            entityManager.close();
        }
    }
}
