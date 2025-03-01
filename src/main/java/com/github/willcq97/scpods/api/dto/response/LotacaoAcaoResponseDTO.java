package com.github.willcq97.scpods.api.dto.response;

import com.github.willcq97.scpods.domain.model.enums.CampusEnum;

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
