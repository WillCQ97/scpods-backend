package br.ufes.willcq.scpods.service;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.model.MetaODS;

@Validated
public interface MetaODSService {

    public Iterable<MetaODS> listar();
}
