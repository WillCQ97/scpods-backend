package br.ufes.willcq.scpods.domain.service;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.domain.model.Objetivo;

@Validated
public interface ObjetivoService {

    public Iterable<Objetivo> listar();

    public Objetivo buscar( Long id );

}
