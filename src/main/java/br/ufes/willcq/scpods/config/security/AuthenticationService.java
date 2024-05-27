package br.ufes.willcq.scpods.config.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import jakarta.servlet.http.HttpServletRequest;

/*
 * We delegate the evaluation of the API Key and constructing the 
 * Authentication object to the AuthenticationService class
 * */
public class AuthenticationService {

    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
    private static final String AUTH_TOKEN = "mytoken";

    public static Authentication getAuthentication( HttpServletRequest request ) {
        String apiKey = request.getHeader( AUTH_TOKEN_HEADER_NAME );

        if( apiKey == null || !apiKey.equals( AUTH_TOKEN ) ) {
            throw new BadCredentialsException( "API Key inválido!" );
        }

        return new ApiKeyAuthentication( apiKey, AuthorityUtils.NO_AUTHORITIES );
    }

}
