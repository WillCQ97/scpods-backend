package br.ufes.willcq.scpods.service;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.model.ObjetivoODS;

@Validated
public interface ObjetivoODSService {

    public Iterable<ObjetivoODS> listar();

    public ObjetivoODS buscar( Long id );
}
