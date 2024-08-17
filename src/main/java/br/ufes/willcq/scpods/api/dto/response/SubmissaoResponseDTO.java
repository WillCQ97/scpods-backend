package br.ufes.willcq.scpods.api.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubmissaoResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;

    private LocalDate dataCadastro;
    private LocalDate dataInicio;
    private LocalDate dataEncerramento;

    private boolean aceito;

    private CoordenadorSubmissaoResponseDTO coordenador;
    private MetaAcaoResponseDTO meta;
    private LocalAcaoResponseDTO local;
    private LotacaoAcaoResponseDTO lotacao;
}
