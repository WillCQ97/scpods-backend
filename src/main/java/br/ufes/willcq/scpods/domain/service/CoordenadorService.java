package br.ufes.willcq.scpods.domain.service;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.domain.model.Coordenador;

@Validated
public interface CoordenadorService {

    public Coordenador buscar( Long id );

}
