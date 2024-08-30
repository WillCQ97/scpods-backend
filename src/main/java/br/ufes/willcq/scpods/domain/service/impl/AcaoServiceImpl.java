package br.ufes.willcq.scpods.domain.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.scpods.api.dto.AcaoSearchDTO;
import br.ufes.willcq.scpods.api.dto.AcaoSearchOptions;
import br.ufes.willcq.scpods.domain.exception.BusinessException;
import br.ufes.willcq.scpods.domain.exception.EntityNotFoundException;
import br.ufes.willcq.scpods.domain.model.Acao;
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
        return acaoRepository.findById( id )
                .orElseThrow( () -> new EntityNotFoundException( "A ação informada não foi encontrada!" ) );
    }

    @Override
    public Acao findAcaoById( Long id ) {
        return acaoRepository
                .findById( id )
                .filter( acao -> Boolean.TRUE.equals( acao.getAceito() ) )
                .orElseThrow( () -> new EntityNotFoundException( "Não foi encontrada uma ação para o id informado!" ) );
    }

    @Override
    public Acao findSubmissaoById( Long id ) {

        return acaoRepository
                .findById( id )
                .filter( acao -> Boolean.FALSE.equals( acao.getAceito() ) )
                .orElseThrow( () -> new EntityNotFoundException( "Não foi encontrada uma submissão para o id informado!" ) );
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
    public List<AcaoSearchDTO> search( AcaoSearchOptions options, boolean aceito ) {

        if( options.getCampus() != null ) {
            this.validarCampusSearch( options.getCampus() );
        }

        return acaoRepository.search(
                options.getTitulo(),
                options.getCampus(),
                options.getNomeCoordenador(),
                options.getNomeLocal(),
                options.getSiglaLotacao(),
                options.getNomeUnidade(),
                options.getCodigoObjetivo(),
                options.getCodigoUnidade(),
                options.getDataInicial(),
                options.getDataFinal(),
                aceito );

    }

    @Override
    @Transactional
    public void inserirSubmissao( Acao acao ) {

        if( acaoRepository.existsByTitulo( acao.getTitulo() ) ) {
            throw new BusinessException( "Já existe uma ação cadastrada com esse título!" );
        }

        this.validar( acao );

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

        this.validar( acao );

        acao.setAceito( optAcao.get().getAceito() );

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

    private void validarCampusSearch( String campus ) {

        var campusEnum = CampusEnum.obterEnum( campus );
        if( campus != null && campusEnum == null ) {
            throw new BusinessException( "O valor informado para o campus não é válido!" );
        }
    }

    private void validar( Acao acao ) {

        if( !metaRepository.existsById( acao.getMeta().getId() ) ) {
            throw new BusinessException( "A meta informada para a ação não foi encontrada!" );
        }

        if( !localRepository.existsById( acao.getLocal().getId() ) ) {
            throw new BusinessException( "O local informado não foi encontrado!" );
        }

        if( !lotacaoRepository.existsById( acao.getLotacao().getId() ) ) {
            throw new BusinessException( "A lotação informada não foi encontrada!" );
        }

    }

}
