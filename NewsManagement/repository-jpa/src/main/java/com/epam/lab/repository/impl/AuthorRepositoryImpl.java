package com.epam.lab.repository.impl;

import com.epam.lab.exception.DaoException;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public static final String AUTHOR_ID = "id";
    public static final String AUTHOR_FIRST_NAME = "authorName";
    public static final String AUTHOR_LAST_NAME = "authorSurname";

    @Autowired
    public AuthorRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Author getEntityById(Long id) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
            Root<Author> authorRoot = criteriaQuery.from(Author.class);
            criteriaQuery.where(criteriaBuilder.equal(authorRoot.get(AUTHOR_ID), id));
            TypedQuery<Author> query = entityManager.createQuery(criteriaQuery);
            return query.getSingleResult();
        } catch (Exception ex) {
            throw new DaoException(ex);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Author> getAllEntities() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
            Root<Author> authorRoot = criteriaQuery.from(Author.class);
            criteriaQuery.select(authorRoot);
            TypedQuery<Author> query = entityManager.createQuery(criteriaQuery);
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
            CriteriaDelete<Author> criteriaDelete = criteriaBuilder.createCriteriaDelete(Author.class);
            Root<Author> authorRoot = criteriaDelete.from(Author.class);
            criteriaDelete.where(criteriaBuilder.equal(authorRoot.get(AUTHOR_ID), id));
            return entityManager.createQuery(criteriaDelete).executeUpdate() > 0;
        } catch (Exception ex) {
            throw new DaoException(ex);
        } finally {
            entityManager.close();
        }
    }

    @Override
    @Transactional
    public boolean updateEntity(Author author) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Author> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Author.class);
            Root<Author> authorRoot = criteriaUpdate.from(Author.class);
            if (author.getAuthorName() != null) {
                criteriaUpdate.set(AUTHOR_FIRST_NAME, author.getAuthorName());
            }
            if (author.getAuthorSurname() != null) {
                criteriaUpdate.set(AUTHOR_LAST_NAME, author.getAuthorSurname());
            }
            criteriaUpdate.where(criteriaBuilder.equal(authorRoot.get(AUTHOR_ID), author.getId()));
            return entityManager.createQuery(criteriaUpdate).executeUpdate() > 0;
        } catch (Exception ex) {
            throw new DaoException(ex);
        } finally {
            entityManager.close();
        }

    }

    @Override
    @Transactional
    public boolean createEntity(Author author) {
        try {
            this.entityManager.persist(author);
            return true;
        } catch (PersistenceException ex) {
            throw new DaoException(ex);
        } finally {
            entityManager.close();
        }
    }
}
