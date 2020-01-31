package com.epam.lab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/NewsManagement")

public class IController {
    @GetMapping
    public String getTestData() {
        return "text message";
    }
}