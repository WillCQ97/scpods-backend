package br.ufes.willcq.scpods.config.docs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties( prefix = "app.swagger" )
@Getter
@Setter
public class AppSwaggerProperties {

    private String title;
    private String description;
    private String version;
    private Author author;
    private License license;

}

@Getter
@Setter
class Author {

    private String name;
    private String email;
    private String url;

}

@Getter
@Setter
class License {

    private String name;
    private String url;

}
