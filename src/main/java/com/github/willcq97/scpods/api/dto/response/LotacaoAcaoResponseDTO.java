package com.github.willcq97.scpods.api.dto.response;

import com.github.willcq97.scpods.domain.model.entity.Lotacao;
import com.github.willcq97.scpods.domain.model.enums.CampusEnum;

public record LotacaoAcaoResponseDTO(
        Long id,
        String descricao,
        String sigla,
        CampusEnum campus
) {

    public static LotacaoAcaoResponseDTO fromEntity( Lotacao lotacao ) {
        return new LotacaoAcaoResponseDTO(
                lotacao.getId(),
                lotacao.getDescricao(),
                lotacao.getSigla(),
                lotacao.getCampus() );
    }
}
