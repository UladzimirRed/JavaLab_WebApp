package com.epam.lab.controller;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.dto.NewsSearchCriteria;
import com.epam.lab.exception.DaoException;
import com.epam.lab.exception.ErrorMessage;
import com.epam.lab.exception.ServiceException;
import com.epam.lab.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping
    public boolean createNews(@RequestBody NewsDto newsDto) {
        if (newsService.saveDto(newsDto)) {
            return true;
        } else throw new RuntimeException();
    }

    @GetMapping(value = "/{id}")
    public NewsDto getNews(@PathVariable("id") Long id) {
        NewsDto newsDto = newsService.showDtoById(id);
        if (newsDto == null) {
            throw new RuntimeException();
        }
        return newsDto;
    }

    @GetMapping
    public List<NewsDto> getAllNews() {
        List<NewsDto> newsDtos = newsService.showAllDto();
        if (newsDtos == null) {
            throw new RuntimeException();
        }
        return newsDtos;
    }

    @GetMapping("/search")
    public List<NewsDto> getNewsByCriteria(@ModelAttribute NewsSearchCriteria newsSearchCriteria) {
        List<NewsDto> newsDtos = newsService.searchByCriteria(newsSearchCriteria);
        if (newsDtos == null) {
            throw new RuntimeException();
        }
        return newsDtos;
    }

    @PutMapping(value = "{id}")
    public NewsDto updateNews(@RequestBody NewsDto newsDto) throws ServiceException {
        NewsDto updatedNewsDto = newsService.editDto(newsDto);
        if (updatedNewsDto == null) {
            throw new RuntimeException();
        }
        return updatedNewsDto;
    }

    @DeleteMapping(value = "{id}")
    public boolean deleteNews(@PathVariable("id") Long id) {
        if (newsService.removeDto(id)) {
            return true;
        } else throw new RuntimeException();
    }

    @ExceptionHandler(DaoException.class)
    public ResponseEntity<ErrorMessage> handle(DaoException ex) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
