package com.github.willcq97.scpods.api.dto.response;

import java.util.List;

import com.github.willcq97.scpods.domain.model.entity.Unidade;
import com.github.willcq97.scpods.domain.model.enums.CampusEnum;

public record UnidadeResponseDTO( Long id, String nome, String codigo, CampusEnum campus, List<LocalResponseDTO> locais ) {

    public static UnidadeResponseDTO fromEntity( Unidade unidade ) {
        List<LocalResponseDTO> locais = unidade.getLocais().stream()
                .map( LocalResponseDTO::fromEntity )
                .toList();

        return new UnidadeResponseDTO(
                unidade.getId(),
                unidade.getNome(),
                unidade.getCodigo(),
                unidade.getCampus(),
                locais );
    }
}
