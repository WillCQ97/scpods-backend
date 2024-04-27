package br.ufes.willcq.scpods.api.dto.response;

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
public class AcaoResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;

    @JsonFormat( pattern = "dd/MM/yyyy" )
    private LocalDate dataCadastro;
    @JsonFormat( pattern = "dd/MM/yyyy" )
    private LocalDate dataInicio;
    @JsonFormat( pattern = "dd/MM/yyyy" )
    private LocalDate dataEncerramento;

    private boolean aceito;

    private CoordenadorAcaoResponseDTO coordenador;
    private MetaAcaoResponseDTO meta;
    private LocalAcaoResponseDTO local;
    private LotacaoAcaoResponseDTO lotacao;

}
