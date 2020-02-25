package com.epam.lab.repository.impl;

import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
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
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Autowired
    public AuthorRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Author getEntityById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> author = criteriaQuery.from(Author.class);
        criteriaQuery.where(criteriaBuilder.equal(author.get("id"), id));
        TypedQuery<Author> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<Author> getAllEntities() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> cq = cb.createQuery(Author.class);
        Root<Author> rootEntry = cq.from(Author.class);
        CriteriaQuery<Author> all = cq.select(rootEntry);
        TypedQuery<Author> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public boolean deleteEntity(Long id) {
        return false;
    }

    @Override
    public boolean updateEntity(Author author) {
        return false;
    }

    @Override
    public boolean createEntity(Author author) {
        return false;
    }
}
