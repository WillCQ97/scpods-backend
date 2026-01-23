package com.github.willcq97.scpods.api.dto.response;

import com.github.willcq97.scpods.domain.model.entity.Coordenador;
import com.github.willcq97.scpods.domain.model.enums.TipoVinculoEnum;

public record CoordenadorAcaoResponseDTO( Long id, String nome, TipoVinculoEnum tipoVinculo, String descricaoVinculo ) {

    public static CoordenadorAcaoResponseDTO fromEntity( Coordenador coordenador ) {
        return new CoordenadorAcaoResponseDTO(
                coordenador.getId(),
                coordenador.getNome(),
                coordenador.getTipoVinculo(),
                coordenador.getDescricaoVinculo() );
    }

}
