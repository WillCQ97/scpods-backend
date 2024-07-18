package br.ufes.willcq.scpods.domain.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.domain.model.Unidade;
import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;

@Validated
public interface UnidadeService {

    public List<CampusEnum> listarOpcoesCampus();

    public List<Unidade> listarUnidades( String campus );

    public List<Unidade> obterContabilizacaoPorCampus( String nomeCampus );

    public Unidade obterContabilizacaoParaUnidade( String codigo );

}
