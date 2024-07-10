package br.ufes.willcq.scpods.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.domain.model.Meta;
import br.ufes.willcq.scpods.domain.model.Objetivo;

@Validated
public interface ObjetivoService {

    List<Objetivo> listar();

    Optional<Objetivo> findObjetivoByCodigo( String codigo );

    Optional<Meta> findMetaByCodigo( String codigo );

}
