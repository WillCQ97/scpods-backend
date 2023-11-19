package br.ufes.willcq.scpods.api.dto.response;

import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LotacaoAcaoResponseDTO {

    private Long id;
    private String descricao;
    private String sigla;
    private CampusEnum campus;

}
