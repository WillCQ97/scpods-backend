package br.ufes.willcq.scpods.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ObjetivoAcaoResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;

}
