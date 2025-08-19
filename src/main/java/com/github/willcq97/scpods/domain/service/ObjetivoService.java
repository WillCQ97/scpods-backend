package com.github.willcq97.scpods.domain.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.github.willcq97.scpods.domain.model.entity.Meta;
import com.github.willcq97.scpods.domain.model.entity.Objetivo;

@Validated
public interface ObjetivoService {

    List<Objetivo> listar();

    Objetivo findObjetivoByCodigo( String codigo );

    Meta findMetaByCodigo( String codigo );

}
