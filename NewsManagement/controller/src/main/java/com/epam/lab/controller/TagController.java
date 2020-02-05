package com.epam.lab.controller;

import com.epam.lab.dto.TagDto;
import com.epam.lab.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService){
        this.tagService = tagService;
    }

    @PostMapping
    public boolean createAuthor(@RequestBody TagDto tagDto) {
        return tagService.saveTag(tagDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TagDto getAuthor(@PathVariable("id") long id) {
        return tagService.showTagById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TagDto> getAllTags() {
        return tagService.showAllTags();
    }

    @PutMapping(value = "{id}")
    public boolean updateAuthor(@RequestBody TagDto tagDto) {
        return tagService.editTag(tagDto);
    }

    @DeleteMapping(value = "{id}")
    public boolean deleteAuthor(@PathVariable("id") long id) {
        return tagService.removeTag(id);
    }
}
