package impl;

import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagRepository;
import config.RepositoryTestConfig;
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
public class TagRepositoryImplTest {
    @Autowired
    private TagRepository tagRepository;

    @Test
    public void createEntity() {
        Tag tag = new Tag();
        tag.setTagName("politics");
        assertTrue(tagRepository.createEntity(tag));
    }

    @Test
    public void getAllEntities() {
        int countOfRowInTable = 10;
        assertNotNull(tagRepository.getAllEntities());
        assertEquals(countOfRowInTable, tagRepository.getAllEntities().size());
    }

    @Test
    public void getEntityById() {
        assertNotNull(tagRepository.getEntityById(1L));
    }

    @Test
    public void updateEntity() {
        Tag tag = tagRepository.getEntityById(1L);
        assertNotNull(tag);
        assertEquals("politics", tag.getTagName());
    }

    @Test
    public void deleteEntity() {
        assertTrue(tagRepository.deleteEntity(1L));
    }
}
