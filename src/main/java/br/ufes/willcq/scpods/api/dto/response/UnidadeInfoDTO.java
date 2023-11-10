package br.ufes.willcq.scpods.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UnidadeInfoDTO {

    private Long id;
    private String nome;

    private List<LotacaoInfoDTO> lotacoes;
}
