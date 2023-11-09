package br.ufes.willcq.scpods.api.dto.response;

import java.time.LocalDate;

import br.ufes.willcq.scpods.api.dto.LotacaoDTO;
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

    private LocalDate dataCadastro;
    private LocalDate dataInicio;
    private LocalDate dataEncerramento;

    private CoordenadorResponseDTO coordenador;
    private MetaResponseDTO meta;
    private LotacaoDTO lotacao;

}
