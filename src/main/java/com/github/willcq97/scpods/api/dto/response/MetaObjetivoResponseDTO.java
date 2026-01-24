package com.github.willcq97.scpods.api.dto.response;

import com.github.willcq97.scpods.domain.model.entity.Meta;

public record MetaObjetivoResponseDTO( Long id, String codigo, String descricao ) {

    public static MetaObjetivoResponseDTO fromEntity( Meta meta ) {
        return new MetaObjetivoResponseDTO( meta.getId(), meta.getCodigo(), meta.getDescricao() );
    }

}
