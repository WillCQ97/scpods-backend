package br.ufes.willcq.scpods.api.dto.input;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AcaoInputDTO {

    private String titulo;
    private String descricao;

    @JsonFormat( pattern = "dd/MM/yyyy" )
    private LocalDate dataCadastro;
    @JsonFormat( pattern = "dd/MM/yyyy" )
    private LocalDate dataInicio;
    @JsonFormat( pattern = "dd/MM/yyyy" )
    private LocalDate dataEncerramento;

    private String metaId;
    private Long localId;

    private CoordenadorInputDTO coordenador;
    private Long lotacaoId;

}
