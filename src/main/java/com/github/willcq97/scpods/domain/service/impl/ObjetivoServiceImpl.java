package com.github.willcq97.scpods.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.willcq97.scpods.domain.model.entity.Meta;
import com.github.willcq97.scpods.domain.model.entity.Objetivo;
import com.github.willcq97.scpods.domain.model.repository.MetaRepository;
import com.github.willcq97.scpods.domain.model.repository.ObjetivoRepository;
import com.github.willcq97.scpods.domain.service.ObjetivoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ObjetivoServiceImpl implements ObjetivoService {

    private final ObjetivoRepository objetivoRepository;

    private final MetaRepository metaRepository;

    @Override
    public List<Objetivo> listar() {
        return objetivoRepository.findAllOrdered();
    }

    @Override
    public Optional<Objetivo> findObjetivoByCodigo( String codigo ) {
        return objetivoRepository.findByCodigo( codigo );
    }

    @Override
    public Optional<Meta> findMetaByCodigo( String codigo ) {
        return metaRepository.findByCodigo( codigo );
    }

}
