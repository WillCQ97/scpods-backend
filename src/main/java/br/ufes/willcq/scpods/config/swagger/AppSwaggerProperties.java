package br.ufes.willcq.scpods.config.swagger;

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
    private AuthorProperties author;
    private LicenseProperties license;

}

@Getter
@Setter
class AuthorProperties {

    private String name;
    private String email;
    private String url;

}

@Getter
@Setter
class LicenseProperties {

    private String name;
    private String url;

}
