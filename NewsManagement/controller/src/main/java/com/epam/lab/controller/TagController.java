package com.epam.lab.controller;

import com.epam.lab.dto.TagDto;
import com.epam.lab.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private TagService tagService;

    @Autowired
    public TagController(TagService tagService){
        this.tagService = tagService;
    }

    @PostMapping
    public boolean createTag(@RequestBody TagDto tagDto) {
        return tagService.saveTag(tagDto);
    }

    @GetMapping(value = "/{id}")
    public TagDto getTag(@PathVariable("id") Long tagId) {
        return tagService.showTagById(tagId);
    }

    @GetMapping()
    public List<TagDto> getAllTags() {
        return tagService.showAllTags();
    }

    @PutMapping(value = "{id}")
    public boolean updateTag(@RequestBody TagDto tagDto) {
        return tagService.editTag(tagDto);
    }

    @DeleteMapping(value = "{id}")
    public boolean deleteTag(@PathVariable("id") Long tagId) {
        return tagService.removeTag(tagId);
    }
}
