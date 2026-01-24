package com.github.willcq97.scpods.api.dto.response;

import java.util.List;

import com.github.willcq97.scpods.domain.model.entity.Objetivo;

public record ObjetivoResponseDTO( Long id, String codigo, String titulo, String descricao, List<MetaObjetivoResponseDTO> metas ) {

    public static ObjetivoResponseDTO fromEntity( Objetivo objetivo ) {
        List<MetaObjetivoResponseDTO> metas = objetivo.getMetas().stream()
                .map( MetaObjetivoResponseDTO::fromEntity )
                .toList();

        return new ObjetivoResponseDTO(
                objetivo.getId(),
                objetivo.getCodigo(),
                objetivo.getTitulo(),
                objetivo.getDescricao(),
                metas );
    }
}
