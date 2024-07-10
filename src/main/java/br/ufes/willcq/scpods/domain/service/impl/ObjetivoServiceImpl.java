package br.ufes.willcq.scpods.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.willcq.scpods.domain.model.Meta;
import br.ufes.willcq.scpods.domain.model.Objetivo;
import br.ufes.willcq.scpods.domain.repository.MetaRepository;
import br.ufes.willcq.scpods.domain.repository.ObjetivoRepository;
import br.ufes.willcq.scpods.domain.service.ObjetivoService;

@Service
public class ObjetivoServiceImpl implements ObjetivoService {

    @Autowired
    private ObjetivoRepository objetivoRepository;

    @Autowired
    private MetaRepository metaRepository;

    @Override
    public List<Objetivo> listar() {
        return objetivoRepository.findAll();
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
