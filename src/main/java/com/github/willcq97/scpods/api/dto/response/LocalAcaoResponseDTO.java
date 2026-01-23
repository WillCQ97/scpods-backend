package com.github.willcq97.scpods.api.dto.response;

import org.locationtech.jts.geom.Point;

import com.github.willcq97.scpods.domain.model.entity.Local;

public record LocalAcaoResponseDTO( Long id, Long idd, String nomePrincipal, String nomeSecundario, String nomeTerciario, Point localizacao, UnidadeAcaoReponseDTO unidade ) {

    public static LocalAcaoResponseDTO fromEntity( Local local ) {
        return new LocalAcaoResponseDTO(
                local.getId(),
                local.getIdd(),
                local.getNomePrincipal(),
                local.getNomeSecundario(),
                local.getNomeTerciario(),
                local.getLocalizacao(),
                UnidadeAcaoReponseDTO.fromEntity( local.getUnidade() ) );
    }
}
