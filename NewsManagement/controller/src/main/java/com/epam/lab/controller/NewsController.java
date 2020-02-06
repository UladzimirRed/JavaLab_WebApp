package com.epam.lab.controller;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
public class NewsController {
    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService){
        this.newsService = newsService;
    }

    @PostMapping
    public boolean createNews(@RequestBody NewsDto newsDto){
        return newsService.saveNews(newsDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NewsDto getNews(@PathVariable("id") Long id){
        return newsService.showNewsById(id);
    }

    @DeleteMapping(value = "{id}")
    public boolean deleteNews(@PathVariable("id") Long id){
        return newsService.removeNews(id);
    }
}
