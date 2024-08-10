package br.ufes.willcq.scpods.domain.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.scpods.api.dto.AcaoSearchDTO;
import br.ufes.willcq.scpods.api.dto.AcaoSearchOptions;
import br.ufes.willcq.scpods.domain.exception.BusinessException;
import br.ufes.willcq.scpods.domain.exception.EntityNotFoundException;
import br.ufes.willcq.scpods.domain.model.Acao;
import br.ufes.willcq.scpods.domain.model.Coordenador;
import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;
import br.ufes.willcq.scpods.domain.repository.AcaoRepository;
import br.ufes.willcq.scpods.domain.repository.LocalRepository;
import br.ufes.willcq.scpods.domain.repository.LotacaoRepository;
import br.ufes.willcq.scpods.domain.repository.MetaRepository;
import br.ufes.willcq.scpods.domain.repository.UnidadeRepository;
import br.ufes.willcq.scpods.domain.service.AcaoService;

@Service
public class AcaoServiceImpl implements AcaoService {

    @Autowired
    private AcaoRepository acaoRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private LotacaoRepository lotacaoRepository;

    @Autowired
    private MetaRepository metaRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Override
    public boolean existsById( Long id ) {
        return acaoRepository.existsById( id );
    }

    @Override
    public Acao findById( Long id ) {
        return acaoRepository.findById( id ).orElseThrow(
                () -> new EntityNotFoundException( "A ação informada não foi encontrada!" ) );
    }

    @Override
    public Optional<Acao> findAcaoById( Long id ) {
        var acaoOptional = acaoRepository.findById( id );
        if( acaoOptional.isPresent() && acaoOptional.get().getAceito() ) {
            return acaoOptional;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Acao> findSubmissaoById( Long id ) {

        var acaoOptional = acaoRepository.findById( id );
        if( acaoOptional.isPresent() && !acaoOptional.get().getAceito() ) {
            return acaoOptional;
        }
        return Optional.empty();
    }

    @Override
    public List<Acao> listar( boolean aceito ) {
        return acaoRepository.findByAceito( aceito );
    }

    @Override
    public List<Acao> listarPorCampus( boolean aceito, String campus ) {

        var campusEnum = CampusEnum.obterEnum( campus );

        if( campusEnum == null ) {
            throw new BusinessException( "O valor informado para o campus não é válido!" );
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

    @Override
    public List<Acao> listarPorUnidade( boolean aceito, String codigoUnidade ) {

        if( codigoUnidade == null || codigoUnidade.isBlank() ) {
            throw new BusinessException( "O valor informado para o código da unidade é inválido" );
        }

        var optUnidade = unidadeRepository.findByCodigo( codigoUnidade.toUpperCase() );
        if( optUnidade.isEmpty() ) {
            throw new EntityNotFoundException( "Não há nenhuma unidade para o código informado" );
        }

        return optUnidade.get().getLocais()
                .stream()
                .flatMap( local -> local.getAcoes().stream() )
                .filter( acao -> acao.getAceito().equals( aceito ) )
                .toList();

    }

    @Override
    public List<AcaoSearchDTO> searchAcoes( AcaoSearchOptions options ) {

        return acaoRepository.search(
                options.getTitulo(),
                this.obterCampusParaSearch( options.getCampus() ),
                options.getNomeCoordenador(),
                options.getNomeLocal(),
                options.getSiglaLotacao(),
                options.getNomeUnidade(),
                options.getCodigoObjetivo(),
                options.getCodigoUnidade(),
                options.getDataInicial(),
                options.getDataFinal(),
                true );

    }

    @Override
    public List<AcaoSearchDTO> searchSubmissoes( AcaoSearchOptions options ) {

        return acaoRepository.search(
                options.getTitulo(),
                this.obterCampusParaSearch( options.getCampus() ),
                options.getNomeCoordenador(),
                options.getNomeLocal(),
                options.getSiglaLotacao(),
                options.getNomeUnidade(),
                options.getCodigoObjetivo(),
                options.getCodigoUnidade(),
                options.getDataInicial(),
                options.getDataFinal(),
                false );

    }

    @Override
    @Transactional
    public void inserirSubmissao( Acao acao ) {

        var optAcao = acaoRepository.findByTitulo( acao.getTitulo() );
        if( optAcao.isPresent() ) {
            throw new BusinessException( "Já existe uma ação cadastrada com esse título!" );
        }

        this.validarAcao( acao );

        acao.setAceito( Boolean.FALSE );
        acao.setDataCadastro( LocalDate.now() );

        acaoRepository.save( acao );
    }

    @Override
    @Transactional
    public Acao atualizar( Acao acao ) {

        var optAcao = acaoRepository.findByTitulo( acao.getTitulo() );
        if( optAcao.isPresent() && !acao.getId().equals( optAcao.get().getId() ) ) {
            throw new BusinessException( "Já existe uma ação diferente cadastrada com esse título!" );
        }

        this.validarAcao( acao );

        acao.setAceito( optAcao.get().getAceito() );
        acao.setDataCadastro( optAcao.get().getDataCadastro() );

        return acaoRepository.save( acao );

    }

    @Override
    @Transactional
    public void excluirSubmissao( Long idAcao ) {

        var acao = this.findById( idAcao );
        if( acao.getAceito() ) {
            throw new BusinessException( "A submissão informada já foi aceita e não pode ser apagada." );
        }

        acaoRepository.deleteById( idAcao );
    }

    @Override
    @Transactional
    public void aceitarSubmissao( Long idAcao ) {

        var acao = this.findById( idAcao );
        if( acao.getAceito() ) {
            throw new BusinessException( "A submissão informada já foi aceita!" );
        }
        acaoRepository.aceitarSubmissao( idAcao );
    }

    private CampusEnum obterCampusParaSearch( String campus ) {
        var campusEnum = CampusEnum.obterEnum( campus );
        if( campus != null && campusEnum == null ) {
            throw new BusinessException( "O valor informado para o campus não é válido!" );
        }
        return campusEnum;
    }

    private void validarAcao( Acao acao ) {

        if( acao.getMeta() == null || acao.getMeta().getId() == null ) {
            throw new BusinessException( "Não foi informada uma meta para a ação!" );
        }

        var metaOpt = metaRepository.findById( acao.getMeta().getId() );
        if( metaOpt.isEmpty() ) {
            throw new BusinessException( "A meta informada para a ação não é válida!" );
        } else {
            acao.setMeta( metaOpt.get() );
        }

        if( acao.getLocal() == null || acao.getLocal().getId() == null ) {
            throw new BusinessException( "O local da ação não foi informado!" );
        }

        var localOpt = localRepository.findById( acao.getLocal().getId() );
        if( localOpt.isEmpty() ) {
            throw new BusinessException( "O local informado não é válido!" );
        } else {
            acao.setLocal( localOpt.get() );
        }

        if( acao.getLotacao() == null || acao.getLotacao().getId() == null ) {
            throw new BusinessException( "A lotação da ação não foi informada!" );
        }

        var lotacaoOpt = lotacaoRepository.findById( acao.getLotacao().getId() );
        if( lotacaoOpt.isEmpty() ) {
            throw new BusinessException( "A lotação informada não é válida!" );
        } else {
            acao.setLotacao( lotacaoOpt.get() );
        }

        this.validarCoordenador( acao.getCoordenador() );

    }

    private void validarCoordenador( Coordenador coordenador ) {

        if( coordenador == null ) {
            throw new BusinessException( "Não foram informados os dados do coordenador!" );
        }

        if( coordenador.getNome() == null || coordenador.getNome().isEmpty() ) {
            throw new BusinessException( "Não foi informado o nome do coordenador!" );
        }

        if( coordenador.getEmail() == null || coordenador.getEmail().isEmpty() ) {
            throw new BusinessException( "Não foi informado o e-mail do coordenador!" );
        }

        if( coordenador.getTipoVinculo() == null ) {
            throw new BusinessException( "Não foi informado o vínculo do coordenador!" );
        }
    }

}
