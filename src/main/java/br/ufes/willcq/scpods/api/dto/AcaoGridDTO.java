package br.ufes.willcq.scpods.api.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AcaoGridDTO {

    private Long id;
    private String titulo;
    @JsonFormat( pattern = "dd/MM/yyyy" )
    private LocalDate dataCadastro;
    private String codigoObjetivo;
    private String codigoMeta;
    private String nomeLocal;
    private String nomeCoordenador;
    private String siglaLotacao;

}
