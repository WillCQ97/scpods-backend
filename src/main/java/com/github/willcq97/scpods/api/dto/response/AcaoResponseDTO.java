package com.github.willcq97.scpods.api.dto.response;

import java.time.LocalDate;

import com.github.willcq97.scpods.domain.model.entity.Acao;

public record AcaoResponseDTO(
        Long id,
        String titulo,
        String descricao,
        String urlEvidencia,
        LocalDate dataCadastro,
        LocalDate dataInicio,
        LocalDate dataEncerramento,
        boolean aceito,
        CoordenadorAcaoResponseDTO coordenador,
        MetaAcaoResponseDTO meta,
        LocalAcaoResponseDTO local,
        LotacaoAcaoResponseDTO lotacao
) {

    public static AcaoResponseDTO fromEntity( Acao acao ) {
        return new AcaoResponseDTO(
                acao.getId(),
                acao.getTitulo(),
                acao.getDescricao(),
                acao.getUrlEvidencia(),
                acao.getDataCadastro(),
                acao.getDataInicio(),
                acao.getDataEncerramento(),
                acao.getAceito(),
                CoordenadorAcaoResponseDTO.fromEntity( acao.getCoordenador() ),
                MetaAcaoResponseDTO.fromEntity( acao.getMeta() ),
                LocalAcaoResponseDTO.fromEntity( acao.getLocal() ),
                LotacaoAcaoResponseDTO.fromEntity( acao.getLotacao() ) );
    }

}
