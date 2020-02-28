package service;

import com.epam.lab.dto.TagDto;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.service.TagService;
import com.epam.lab.service.impl.TagServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class TagServiceImplTest {
    TagService tagService;
    @Mock
    TagRepository tagDao;
    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        modelMapper = new ModelMapper();
        tagService = new TagServiceImpl(tagDao, modelMapper);
    }

    @Test
    void showAllDto() {
        Set<Tag> tags = new HashSet<>();
        Tag tag = new Tag("politics");
        Tag tag2 = new Tag("world");
        Tag tag3 = new Tag("lightning");
        tags.add(tag);
        tags.add(tag2);
        tags.add(tag3);
        Integer expectedListSize = 3;

        when(tagDao.getAllEntities()).thenReturn(tags);
        Set<TagDto> tagDtos = tagService.showAllDto();

        assertEquals(expectedListSize, tagDtos.size());
    }

    @Test
    void showDtoById() {
        String tagName = "world";
        when(tagDao.getEntityById(1L)).thenReturn(new Tag(tagName));
        TagDto tagDto = tagService.showDtoById(1L);
        assertEquals(tagName, tagDto.getTagName());
    }

    @Test
    void saveDto() {
        String tagName = "space";
        TagDto tagDto = new TagDto();
        tagDto.setTagName(tagName);

        Tag tag = new Tag();
        tag.setTagName(tagName);

        when(tagDao.createEntity(tag)).thenReturn(true);
        assertTrue(tagService.saveDto(tagDto));
    }

    @Test
    void removeDto() {
        Long tagId = 1L;
        when(tagDao.deleteEntity(tagId)).thenReturn(true);
        assertTrue(tagService.removeDto(tagId));
    }
}
