package br.ufes.willcq.scpods.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RestServiceCorsApplication {

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {

            // TODO: refinar o acesso a estes métodos
            @Override
            public void addCorsMappings( @NonNull CorsRegistry registry ) {
                registry.addMapping( "/**" )
                        .allowedOrigins( "http://localhost", "http://localhost:80", "http://localhost:3000", "http://localhost:8080", "http://localhost:8001" )
                        .allowedMethods( "HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS" );
            }
        };
    }
}
