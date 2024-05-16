package br.ufes.willcq.scpods.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MetaAcaoResponseDTO {

    private Long id;
    private String codigo;
    private String descricao;

    private ObjetivoAcaoResponseDTO objetivo;

}
