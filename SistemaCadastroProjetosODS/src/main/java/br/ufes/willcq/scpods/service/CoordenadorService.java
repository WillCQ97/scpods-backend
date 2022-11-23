package br.ufes.willcq.scpods.service;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.model.Coordenador;

@Validated
public interface CoordenadorService {

    public Coordenador buscar( Long id );

}
