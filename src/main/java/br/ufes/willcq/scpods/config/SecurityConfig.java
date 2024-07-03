package br.ufes.willcq.scpods.config;

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

    // @Autowired
    // public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
    // auth.inMemoryAuthentication()
    // .withUser( "ADMIN" )
    // .password( passwordEncoder().encode( "admin.123" ) )
    // .authorities( "ROLE_ADMIN" );
    // }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() throws Exception {
        UserDetails user = User.withUsername( "ADMIN" )
                .password( passwordEncoder().encode( "admin.123" ) )
                .roles( "ADMIN" )
                .build();
        return new InMemoryUserDetailsManager( user );
    }

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
        http.authorizeHttpRequests(
                expressionIntercepterUrlRegistry -> expressionIntercepterUrlRegistry
                        .requestMatchers( "/acoes/**" ).permitAll()
                        .requestMatchers( "/objetivos/**" ).permitAll()
                        .requestMatchers( "/submissoes/**" ).authenticated()
                        .requestMatchers( "/unidades/**" ).permitAll() )
                .httpBasic( Customizer.withDefaults() );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
