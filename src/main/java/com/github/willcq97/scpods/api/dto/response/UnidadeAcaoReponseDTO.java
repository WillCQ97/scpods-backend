package com.github.willcq97.scpods.api.dto.response;

import com.github.willcq97.scpods.domain.model.entity.Unidade;
import com.github.willcq97.scpods.domain.model.enums.CampusEnum;

public record UnidadeAcaoReponseDTO( Long id, String nome, CampusEnum campus, String codigo ) {

    public static UnidadeAcaoReponseDTO fromEntity( Unidade unidade ) {
        return new UnidadeAcaoReponseDTO(
                unidade.getId(),
                unidade.getNome(),
                unidade.getCampus(),
                unidade.getCodigo() );
    }

}
