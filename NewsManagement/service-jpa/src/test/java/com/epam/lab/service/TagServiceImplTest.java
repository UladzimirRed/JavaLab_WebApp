package com.epam.lab.service;

import com.epam.lab.dto.TagDto;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagRepository;
import com.epam.lab.service.impl.TagServiceJpaImpl;
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
    private TagService tagService;
    private static final Long EXISTENT_ID = 1L;
    private static final String TAG_NAME = "politics";

    @Mock
    TagRepository tagRepository;
    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        modelMapper = new ModelMapper();
        tagService = new TagServiceJpaImpl(tagRepository, modelMapper);
    }

    @Test
    void showAllDtoTest() {
        Set<Tag> tags = new HashSet<>();
        Tag tag = new Tag("politics");
        Tag tag2 = new Tag("world");
        Tag tag3 = new Tag("lightning");
        tags.add(tag);
        tags.add(tag2);
        tags.add(tag3);
        Integer expectedListSize = 3;

        when(tagRepository.getAllEntities()).thenReturn(tags);
        Set<TagDto> tagDtos = tagService.showAllDto();

        assertEquals(expectedListSize, tagDtos.size());
    }

    @Test
    void showDtoByIdTest() {
        String tagName = "world";
        when(tagRepository.getEntityById(EXISTENT_ID)).thenReturn(new Tag(tagName));
        TagDto tagDto = tagService.showDtoById(EXISTENT_ID);
        assertEquals(tagName, tagDto.getTagName());
    }

    @Test
    void saveDtoTest() {
        String tagName = "space";
        TagDto tagDto = new TagDto();
        tagDto.setTagName(tagName);

        Tag tag = new Tag();
        tag.setTagName(tagName);

        when(tagRepository.createEntity(tag)).thenReturn(true);
        assertTrue(tagService.saveDto(tagDto));
    }

    @Test
    void editDto() {
        Tag tag = new Tag();
        tag.setId(EXISTENT_ID);
        tag.setTagName(TAG_NAME);
        TagDto tagDto = new TagDto();
        tagDto.setId(EXISTENT_ID);
        tagDto.setTagName(TAG_NAME);
        when(tagRepository.updateEntity(tag)).thenReturn(true);
        when(tagRepository.getEntityById(EXISTENT_ID)).thenReturn(tag);
        TagDto updatedTagDto = tagService.editDto(tagDto);
        assertEquals(TAG_NAME, updatedTagDto.getTagName());
    }

    @Test
    void removeDtoTest() {
        Long tagId = 1L;
        when(tagRepository.deleteEntity(tagId)).thenReturn(true);
        assertTrue(tagService.removeDto(tagId));
    }
}
