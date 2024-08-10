package br.ufes.willcq.scpods.domain.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.api.dto.select.SelectModel;
import br.ufes.willcq.scpods.api.dto.select.SelectModelString;
import br.ufes.willcq.scpods.domain.model.Unidade;

@Validated
public interface UnidadeService {

    public List<SelectModelString> listarOpcoesCampus();

    public List<SelectModel<String>> listarOpcoesUnidades();

    public List<Unidade> listarUnidades( String campus );

    public List<Unidade> obterContabilizacaoPorCampus( String nomeCampus );

    public Unidade obterContabilizacaoParaUnidade( String codigo );

}
