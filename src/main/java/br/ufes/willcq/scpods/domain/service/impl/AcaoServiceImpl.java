package br.ufes.willcq.scpods.domain.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.scpods.domain.exception.NegocioException;
import br.ufes.willcq.scpods.domain.model.Acao;
import br.ufes.willcq.scpods.domain.model.Coordenador;
import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;
import br.ufes.willcq.scpods.domain.repository.AcaoRepository;
import br.ufes.willcq.scpods.domain.repository.CentroRepository;
import br.ufes.willcq.scpods.domain.repository.CoordenadorRepository;
import br.ufes.willcq.scpods.domain.repository.LotacaoRepository;
import br.ufes.willcq.scpods.domain.repository.MetaRepository;
import br.ufes.willcq.scpods.domain.service.AcaoService;

@Service
@Transactional
public class AcaoServiceImpl implements AcaoService {

    @Autowired
    private AcaoRepository acaoRepository;

    @Autowired
    private CoordenadorRepository coordenadorRepository;

    @Autowired
    private CentroRepository centroRepository;

    @Autowired
    private LotacaoRepository lotacaoRepository;

    @Autowired
    private MetaRepository metaRepository;

    @Override
    public Optional<Acao> buscarPeloId( Long id ) {
        return acaoRepository.findById( id );
    }

    @Override
    public List<Acao> listar( boolean aceito ) {
        return acaoRepository.findByAceito( aceito );
    }

    @Override
    public List<Acao> listarPorCampus( boolean aceito, String campus ) {

        var campusEnum = CampusEnum.obterEnum( campus );

        if( campusEnum == null ) {
            throw new NegocioException( "O valor campus informado não é válido!" );
        }

        var centros = centroRepository.findByCampus( campusEnum );
        var acoes = new ArrayList<Acao>();
        for( var centro : centros ) {
            for( var lotacao : centro.getLotacoes() ) {
                acoes.addAll( lotacao.getAcoes() );
            }
        }

        return acoes.stream().filter( acao -> acao.getAceito().equals( aceito ) ).collect( Collectors.toList() );
    }

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
            throw new NegocioException( "Já existe uma ação diferente cadastrada com esse título!" );
        }

        this.validarAcao( acao );

        acao.setAceito( optAcao.get().getAceito() );
        acao.setDataCadastro( optAcao.get().getDataCadastro() );

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

        var metaOpt = metaRepository.findById( acao.getMeta().getId() );
        if( metaOpt.isEmpty() ) {
            throw new NegocioException( "A meta informada para a ação não é válida!" );
        } else {
            acao.setMeta( metaOpt.get() );
        }

        if( acao.getLotacao() == null && acao.getLotacao().getId() == null ) {
            throw new NegocioException( "A lotação da ação não foi informada!" );
        }

        var lotacaoOpt = lotacaoRepository.findById( acao.getLotacao().getId() );
        if( lotacaoOpt.isEmpty() ) {
            throw new NegocioException( "A lotação informada não é válida!" );
        } else {
            acao.setLotacao( lotacaoOpt.get() );
        }

        this.validarCoordenador( acao.getCoordenador() );

    }

    private void validarCoordenador( Coordenador coordenador ) {

        if( coordenador == null ) {
            throw new NegocioException( "Não foram informados os dados do coordenador!" );
        }

        if( coordenador.getNome() == null || coordenador.getNome().isEmpty() ) {
            throw new NegocioException( "Não foi informado o nome do coordenador!" );
        }

        if( coordenador.getEmail() == null || coordenador.getEmail().isEmpty() ) {
            throw new NegocioException( "Não foi informado o e-mail do coordenador!" );
        }

        if( coordenador.getTipoVinculo() == null ) {
            throw new NegocioException( "Não foi informado o vínculo do coordenador!" );
        }

    }

}
