package com.github.willcq97.scpods.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bedatadriven.jackson.datatype.jts.JtsModule;

@Configuration
public class JacksonConfig {

    @Bean
    JtsModule jtsModule() {
        return new JtsModule();
    }
}
