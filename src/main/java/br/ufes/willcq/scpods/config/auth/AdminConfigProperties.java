package br.ufes.willcq.scpods.config.auth;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties( prefix = "app.admin" )
@Getter
@Setter
public class AdminConfigProperties {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotEmpty
    private List<String> roles;

}
