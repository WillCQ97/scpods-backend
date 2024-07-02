package br.ufes.willcq.scpods.domain.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.scpods.api.dto.AcaoGridDTO;
import br.ufes.willcq.scpods.api.dto.AcaoGridOptions;
import br.ufes.willcq.scpods.domain.exception.NegocioException;
import br.ufes.willcq.scpods.domain.model.Acao;
import br.ufes.willcq.scpods.domain.model.Coordenador;
import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;
import br.ufes.willcq.scpods.domain.repository.AcaoRepository;
import br.ufes.willcq.scpods.domain.repository.CoordenadorRepository;
import br.ufes.willcq.scpods.domain.repository.LocalRepository;
import br.ufes.willcq.scpods.domain.repository.LotacaoRepository;
import br.ufes.willcq.scpods.domain.repository.MetaRepository;
import br.ufes.willcq.scpods.domain.repository.UnidadeRepository;
import br.ufes.willcq.scpods.domain.service.AcaoService;

@Service
@Transactional
public class AcaoServiceImpl implements AcaoService {

    @Autowired
    private AcaoRepository acaoRepository;

    @Autowired
    private CoordenadorRepository coordenadorRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private LotacaoRepository lotacaoRepository;

    @Autowired
    private MetaRepository metaRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Override
    public Optional<Acao> findById( Long id ) {
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
            throw new NegocioException( "O valor informado para o campus não é válido!" );
        }

        var unidades = unidadeRepository.findByCampus( campusEnum );
        var acoes = new ArrayList<Acao>();
        for( var unidade : unidades ) {
            for( var local : unidade.getLocais() ) {
                acoes.addAll( local.getAcoes() );
            }
        }

        return unidades.stream()
                .flatMap( unidade -> unidade.getLocais().stream() )
                .flatMap( local -> local.getAcoes().stream() )
                .filter( acao -> acao.getAceito().equals( aceito ) )
                .toList();
    }

    public List<Acao> listarPorUnidade( boolean aceito, String codigoUnidade ) {

        if( codigoUnidade == null || codigoUnidade.isBlank() ) {
            throw new NegocioException( "O valor informado para o código da unidade é inválido" );
        }

        var optUnidade = unidadeRepository.findByCodigo( codigoUnidade.toUpperCase() );
        if( optUnidade.isEmpty() ) {
            throw new NegocioException( "Não há nenhuma unidade para o código informado" );
        }

        return optUnidade.get().getLocais()
                .stream()
                .flatMap( local -> local.getAcoes().stream() )
                .filter( acao -> acao.getAceito().equals( aceito ) )
                .toList();

    }

    @Override
    public List<AcaoGridDTO> searchAcoes( AcaoGridOptions options ) {

        var campusEnum = CampusEnum.obterEnum( options.getCampus() );
        if( options.getCampus() != null && campusEnum == null ) {
            throw new NegocioException( "O valor informado para o campus não é válido!" );
        }

        return acaoRepository.searchAcoes( options.getTitulo(), options.getNomeCoordenador(),
                options.getNomeLotacao(), options.getCodigoObjetivo(), campusEnum, options.getNomeUnidade() );
    }

    @Override
    public Acao inserirSubmissao( Acao acao ) {

        var optAcao = acaoRepository.findByTitulo( acao.getTitulo() );
        if( optAcao.isPresent() ) {
            throw new NegocioException( "Já existe uma ação cadastrada com esse título!" );
        }

        this.validarAcao( acao );

        acao.setAceito( Boolean.FALSE );
        acao.setDataCadastro( LocalDate.now() );

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
    public void excluirSubmissao( Long idAcao ) {

        var optAcao = acaoRepository.findById( idAcao );
        if( optAcao.isPresent() ) {
            acaoRepository.deleteById( idAcao );
        }
    }

    @Override
    public void aceitarSubmissao( Long idAcao ) {

        var acao = acaoRepository.findById( idAcao ).get();
        if( acao.getAceito() ) {
            throw new NegocioException( "A ação informada já foi aceita!" );
        }

        acao.setAceito( Boolean.TRUE );
        acaoRepository.save( acao );
    }

    private void validarAcao( Acao acao ) {

        if( acao.getMeta() == null || acao.getMeta().getId() == null ) {
            throw new NegocioException( "Não foi informada uma meta para a ação!" );
        }

        var metaOpt = metaRepository.findById( acao.getMeta().getId() );
        if( metaOpt.isEmpty() ) {
            throw new NegocioException( "A meta informada para a ação não é válida!" );
        } else {
            acao.setMeta( metaOpt.get() );
        }

        if( acao.getLocal() == null || acao.getLocal().getId() == null ) {
            throw new NegocioException( "O local da ação não foi informado!" );
        }

        var localOpt = localRepository.findById( acao.getLocal().getId() );
        if( localOpt.isEmpty() ) {
            throw new NegocioException( "O local informado não é válido!" );
        } else {
            acao.setLocal( localOpt.get() );
        }

        if( acao.getLotacao() == null || acao.getLotacao().getId() == null ) {
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
