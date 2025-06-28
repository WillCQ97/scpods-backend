package com.github.willcq97.scpods.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.willcq97.scpods.domain.model.Meta;
import com.github.willcq97.scpods.domain.model.Objetivo;
import com.github.willcq97.scpods.domain.repository.MetaRepository;
import com.github.willcq97.scpods.domain.repository.ObjetivoRepository;
import com.github.willcq97.scpods.domain.service.ObjetivoService;

@Service
public class ObjetivoServiceImpl implements ObjetivoService {

    @Autowired
    private ObjetivoRepository objetivoRepository;

    @Autowired
    private MetaRepository metaRepository;

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
