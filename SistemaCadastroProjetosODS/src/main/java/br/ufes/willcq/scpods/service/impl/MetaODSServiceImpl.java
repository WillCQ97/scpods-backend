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
    private MetaODSRepository metaODSRepository;

    @Override
    public Iterable<MetaODS> listar() {
        return metaODSRepository.findAll();
    }

    @Override
    public MetaODS buscar( String id ) {
        var metaOptional = metaODSRepository.findById( id );

        if( metaOptional.isPresent() ) {
            return metaOptional.get();
        } else {
            // TODO: implementar uma exception própria
            throw new RuntimeException( "Não foi encontrada a meta com o id informado!" );
        }
    }
}
