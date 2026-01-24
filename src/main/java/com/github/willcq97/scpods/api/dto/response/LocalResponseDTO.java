package com.github.willcq97.scpods.api.dto.response;

import com.github.willcq97.scpods.domain.model.entity.Local;

public record LocalResponseDTO( Long id, Long idd, String nomePrincipal, String nomeSecundario, String nomeTerciario ) {

    public static LocalResponseDTO fromEntity( Local local ) {
        return new LocalResponseDTO(
                local.getId(),
                local.getIdd(),
                local.getNomePrincipal(),
                local.getNomeSecundario(),
                local.getNomeTerciario() );
    }
}
