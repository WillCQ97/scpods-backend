package br.ufes.willcq.scpods.domain.service;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.domain.model.Meta;

@Validated
public interface MetaService {

    public Iterable<Meta> listar();

    public Meta buscar( String id );

}
