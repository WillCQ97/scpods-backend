package com.github.willcq97.scpods.domain.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.github.willcq97.scpods.api.dto.select.SelectModel;
import com.github.willcq97.scpods.api.dto.select.SelectModelString;
import com.github.willcq97.scpods.domain.model.entity.Unidade;

@Validated
public interface UnidadeService {

    public List<SelectModelString> listarOpcoesCampus();

    public List<SelectModel<String>> listarOpcoesUnidades();

    public List<Unidade> listarUnidades( String campus );

    public List<Unidade> obterContabilizacaoPorCampus( String nomeCampus );

    public Unidade obterContabilizacaoParaUnidade( String codigo );

}
