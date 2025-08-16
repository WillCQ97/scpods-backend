package com.github.willcq97.scpods.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.annotation.Validated;

import com.github.willcq97.scpods.domain.model.entity.Meta;
import com.github.willcq97.scpods.domain.model.entity.Objetivo;

@Validated
public interface ObjetivoService {

    List<Objetivo> listar();

    Optional<Objetivo> findObjetivoByCodigo( String codigo );

    Optional<Meta> findMetaByCodigo( String codigo );

}
