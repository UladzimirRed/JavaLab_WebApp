package com.epam.lab.controller;

import com.epam.lab.dto.TagDto;
import com.epam.lab.service.impl.TagServiceJpaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/tags")
public class TagController {
    private TagServiceJpaImpl tagService;

    @Autowired
    public TagController(TagServiceJpaImpl tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public boolean createTag(@RequestBody TagDto tagDto) {
        return tagService.saveDto(tagDto);
    }

    @GetMapping(value = "/{id}")
    public TagDto getTag(@PathVariable("id") Long tagId) {
        return tagService.showDtoById(tagId);
    }

    @GetMapping()
    public Collection<TagDto> getAllTags() {
        return tagService.showAllDto();
    }

    @PutMapping(value = "{id}")
    public TagDto updateTag(@RequestBody TagDto tagDto) {
        return tagService.editDto(tagDto);
    }

    @DeleteMapping(value = "{id}")
    public boolean deleteTag(@PathVariable("id") Long tagId) {
        return tagService.removeDto(tagId);
    }

}
