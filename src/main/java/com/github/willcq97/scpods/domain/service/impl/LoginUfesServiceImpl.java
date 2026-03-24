package com.github.willcq97.scpods.domain.service.impl;

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

import com.github.willcq97.scpods.api.dto.input.LoginUsuarioDTO;
import com.github.willcq97.scpods.domain.exception.BusinessException;
import com.github.willcq97.scpods.domain.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginUfesServiceImpl implements LoginService {

    private static final String URL_LOGIN = "https://acesso.ufes.br/login";
    private static final Pattern EXECUTION_INPUT_PATTERN = Pattern.compile( "<input type=\"hidden\" name=\"execution\" value=\"[a-zA-Z0-9-_]+\"/>" );
    private final RestTemplate restTemplate;

    public LoginUfesServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    public void validar( LoginUsuarioDTO usuario ) {
        var executionValue = extractExecutionFormValue();

        if( executionValue.isEmpty() ) {
            throw new BusinessException( "Não foi possível validar seu usuário!" );
        }

        if( !this.verificarLoginUsuario( usuario.getUsername(), usuario.getPassword(), executionValue.get() ) ) {
            throw new BusinessException( "As informações de login são inválidas!" );
        }

    }

    /**
     * Recupera o conteúdo HTML da página de login e processa para extrair o campo input oculto contendo o executionFormValue. Esse valor é necessário para autenticar um usuário ao fazer uma requisição subsequente. Este método é altamente preso a estrutura HTML da página de login, para tornar o
     * login possível nesta aplicação.
     * 
     * @return o executionFormValue extraído necessário para validação do login
     */
    private Optional<String> extractExecutionFormValue() {

        ResponseEntity<String> response = restTemplate.getForEntity( URL_LOGIN, String.class );
        HttpStatusCode statusCode = response.getStatusCode();
        log.debug( "extractExecutionFormValue. Status Code: {}", statusCode );

        String body = response.getBody();
        Matcher matcher = EXECUTION_INPUT_PATTERN.matcher( body );

        if( matcher.find() ) {
            String input = matcher.group( 0 );
            log.debug( "extractExecutionFormValue. input: {}", input );

            String[] inputItems = input.split( " " );

            if( inputItems.length > 0 ) {
                String inputValue = inputItems[inputItems.length - 1];

                if( inputValue.length() >= 10 ) {
                    String value = inputValue.substring( 7, inputValue.length() - 3 );
                    log.debug( "extractExecutionFormValue. value: {}", value );
                    return Optional.of( value );
                }
            }
        }

        return Optional.empty();
    }

    /**
     * Verifica as credenciais de login do usuário enviando uma requisição HTTP POST para a URL de login com o username, senha e valor de execução fornecidos. O método constrói a requisição com os headers e dados de formulário apropriados, e então envia a requisição para validar o login.
     *
     * O método espera que a resposta seja HTTP 200 OK para um login bem-sucedido. Se a resposta for 401 Unauthorized, indica credenciais incorretas. Outras exceções são propagadas.
     *
     * @param username       o nome de usuário a ser autenticado
     * @param password       a senha do usuário a ser autenticado
     * @param executionValue valor único extraído da página de login necessário para validação
     * @return {@code true} se o login for bem-sucedido (HTTP 200 OK), {@code false} se falhar por credenciais incorretas (HTTP 401 Unauthorized)
     * @throws Exception se ocorrer qualquer outro erro durante a requisição HTTP
     */
    private boolean verificarLoginUsuario( String username, String password, String executionValue ) {

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
            log.debug( "VerificarLoginUsuario. Response: " + response.getBody() );
            return HttpStatus.OK.equals( response.getStatusCode() );

        } catch ( HttpClientErrorException.Unauthorized _ ) {
            return false;
        }
    }

}
