package br.ufes.willcq.scpods.domain.service;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.domain.model.Acao;

@Validated
public interface CadastroAcaoService {

    public Acao atualizar( Acao acao );

    public Acao salvar( Acao acao );

}
