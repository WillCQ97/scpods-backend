package com.github.willcq97.scpods.api.dto.response;

import java.util.List;

import com.github.willcq97.scpods.domain.model.entity.Unidade;
import com.github.willcq97.scpods.domain.model.enums.CampusEnum;


public record UnidadeInfoDTO( Long id, String nome, String codigo, CampusEnum campus, List<LocalInfoDTO> locais ) {

    public static UnidadeInfoDTO fromEntity( Unidade unidade ) {
        List<LocalInfoDTO> locais = unidade.getLocais().stream()
                .map( LocalInfoDTO::fromEntity )
                .toList();

        return new UnidadeInfoDTO(
                unidade.getId(),
                unidade.getNome(),
                unidade.getCodigo(),
                unidade.getCampus(),
                locais );
    }

}
