package br.ufes.willcq.scpods.api.exception_handler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.ufes.willcq.scpods.domain.exception.BusinessException;
import br.ufes.willcq.scpods.domain.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid( @NonNull MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request ) {

        var msg = "Um ou mais campos estão inválidos.";
        var problema = new Error( status.value(), OffsetDateTime.now(), msg );
        var campos = new ArrayList<Error.Field>();

        for( ObjectError error : ex.getBindingResult().getAllErrors() ) {

            String nome = ( ( FieldError ) error ).getField();
            String mensagem = messageSource.getMessage( error, LocaleContextHolder.getLocale() );
            campos.add( new Error.Field( nome, mensagem ) );

        }
        problema.setCampos( campos );

        return handleExceptionInternal( ex, problema, headers, status, request );
    }

    @ExceptionHandler( BusinessException.class )
    public ResponseEntity<Object> handleBusinessException( BusinessException ex, @NonNull WebRequest request ) {

        var status = HttpStatus.BAD_REQUEST;
        var problema = new Error( status.value(), OffsetDateTime.now(), ex.getMessage() );

        return handleExceptionInternal( ex, problema, new HttpHeaders(), status, request );
    }

    @ExceptionHandler( EntityNotFoundException.class )
    public ResponseEntity<Object> handleBusinessException( EntityNotFoundException ex, @NonNull WebRequest request ) {

        var status = HttpStatus.NOT_FOUND;
        var problema = new Error( status.value(), OffsetDateTime.now(), ex.getMessage() );

        return handleExceptionInternal( ex, problema, new HttpHeaders(), status, request );
    }

    @ExceptionHandler( Exception.class )
    public ResponseEntity<Object> handleAllExceptions( Exception ex, @NonNull WebRequest request ) {

        var msg = "Erro inesperado! Entre em contato com o administrador do sistema com o código " + UUID.randomUUID();
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var problema = new Error( status.value(), OffsetDateTime.now(), msg );

        logger.error( msg, ex );
        return handleExceptionInternal( ex, problema, new HttpHeaders(), status, request );
    }

}
