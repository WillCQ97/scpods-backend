package br.ufes.willcq.scpods.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.willcq.scpods.model.Coordenador;
import br.ufes.willcq.scpods.repository.CoordenadorRepository;
import br.ufes.willcq.scpods.service.CoordenadorService;

/**
 * @deprecated (when: 07-12-2022, why: Não será utilizado diretamente,
 *             provavelmente)
 */
@Deprecated
@Service
@Transactional
public class CoordenadorServiceImpl implements CoordenadorService {

    @Autowired
    private CoordenadorRepository repository;

    @Override
    public Coordenador buscar( Long id ) {
        var coordenadorOpt = repository.findById( id );

        if( coordenadorOpt.isPresent() ) {
            return coordenadorOpt.get();
        } else {
            throw new RuntimeException( "Não foi encontrado o coordenador com o id informado!" );
        }
    }

}
