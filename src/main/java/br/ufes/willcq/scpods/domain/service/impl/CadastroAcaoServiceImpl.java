package br.ufes.willcq.scpods.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.scpods.domain.exception.NegocioException;
import br.ufes.willcq.scpods.domain.model.Acao;
import br.ufes.willcq.scpods.domain.repository.AcaoRepository;
import br.ufes.willcq.scpods.domain.repository.CoordenadorRepository;
import br.ufes.willcq.scpods.domain.service.CadastroAcaoService;

@Service
@Transactional
public class CadastroAcaoServiceImpl implements CadastroAcaoService {

    @Autowired
    private AcaoRepository acaoRepository;

    @Autowired
    private CoordenadorRepository coordenadorRepository;

    @Override
    public Acao salvar( Acao acao ) {

        var optAcao = acaoRepository.findByTitulo( acao.getTitulo() );
        if( optAcao.isPresent() ) {
            throw new NegocioException( "Já existe uma ação cadastrada com esse título!" );
        }

        var coordenador = coordenadorRepository.save( acao.getCoordenador() );
        acao.setCoordenador( coordenador );

        return acaoRepository.save( acao );
    }

}
