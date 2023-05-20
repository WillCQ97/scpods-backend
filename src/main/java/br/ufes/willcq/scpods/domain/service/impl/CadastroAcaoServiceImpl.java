package br.ufes.willcq.scpods.domain.service.impl;

import java.time.LocalDate;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.scpods.domain.exception.NegocioException;
import br.ufes.willcq.scpods.domain.model.Acao;
import br.ufes.willcq.scpods.domain.repository.AcaoRepository;
import br.ufes.willcq.scpods.domain.repository.CoordenadorRepository;
import br.ufes.willcq.scpods.domain.repository.EstruturaOrganizacionalRepository;
import br.ufes.willcq.scpods.domain.repository.MetaRepository;
import br.ufes.willcq.scpods.domain.service.CadastroAcaoService;

@Service
@Transactional
public class CadastroAcaoServiceImpl implements CadastroAcaoService {

    @Autowired
    private AcaoRepository acaoRepository;

    @Autowired
    private CoordenadorRepository coordenadorRepository;

    @Autowired
    private EstruturaOrganizacionalRepository estruturaOrganizacionalRepository;

    @Autowired
    private MetaRepository metaRepository;

    @Override
    public Acao salvar( Acao acao ) {

        var optAcao = acaoRepository.findByTitulo( acao.getTitulo() );
        if( optAcao.isPresent() ) {
            throw new NegocioException( "Já existe uma ação cadastrada com esse título!" );
        }

        this.validarAcao( acao );

        var coordenador = coordenadorRepository.save( acao.getCoordenador() );

        acao.setAceito( Boolean.FALSE );
        acao.setDataCadastro( LocalDate.now() );
        acao.setCoordenador( coordenador );

        return acaoRepository.save( acao );
    }

    @Override
    public Acao atualizar( Acao acao ) {

        var optAcao = acaoRepository.findByTitulo( acao.getTitulo() );
        if( optAcao.isPresent() && !acao.getId().equals( optAcao.get().getId() ) ) {
            throw new NegocioException( "Já existe uma ação cadastrada com esse título!" );
        }

        this.validarAcao( acao );
        coordenadorRepository.save( acao.getCoordenador() );
        return acaoRepository.save( acao );

    }

    @Override
    public void excluir( Long idAcao ) {

        acaoRepository.deleteById( idAcao );

    }

    private void validarAcao( Acao acao ) {

        if( acao.getMeta() == null && acao.getMeta().getId() == null ) {
            throw new NegocioException( "Não foi informada uma meta para a ação!" );
        }

        if( acao.getEstruturaOrganizacional() == null && acao.getEstruturaOrganizacional().getId() == null ) {
            throw new NegocioException( "A estrutura organizacional não foi informada!" );
        }

        var metaOpt = metaRepository.findById( acao.getMeta().getId() );
        if( metaOpt.isEmpty() ) {
            throw new NegocioException( "A meta informada para a ação não é válida!" );
        } else {
            acao.setMeta( metaOpt.get() );
        }

        var estruturaOpt = estruturaOrganizacionalRepository.findById( acao.getEstruturaOrganizacional().getId() );
        if( estruturaOpt.isEmpty() ) {
            throw new NegocioException( "A estrutura organizacional informada não é válida! " );
        } else {
            acao.setEstruturaOrganizacional( estruturaOpt.get() );
        }

    }

}
