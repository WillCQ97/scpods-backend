package com.github.willcq97.scpods.domain.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.github.willcq97.scpods.api.dto.AcaoSearchDTO;
import com.github.willcq97.scpods.api.dto.AcaoSearchOptions;
import com.github.willcq97.scpods.domain.model.Acao;

import jakarta.validation.constraints.NotNull;

@Validated
public interface AcaoService {

    public boolean existsById( @NotNull Long idAcao );

    public List<Acao> listar( boolean aceito );

    public List<Acao> listarPorCampus( boolean aceito, String campus );

    public List<Acao> listarPorUnidade( boolean aceito, String codigoUnidade );

    public List<AcaoSearchDTO> search( AcaoSearchOptions options, boolean aceito );

    public Acao findById( @NotNull Long id );

    public Acao findAcaoById( @NotNull Long id );

    public Acao findSubmissaoById( @NotNull Long id );

    public Acao atualizar( Acao acao );

    public void inserirSubmissao( Acao acao );

    public void excluirSubmissao( @NotNull Long idAcao );

    public void aceitarSubmissao( @NotNull Long idAcao );
}
