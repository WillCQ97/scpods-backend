package br.ufes.willcq.scpods.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.scpods.domain.model.Objetivo;
import br.ufes.willcq.scpods.domain.repository.ObjetivoRepository;
import br.ufes.willcq.scpods.domain.service.ObjetivoService;

@Service
@Transactional
public class ObjetivoServiceImpl implements ObjetivoService {

    @Autowired
    private ObjetivoRepository repository;

    @Override
    public Iterable<Objetivo> listar() {
        return repository.findAll();
    }

    @Override
    public Objetivo buscar( Long id ) {
        var objetivoOptional = repository.findById( id );

        if( objetivoOptional.isPresent() ) {
            return objetivoOptional.get();
        } else {
            throw new RuntimeException( "Não foi encontrado o objetivo de ODS com o id informado!" );
        }
    }

}
