package br.ufes.willcq.scpods.domain.service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.ufes.willcq.scpods.api.dto.input.LoginUsuarioDTO;
import br.ufes.willcq.scpods.domain.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginService {

    private static final String URL_LOGIN = "https://acesso.ufes.br/login";
    private static final Pattern EXECUTION_INPUT_PATTERN = Pattern.compile( "<input type=\"hidden\" name=\"execution\" value=\"[a-zA-Z0-9-_]+\"/>" );

    private final RestTemplate restTemplate;

    public LoginService() {
        this.restTemplate = new RestTemplate();
    }

    public void validarLogin( LoginUsuarioDTO usuario ) {
        var executionValue = getExecutionFormValue();

        if( executionValue.isEmpty() ) {
            throw new BusinessException( "Não foi possível realizar o login!" );
        }

        if( !this.verifyUserLogin( usuario.getUsername(), usuario.getPassword(), executionValue.get() ) ) {
            throw new BusinessException( "Login informado é inválido!" );
        }

    }

    private Optional<String> getExecutionFormValue() {

        ResponseEntity<String> response = restTemplate.getForEntity( URL_LOGIN, String.class );
        HttpStatusCode statusCode = response.getStatusCode();
        String body = response.getBody();

        log.debug( "Execution Input Form Request. Status Code: " + statusCode );

        if( body != null ) {

            String html = new String( body.getBytes( StandardCharsets.UTF_8 ) );
            Matcher matcher = EXECUTION_INPUT_PATTERN.matcher( html );

            if( matcher.find() ) {
                String input = matcher.group( 0 );
                String[] inputItems = input.split( " " );
                String inputValue = inputItems[inputItems.length - 1];
                String value = inputValue.substring( 7, inputValue.length() - 3 );

                log.debug( "Execution Input Form Request. input: " + input );
                log.debug( "Execution Input Form Request. value: " + value );

                return Optional.of( value );
            }
        }
        return Optional.empty();
    }

    private boolean verifyUserLogin( String username, String password, String executionValue ) {

        // Create headers specifying that the request content type is x-www-form-urlencoded
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_FORM_URLENCODED );

        // Create a map with the form data
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add( "username", username );
        formData.add( "password", password );
        formData.add( "_eventId", "submit" );
        formData.add( "execution", executionValue );

        // Create the request entity with the headers and the form data
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>( formData, headers );

        // Make the POST request
        ResponseEntity<String> response = restTemplate.exchange( URL_LOGIN, HttpMethod.POST, requestEntity, String.class );

        // Check the response
        if( response.getStatusCode() == HttpStatus.OK ) {
            log.debug( "Login Ufes Verification. Response received: " + response.getBody() );
            return true;

        } else if( response.getStatusCode() == HttpStatus.UNAUTHORIZED ) {
            return false;

        } else {
            var msg = "Erro inesperado ao validar login! Entre em contato com o administrador do sistema com o código " + UUID.randomUUID();
            log.error( "Login Ufes Verification. Código do erro: " + response.getStatusCode() );
            log.error( msg );
            throw new BusinessException( msg );
        }
    }

}
