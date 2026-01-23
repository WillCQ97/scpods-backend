package com.github.willcq97.scpods.api.dto.response;

import com.github.willcq97.scpods.domain.model.entity.Meta;

public record MetaAcaoResponseDTO( Long id, String codigo, String descricao, ObjetivoAcaoResponseDTO objetivo ) {

    public static MetaAcaoResponseDTO fromEntity( Meta meta ) {
        return new MetaAcaoResponseDTO(
                meta.getId(),
                meta.getCodigo(),
                meta.getDescricao(),
                ObjetivoAcaoResponseDTO.fromEntity( meta.getObjetivo() ) );
    }
}
