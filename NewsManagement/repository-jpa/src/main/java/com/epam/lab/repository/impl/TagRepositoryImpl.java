package com.epam.lab.repository.impl;

import com.epam.lab.exception.DaoException;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class TagRepositoryImpl implements TagRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "tagName";

    @Autowired
    public TagRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }


    @Override
    public Tag getEntityById(Long id) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
            Root<Tag> tagRoot = criteriaQuery.from(Tag.class);
            criteriaQuery.where(criteriaBuilder.equal(tagRoot.get(TAG_ID), id));
            TypedQuery<Tag> query = entityManager.createQuery(criteriaQuery);
            return query.getSingleResult();
        } catch (Exception ex) {
            throw new DaoException(ex);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Set<Tag> getAllEntities() {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
            Root<Tag> tagRoot = criteriaQuery.from(Tag.class);
            criteriaQuery.select(tagRoot);
            TypedQuery<Tag> query = entityManager.createQuery(criteriaQuery);
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
            CriteriaDelete<Tag> criteriaDelete = criteriaBuilder.createCriteriaDelete(Tag.class);
            Root<Tag> tagRoot = criteriaDelete.from(Tag.class);
            criteriaDelete.where(criteriaBuilder.equal(tagRoot.get(TAG_ID), id));
            return entityManager.createQuery(criteriaDelete).executeUpdate() > 0;
        } catch (Exception ex) {
            throw new DaoException(ex);
        } finally {
            entityManager.close();
        }
    }

    @Override
    @Transactional
    public boolean updateEntity(Tag tag) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Tag> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Tag.class);
            Root<Tag> authorRoot = criteriaUpdate.from(Tag.class);
            if (tag.getTagName() != null) {
                criteriaUpdate.set(TAG_NAME, tag.getTagName());
            }
            criteriaUpdate.where(criteriaBuilder.equal(authorRoot.get(TAG_ID), tag.getId()));
            return entityManager.createQuery(criteriaUpdate).executeUpdate() > 0;
        } catch (Exception ex) {
            throw new DaoException(ex);
        } finally {
            entityManager.close();
        }
    }

    @Override
    @Transactional
    public boolean createEntity(Tag tag) {
        try {
            this.entityManager.persist(tag);
            return true;
        } catch (PersistenceException ex) {
            throw new DaoException(ex);
        } finally {
            entityManager.close();
        }
    }
}
