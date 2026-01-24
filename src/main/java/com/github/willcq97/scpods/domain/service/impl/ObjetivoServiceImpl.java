package com.github.willcq97.scpods.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.willcq97.scpods.domain.exception.EntityNotFoundException;
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
    public List<Objetivo> findAll() {
        return objetivoRepository.findAllOrdered();
    }

    @Override
    public Objetivo findObjetivoByCodigo( String codigo ) {
        return objetivoRepository.findByCodigo( codigo )
                .orElseThrow( () -> new EntityNotFoundException( "Não foi encontrada um objetivo com o id informado" ) );
    }

    @Override
    public Meta findMetaByCodigo( String codigo ) {
        return metaRepository.findByCodigo( codigo )
                .orElseThrow( () -> new EntityNotFoundException( "Não foi encontrada uma meta com o id informado" ) );
    }

}
