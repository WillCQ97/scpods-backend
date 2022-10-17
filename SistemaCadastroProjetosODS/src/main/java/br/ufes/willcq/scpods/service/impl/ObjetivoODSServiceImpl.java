package br.ufes.willcq.scpods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.scpods.model.ObjetivoODS;
import br.ufes.willcq.scpods.repository.ObjetivoODSRepository;
import br.ufes.willcq.scpods.service.ObjetivoODSService;

@Service
@Transactional
public class ObjetivoODSServiceImpl implements ObjetivoODSService {

    @Autowired
    private ObjetivoODSRepository repository;

    @Override
    public Iterable<ObjetivoODS> listar() {
        return repository.findAll();
    }

    @Override
    public ObjetivoODS buscar( Long id ) {
        var objetivoOptional = repository.findById( id );

        if( objetivoOptional.isPresent() ) {
            return objetivoOptional.get();
        } else {
            throw new RuntimeException( "Não foi encontrado o objetivo de ODS com o id informado!" );
        }
    }

}
