package br.ufes.willcq.scpods.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

/*
 * We delegate the evaluation of the API Key and constructing the 
 * Authentication object to the AuthenticationService class
 * */
@Component
public class AuthenticationService {

    @Value( "${api.key}" )
    private String AUTH_API_KEY;

    public Authentication getAuthentication( HttpServletRequest request ) {
        String apiKey = request.getHeader( "X-AUTH-API-KEY" );

        if( apiKey == null || !apiKey.equals( AUTH_API_KEY ) ) {
            throw new BadCredentialsException( "API Key inválido!" );
        }

        return new ApiKeyAuthentication( apiKey, AuthorityUtils.NO_AUTHORITIES );
    }

}
