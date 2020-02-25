package com.epam.lab.repository.impl;

import com.epam.lab.model.News;
import com.epam.lab.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class NewsRepositoryImpl implements NewsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public NewsRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public News getEntityById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> news = criteriaQuery.from(News.class);
        criteriaQuery.where(criteriaBuilder.equal(news.get("id"), id));
        TypedQuery<News> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<News> getAllEntities() {
        return null;
    }

    @Override
    public boolean deleteEntity(Long id) {
        return false;
    }

    @Override
    public boolean updateEntity(News news) {
        return false;
    }

    @Override
    public boolean createEntity(News news) {
        return false;
    }
}
