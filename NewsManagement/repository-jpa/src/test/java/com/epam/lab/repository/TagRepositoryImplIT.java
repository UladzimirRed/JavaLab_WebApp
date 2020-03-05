package com.epam.lab.repository;

import com.epam.lab.exception.DaoException;
import com.epam.lab.model.Tag;
import com.epam.lab.configuration.RepositoryTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryTestConfig.class})
@ComponentScan("com.epam.lab")
public class TagRepositoryImplIT {
    @Autowired
    private TagRepository tagRepository;
    private static final Long EXISTENT_ID = 1L;
    private static final Long NONEXISTENT_ID = 999L;
    private static final String UPDATED_TAG_NAME = "politicsUPD";

    @Test
    public void createEntity() {
        Tag tag = new Tag();
        tag.setTagName("politics");
        assertTrue(tagRepository.createEntity(tag));
    }

    @Test(expected = DaoException.class)
    public void createEntityThrowsException() {
        Tag tag = new Tag();
        tagRepository.createEntity(tag);
    }

    @Test
    public void getAllEntities() {
        int countOfRowInTable = 10;
        assertNotNull(tagRepository.getAllEntities());
        assertEquals(countOfRowInTable, tagRepository.getAllEntities().size());
    }

    @Test
    public void getEntityById() {
        assertNotNull(tagRepository.getEntityById(EXISTENT_ID));
    }

    @Test(expected = DaoException.class)
    public void getEntityByIdThrowsException() throws DaoException {
        tagRepository.getEntityById(NONEXISTENT_ID);
    }

    @Test
    public void updateEntity() {
        Tag tag = new Tag();
        tag.setId(EXISTENT_ID);
        tag.setTagName(UPDATED_TAG_NAME);
        assertTrue(tagRepository.updateEntity(tag));
        Tag updatedTag = tagRepository.getEntityById(EXISTENT_ID);
        assertEquals(UPDATED_TAG_NAME, updatedTag.getTagName());
    }

    @Test(expected = DaoException.class)
    public void updateEntityThrowsException() throws DaoException {
        Tag tag = new Tag();
        tagRepository.updateEntity(tag);
    }

    @Test
    public void deleteEntity() {
        int countOfRowInTableAfterDelete = 9;
        assertTrue(tagRepository.deleteEntity(EXISTENT_ID));
        assertEquals(countOfRowInTableAfterDelete, tagRepository.getAllEntities().size());
    }
}
