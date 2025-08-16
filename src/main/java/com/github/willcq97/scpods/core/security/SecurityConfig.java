package com.github.willcq97.scpods.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final AdminConfigProperties admin;

    @Bean
    InMemoryUserDetailsManager userDetailsManager() throws Exception {
        UserDetails user = User.withUsername( admin.getUsername() )
                .password( passwordEncoder().encode( admin.getPassword() ) )
                .roles( admin.getRoles().toArray( new String[0] ) )
                .build();
        return new InMemoryUserDetailsManager( user );
    }

    @Bean
    SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
        http.authorizeHttpRequests(
                expressionIntercepterUrlRegistry -> expressionIntercepterUrlRegistry
                        .requestMatchers( "/api-docs/**", "/swagger-ui/**" ).permitAll()
                        .requestMatchers( "/acoes/**", "/lotacoes/**", "/usuarios/validar-login-ufes", "/objetivos/**", "/unidades/**" ).permitAll()
                        .requestMatchers( "/submissoes/**", "/usuarios/**" ).authenticated() )
                .csrf( csrf -> csrf.disable() )
                .httpBasic( Customizer.withDefaults() );
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
