package br.ufes.willcq.scpods.service;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.model.Meta;

@Validated
public interface MetaService {

    public Iterable<Meta> listar();

    public Meta buscar( String id );

}
