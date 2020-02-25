package com.epam.lab.repository.impl;

import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TagRepositoryImpl implements TagRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TagRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }


    @Override
    public Tag getEntityById(Long id) {
        return null;
    }

    @Override
    public List<Tag> getAllEntities() {
        return null;
    }

    @Override
    public boolean deleteEntity(Long id) {
        return false;
    }

    @Override
    public boolean updateEntity(Tag tag) {
        return false;
    }

    @Override
    public boolean createEntity(Tag tag) {
        return false;
    }
}
