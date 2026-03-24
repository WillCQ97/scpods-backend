package com.github.willcq97.scpods.api.dto.search;

import java.time.LocalDate;

import com.github.willcq97.scpods.domain.model.entity.Acao;

public record AcaoSearchDTO(
        Long id,
        String titulo,
        LocalDate dataCadastro,
        String codigoObjetivo,
        String codigoMeta,
        String nomeLocal,
        String nomeCoordenador,
        String siglaLotacao
) {

    public static AcaoSearchDTO fromEntity( Acao acao ) {
        return new AcaoSearchDTO(
                acao.getId(),
                acao.getTitulo(),
                acao.getDataCadastro(),
                acao.getCodigoObjetivo(),
                acao.getCodigoMeta(),
                acao.getNomeLocal(),
                acao.getNomeCoordenador(),
                acao.getSiglaLotacao() );
    }

}
