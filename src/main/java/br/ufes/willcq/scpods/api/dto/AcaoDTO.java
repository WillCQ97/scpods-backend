package br.ufes.willcq.scpods.api.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AcaoDTO {

    private Long id;
    private String titulo;
    private String descricao;

    private LocalDate dataCadastro;
    private LocalDate dataInicio;
    private LocalDate dataEncerramento;

    private Boolean aceito;

    private MetaDTO meta;
    private CoordenadorDTO coordenador;

    private LotacaoDTO lotacao;

}
