package com.github.willcq97.scpods.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    ModelMapper modelMapper() {

        var modelmapper = new ModelMapper();
        modelmapper.getConfiguration().setAmbiguityIgnored( true );
        return modelmapper;

    }
}
