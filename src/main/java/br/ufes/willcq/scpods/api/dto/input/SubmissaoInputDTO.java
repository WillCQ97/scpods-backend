package br.ufes.willcq.scpods.api.dto.input;

import java.time.LocalDate;

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

    private LocalDate dataInicio;
    private LocalDate dataEncerramento;

    private Long metaId;
    private Long localId;
    private Long lotacaoId;

    private CoordenadorInputDTO coordenador;

}
