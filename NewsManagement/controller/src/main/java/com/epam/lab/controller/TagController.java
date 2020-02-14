package com.epam.lab.controller;

import com.epam.lab.dto.TagDto;
import com.epam.lab.exception.DaoException;
import com.epam.lab.exception.ErrorMessage;
import com.epam.lab.exception.ServiceException;
import com.epam.lab.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public boolean createTag(@RequestBody TagDto tagDto) {
        if (tagService.saveDto(tagDto)) {
            return true;
        } else throw new RuntimeException();
    }

    @GetMapping(value = "/{id}")
    public TagDto getTag(@PathVariable("id") Long tagId) {
        TagDto tagDto = tagService.showDtoById(tagId);
        if (tagDto == null) {
            throw new RuntimeException();
        }
        return tagDto;
    }

    @GetMapping()
    public List<TagDto> getAllTags() {
        List<TagDto> tagDtos = tagService.showAllDto();
        if (tagDtos == null) {
            throw new RuntimeException();
        }
        return tagDtos;
    }

    @PutMapping(value = "{id}")
    public TagDto updateTag(@RequestBody TagDto tagDto) throws ServiceException {
        TagDto updatedTagDto = tagService.editDto(tagDto);
        if (updatedTagDto == null) {
            throw new RuntimeException();
        }
        return updatedTagDto;
    }

    @DeleteMapping(value = "{id}")
    public boolean deleteTag(@PathVariable("id") Long tagId) {
        if (tagService.removeDto(tagId)) {
            return true;
        } else throw new RuntimeException();
    }

    @ExceptionHandler(DaoException.class)
    public ResponseEntity<ErrorMessage> handle(DaoException ex) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
