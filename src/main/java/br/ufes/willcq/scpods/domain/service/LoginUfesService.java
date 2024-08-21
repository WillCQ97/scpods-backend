package br.ufes.willcq.scpods.domain.service;

import java.util.Optional;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.ufes.willcq.scpods.api.dto.input.LoginUsuarioDTO;
import br.ufes.willcq.scpods.domain.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginUfesService {

    private static final String URL_LOGIN = "https://acesso.ufes.br/login";
    private static final Pattern EXECUTION_INPUT_PATTERN = Pattern.compile( "<input type=\"hidden\" name=\"execution\" value=\"[a-zA-Z0-9-_]+\"/>" );
    private final RestTemplate restTemplate;

    public LoginUfesService() {
        this.restTemplate = new RestTemplate();
    }

    public void validarLoginUfes( LoginUsuarioDTO usuario ) {
        var executionValue = getExecutionFormValue();

        if( executionValue.isEmpty() ) {
            throw new BusinessException( "Não foi possível realizar o login!" );
        }

        if( !this.verifyUserLogin( usuario.getUsername(), usuario.getPassword(), executionValue.get() ) ) {
            throw new BusinessException( "Login informado é inválido!" );
        }

    }

    /**
     * Retrieves the HTML content of the login page and processes it to extract the hidden input field containing the execution value. This value is required to authenticate a user by making a subsequent request.
     *
     * @return the extracted execution value needed for login validation
     */

    private Optional<String> getExecutionFormValue() {

        ResponseEntity<String> response = restTemplate.getForEntity( URL_LOGIN, String.class );
        HttpStatusCode statusCode = response.getStatusCode();
        log.debug( "Execution Input Form Request. Status Code: {}", statusCode );

        String body = response.getBody();
        Matcher matcher = EXECUTION_INPUT_PATTERN.matcher( body );

        if( matcher.find() ) {
            String input = matcher.group( 0 ); // the first item will be the desired input
            log.debug( "Execution Input Form Request. input: {}", input );

            // Use regex capturing group directly or validate input length before substring
            String[] inputItems = input.split( " " );

            if( inputItems.length > 0 ) {
                String inputValue = inputItems[inputItems.length - 1]; // value will be the last item on split

                if( inputValue.length() >= 10 ) {
                    String value = inputValue.substring( 7, inputValue.length() - 3 );
                    log.debug( "Execution Input Form Request. value: {}", value );
                    return Optional.of( value );

                }
            }
        }

        return Optional.empty();
    }

    /**
     * Verifies the user's login credentials by sending an HTTP POST request to the login URL with the provided username, password, and execution value. The method constructs the request with the appropriate headers and form data, and then sends the request to validate the login.
     *
     * The method expects the response to be HTTP 200 OK for a successful login. If the response status is 401 Unauthorized, it indicates incorrect login credentials. Any other exceptions are propagated further.
     *
     * @param username       the user's username to be authenticated
     * @param password       the user's password to be authenticated
     * @param executionValue a unique value extracted from the login page required for login validation
     * @return {@code true} if the login is successful (HTTP 200 OK), {@code false} if the login fails due to incorrect credentials (HTTP 401 Unauthorized)
     * @throws Exception if any other error occurs during the HTTP request
     */
    private boolean verifyUserLogin( String username, String password, String executionValue ) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_FORM_URLENCODED );

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add( "username", username );
        formData.add( "password", password );
        formData.add( "_eventId", "submit" );
        formData.add( "execution", executionValue );

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>( formData, headers );

        try {

            ResponseEntity<String> response = restTemplate.exchange( URL_LOGIN, HttpMethod.POST, requestEntity, String.class );
            log.debug( "Login Ufes Verification. Response received: " + response.getBody() );
            return response.getStatusCode() == HttpStatus.OK;

        } catch ( HttpClientErrorException.Unauthorized unauthorized ) {

            return false;

        } catch ( Exception e ) {
            throw e;
        }
    }

}
