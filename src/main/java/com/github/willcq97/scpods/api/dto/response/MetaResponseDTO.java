package com.github.willcq97.scpods.api.dto.response;

import com.github.willcq97.scpods.domain.model.entity.Meta;

public record MetaResponseDTO( Long id, String codigo, String descricao, ObjetivoAcaoResponseDTO objetivo ) {

    public static MetaResponseDTO fromEntity( Meta meta ) {
        return new MetaResponseDTO(
                meta.getId(),
                meta.getCodigo(),
                meta.getDescricao(),
                ObjetivoAcaoResponseDTO.fromEntity( meta.getObjetivo() ) );
    }

}
