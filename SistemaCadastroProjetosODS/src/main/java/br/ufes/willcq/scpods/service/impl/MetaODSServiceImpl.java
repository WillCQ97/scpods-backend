package br.ufes.willcq.scpods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.scpods.model.MetaODS;
import br.ufes.willcq.scpods.repository.MetaODSRepository;
import br.ufes.willcq.scpods.service.MetaODSService;

@Service
@Transactional
public class MetaODSServiceImpl implements MetaODSService {

    @Autowired
    private MetaODSRepository repository;

    @Override
    public Iterable<MetaODS> listar() {
        return repository.findAll();
    }

    @Override
    public MetaODS buscar( String id ) {
        var metaOptional = repository.findById( id );

        if( metaOptional.isPresent() ) {
            return metaOptional.get();
        } else {
            throw new RuntimeException( "Não foi encontrada a meta com o id informado!" );
        }
    }
}
