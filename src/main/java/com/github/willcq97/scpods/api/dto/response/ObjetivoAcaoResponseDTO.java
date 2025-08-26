package com.github.willcq97.scpods.api.dto.response;

import com.github.willcq97.scpods.domain.model.entity.Objetivo;

public record ObjetivoAcaoResponseDTO( Long id, String codigo, String titulo, String descricao ) {

    public static ObjetivoAcaoResponseDTO fromEntity( Objetivo objetivo ) {
        return new ObjetivoAcaoResponseDTO(
                objetivo.getId(),
                objetivo.getCodigo(),
                objetivo.getTitulo(),
                objetivo.getDescricao() );
    }

}
