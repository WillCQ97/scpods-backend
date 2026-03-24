package com.github.willcq97.scpods.api.dto.response;

import org.locationtech.jts.geom.Point;

import com.github.willcq97.scpods.domain.model.entity.Local;

public record LocalInfoDTO(
        Long id,
        Long idd,
        String nomePrincipal,
        String nomeSecundario,
        String nomeTerciario,
        Point localizacao,
        Long projetosTotais,
        Long projetosAtivos,
        Long objetivosAtendidos,
        Long idObjetivoComMaisProjetos
) {

    public static LocalInfoDTO fromEntity( Local local ) {
        return new LocalInfoDTO(
                local.getId(),
                local.getIdd(),
                local.getNomePrincipal(),
                local.getNomeSecundario(),
                local.getNomeTerciario(),
                local.getLocalizacao(),
                local.getProjetosTotais(),
                local.getProjetosAtivos(),
                local.getObjetivosAtendidos(),
                local.getIdObjetivoComMaisProjetos() );
    }
}
