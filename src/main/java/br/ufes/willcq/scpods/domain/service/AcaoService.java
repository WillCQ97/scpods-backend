package br.ufes.willcq.scpods.domain.service;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.domain.model.Acao;

@Validated
public interface AcaoService {

    public Iterable<Acao> listar();

    public Acao buscar( Long id );

}
