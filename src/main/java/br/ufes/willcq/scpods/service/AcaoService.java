package br.ufes.willcq.scpods.service;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.model.Acao;

@Validated
public interface AcaoService {

    public Iterable<Acao> listar();

    public Acao buscar( Long id );

}
