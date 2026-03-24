package com.github.willcq97.scpods.api.dto.response;

import com.github.willcq97.scpods.domain.model.entity.Coordenador;
import com.github.willcq97.scpods.domain.model.enums.TipoVinculoEnum;

public record CoordenadorSubmissaoResponseDTO(
        Long id,
        String nome,
        String email,
        TipoVinculoEnum tipoVinculo,
        String descricaoVinculo
) {

    public static CoordenadorSubmissaoResponseDTO fromEntity( Coordenador coordenador ) {
        return new CoordenadorSubmissaoResponseDTO(
                coordenador.getId(),
                coordenador.getNome(),
                coordenador.getEmail(),
                coordenador.getTipoVinculo(),
                coordenador.getDescricaoVinculo() );
    }

}
