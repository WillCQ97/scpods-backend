package br.ufes.willcq.scpods.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.scpods.domain.model.Acao;
import br.ufes.willcq.scpods.domain.repository.AcaoRepository;
import br.ufes.willcq.scpods.domain.service.AcaoService;

@Service
@Transactional
public class AcaoServiceImpl implements AcaoService {

    @Autowired
    private AcaoRepository repository;

    @Override
    public Iterable<Acao> listar() {
        return repository.findAll();
    }

    @Override
    public Acao buscar( Long id ) {

        var acaoOpt = repository.findById( id );

        if( acaoOpt.isPresent() ) {
            return acaoOpt.get();
        } else {
            throw new RuntimeException( "Não foi encontrada a ação com o id informado!" );
        }
    }

}
