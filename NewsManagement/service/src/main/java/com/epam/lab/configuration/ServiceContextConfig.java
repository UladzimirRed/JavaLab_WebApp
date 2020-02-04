package com.epam.lab.configuration;

import com.epam.lab.mapper.AuthorModelMapper;
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
        return new AuthorModelMapper();
    }

}
