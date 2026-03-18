package com.github.willcq97.scpods.api.mapper;

import org.springframework.stereotype.Component;

import com.github.willcq97.scpods.api.dto.input.SubmissaoInputDTO;
import com.github.willcq97.scpods.domain.model.entity.Acao;
import com.github.willcq97.scpods.domain.model.entity.Coordenador;
import com.github.willcq97.scpods.domain.model.entity.Local;
import com.github.willcq97.scpods.domain.model.entity.Lotacao;
import com.github.willcq97.scpods.domain.model.entity.Meta;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AcaoMapper {

    public Acao toEntity( SubmissaoInputDTO dto ) {
        var acao = new Acao();

        acao.setTitulo( dto.getTitulo() );
        acao.setDescricao( dto.getDescricao() );
        acao.setUrlEvidencia( dto.getUrlEvidencia() );
        acao.setDataInicio( dto.getDataInicio() );
        acao.setDataEncerramento( dto.getDataEncerramento() );

        var meta = new Meta();
        meta.setId( dto.getMetaId() );
        acao.setMeta( meta );

        var local = new Local();
        local.setId( dto.getLocalId() );
        acao.setLocal( local );

        var lotacao = new Lotacao();
        lotacao.setId( dto.getLotacaoId() );
        acao.setLotacao( lotacao );

        var coordDto = dto.getCoordenador();
        var coordenador = new Coordenador();
        coordenador.setNome( coordDto.getNome() );
        coordenador.setEmail( coordDto.getEmail() );
        coordenador.setTipoVinculo( coordDto.getTipoVinculo() );
        coordenador.setDescricaoVinculo( coordDto.getDescricaoVinculo() );
        acao.setCoordenador( coordenador );

        return acao;
    }

}
