package com.github.willcq97.scpods.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.jspecify.annotations.NonNull;

@Configuration
public class RestServiceCorsApplication {

    @Bean
    WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings( @NonNull CorsRegistry registry ) {
                registry.addMapping( "/**" )
                        .allowedOriginPatterns( "*" )
                        .allowedMethods( "HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS" );
            }
        };
    }
}
