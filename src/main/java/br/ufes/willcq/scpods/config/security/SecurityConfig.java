package br.ufes.willcq.scpods.config.security;

import org.springframework.beans.factory.annotation.Autowired;
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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AdminConfigProperties admin;

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() throws Exception {
        UserDetails user = User.withUsername( admin.getUsername() )
                .password( passwordEncoder().encode( admin.getPassword() ) )
                .roles( admin.getRoles().toArray( new String[0] ) )
                .build();
        return new InMemoryUserDetailsManager( user );
    }

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
        http.authorizeHttpRequests(
                expressionIntercepterUrlRegistry -> expressionIntercepterUrlRegistry
                        .requestMatchers( "/api-docs/**", "/swagger-ui/**" ).permitAll()
                        .requestMatchers( "/acoes/**", "/objetivos/**", "/unidades/**" ).permitAll()
                        .requestMatchers( "/submissoes/**", "/usuarios/**" ).authenticated() )
                .csrf( csrf -> csrf.disable() )
                .httpBasic( Customizer.withDefaults() );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
