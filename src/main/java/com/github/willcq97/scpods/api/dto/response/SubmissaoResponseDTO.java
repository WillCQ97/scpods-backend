package com.github.willcq97.scpods.api.dto.response;

import java.time.LocalDate;

import com.github.willcq97.scpods.domain.model.entity.Acao;

public record SubmissaoResponseDTO( Long id, String titulo, String descricao, String urlEvidencia, LocalDate dataCadastro, LocalDate dataInicio, LocalDate dataEncerramento, boolean aceito, CoordenadorSubmissaoResponseDTO coordenador, MetaAcaoResponseDTO meta, LocalAcaoResponseDTO local, LotacaoAcaoResponseDTO lotacao ) {

    public static SubmissaoResponseDTO fromEntity( Acao acao ) {
        return new SubmissaoResponseDTO(
                acao.getId(),
                acao.getTitulo(),
                acao.getDescricao(),
                acao.getUrlEvidencia(),
                acao.getDataCadastro(),
                acao.getDataInicio(),
                acao.getDataEncerramento(),
                acao.getAceito(),
                CoordenadorSubmissaoResponseDTO.fromEntity( acao.getCoordenador() ),
                MetaAcaoResponseDTO.fromEntity( acao.getMeta() ),
                LocalAcaoResponseDTO.fromEntity( acao.getLocal() ),
                LotacaoAcaoResponseDTO.fromEntity( acao.getLotacao() ) );
    }
}
