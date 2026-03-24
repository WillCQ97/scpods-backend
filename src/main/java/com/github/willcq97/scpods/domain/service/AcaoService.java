package com.github.willcq97.scpods.domain.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.github.willcq97.scpods.api.dto.search.AcaoSearchDTO;
import com.github.willcq97.scpods.api.dto.search.AcaoSearchOptionsDTO;
import com.github.willcq97.scpods.domain.model.entity.Acao;

import jakarta.validation.constraints.NotNull;

@Validated
public interface AcaoService {

    public boolean existsById( @NotNull Long idAcao );

    public List<Acao> listar( boolean aceito );

    public List<Acao> listarPorCampus( boolean aceito, String campus );

    public List<Acao> listarPorUnidade( boolean aceito, String codigoUnidade );

    public List<AcaoSearchDTO> search( AcaoSearchOptionsDTO options, boolean aceito );

    public Acao findById( @NotNull Long id );

    public Acao findAcaoById( @NotNull Long id );

    public Acao findSubmissaoById( @NotNull Long id );

    public Acao atualizar( Acao acao );

    public void inserir( Acao acao );

    public void excluir( @NotNull Long idAcao );

    public void aceitar( @NotNull Long idAcao );
}
