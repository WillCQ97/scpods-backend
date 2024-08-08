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
public class SubmissaoInputDTO {

    private String titulo;
    private String descricao;

    @JsonFormat( pattern = "dd/MM/yyyy" )
    private LocalDate dataInicio;
    @JsonFormat( pattern = "dd/MM/yyyy" )
    private LocalDate dataEncerramento;

    private Long metaId;
    private Long localId;
    private Long lotacaoId;

    private CoordenadorInputDTO coordenador;

}
