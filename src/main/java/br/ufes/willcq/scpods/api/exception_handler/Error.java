package br.ufes.willcq.scpods.api.exception_handler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude( Include.NON_NULL )
public class Error {

    private Integer status;
    private OffsetDateTime dataHora;
    private String mensagem;
    private List<Field> campos;

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Field {

        private String nome;
        private String mensagem;

    }

    public Error( Integer status, OffsetDateTime dataHora, String mensagem ) {
        super();
        this.status = status;
        this.dataHora = dataHora;
        this.mensagem = mensagem;
    }

}
