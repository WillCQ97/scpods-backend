package br.ufes.willcq.scpods.api.dto;

import br.ufes.willcq.scpods.api.dto.response.ObjetivoAcaoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MetaResponseDTO {

    private String id;
    private String descricao;

    private ObjetivoAcaoResponseDTO objetivo;

}
