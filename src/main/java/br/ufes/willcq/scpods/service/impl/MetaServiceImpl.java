package br.ufes.willcq.scpods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.scpods.model.Meta;
import br.ufes.willcq.scpods.repository.MetaRepository;
import br.ufes.willcq.scpods.service.MetaService;

@Service
@Transactional
public class MetaServiceImpl implements MetaService {

    @Autowired
    private MetaRepository repository;

    @Override
    public Iterable<Meta> listar() {
        return repository.findAll();
    }

    @Override
    public Meta buscar( String id ) {
        var metaOptional = repository.findById( id );

        if( metaOptional.isPresent() ) {
            return metaOptional.get();
        } else {
            throw new RuntimeException( "Não foi encontrada a meta com o id informado!" );
        }
    }
}
