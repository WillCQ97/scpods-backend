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

    // TODO: utilizar o spring-boot-starter-validation para adicionar as validações
    // diretamente na model
    private void validarAcao( Acao acao ) {

        if( acao.getMeta() == null && acao.getMeta().getId() == null ) {
            throw new NegocioException( "Não foi informada uma meta para essa ação!" );
        }

        if( acao.getCoordenador() == null ) {
            throw new NegocioException( "Não foi informado um coordenador para essa ação!" );
        }

        if( acao.getDataInicio() == null ) {
            throw new NegocioException( "Não foi informada a data de início do projeto!" );
        }

        if( acao.getEstruturaOrganizacional() == null ) {
            throw new NegocioException( "Não foi informada qual a estrutura organizacional relacionada com a ação!" );
        }

    }

    @Override
    public Acao salvar( Acao acao ) {

        this.validarAcao( acao );

        var optAcao = acaoRepository.findByTitulo( acao.getTitulo() );
        if( optAcao.isPresent() ) {
            throw new NegocioException( "Já existe uma ação cadastrada com esse título!" );
        }

        var coordenador = coordenadorRepository.save( acao.getCoordenador() );
        acao.setCoordenador( coordenador );

        return acaoRepository.save( acao );
    }

}
