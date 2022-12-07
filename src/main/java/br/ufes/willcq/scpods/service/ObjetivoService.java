package br.ufes.willcq.scpods.service;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.model.Objetivo;

@Validated
public interface ObjetivoService {

    public Iterable<Objetivo> listar();

    public Objetivo buscar( Long id );

}
