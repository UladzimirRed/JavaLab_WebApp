package com.epam.lab.configuration;

import com.epam.lab.mapper.AuthorModelMapper;
import com.epam.lab.mapper.NewsModelMapper;
import com.epam.lab.mapper.TagModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceContextConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public AuthorModelMapper authorModelMapper(){
        return new AuthorModelMapper(modelMapper());
    }

    @Bean
    public TagModelMapper tagModelMapper(){
        return new TagModelMapper(modelMapper());
    }

    @Bean
    public NewsModelMapper newsModelMapper(){
        return new NewsModelMapper(modelMapper(), authorModelMapper(), tagModelMapper());
    }
}
