package com.github.willcq97.scpods.domain.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.willcq97.scpods.api.dto.search.AcaoSearchDTO;
import com.github.willcq97.scpods.api.dto.search.AcaoSearchOptionsDTO;
import com.github.willcq97.scpods.domain.exception.BusinessException;
import com.github.willcq97.scpods.domain.exception.EntityNotFoundException;
import com.github.willcq97.scpods.domain.model.entity.Acao;
import com.github.willcq97.scpods.domain.model.entity.Coordenador;
import com.github.willcq97.scpods.domain.model.entity.Local;
import com.github.willcq97.scpods.domain.model.entity.Lotacao;
import com.github.willcq97.scpods.domain.model.entity.Meta;
import com.github.willcq97.scpods.domain.model.entity.Objetivo;
import com.github.willcq97.scpods.domain.model.entity.Unidade;
import com.github.willcq97.scpods.domain.model.enums.CampusEnum;
import com.github.willcq97.scpods.domain.model.repository.AcaoRepository;
import com.github.willcq97.scpods.domain.model.repository.LocalRepository;
import com.github.willcq97.scpods.domain.model.repository.LotacaoRepository;
import com.github.willcq97.scpods.domain.model.repository.MetaRepository;
import com.github.willcq97.scpods.domain.model.repository.UnidadeRepository;
import com.github.willcq97.scpods.domain.service.AcaoService;
import com.github.willcq97.scpods.utils.SpecificationUtil;

import jakarta.persistence.criteria.Join;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AcaoServiceImpl implements AcaoService {

    private final AcaoRepository acaoRepository;

    private final LocalRepository localRepository;

    private final LotacaoRepository lotacaoRepository;

    private final MetaRepository metaRepository;

    private final UnidadeRepository unidadeRepository;

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
    public List<AcaoSearchDTO> search( AcaoSearchOptionsDTO options, boolean aceito ) {

        if( options.campus() != null ) {
            this.validarCampusSearch( options.campus() );
        }

        Specification<Acao> spec = ( root, query, cb ) -> {
            var predicates = cb.conjunction();

            cb.equal( root.<Boolean>get( "aceito" ), aceito );

            predicates = SpecificationUtil.addLikeIgnoreCase( root, cb, predicates, "titulo", options.titulo() );
            predicates = SpecificationUtil.addBetweenDates( root, cb, predicates, "dataCadastro", options.dataInicial(), options.dataFinal() );

            Join<Acao, Coordenador> coordenadorJoin = root.join( "coordenador" );
            predicates = SpecificationUtil.addLikeIgnoreCase( coordenadorJoin, cb, predicates, "nome", options.nomeCoordenador() );

            Join<Acao, Local> localJoin = root.join( "local" );
            if( options.nomeLocal() != null && !options.nomeLocal().isBlank() ) {
                var nomeLocal = options.nomeLocal().trim().toLowerCase();

                var localPredicates = cb.like( cb.lower( localJoin.<String>get( "nomePrincipal" ) ), "%" + nomeLocal + "%" );
                localPredicates = cb.or( localPredicates, cb.like( cb.lower( localJoin.<String>get( "nomeSecundario" ) ), "%" + nomeLocal + "%" ) );
                localPredicates = cb.or( localPredicates, cb.like( cb.lower( localJoin.<String>get( "nomeTerciario" ) ), "%" + nomeLocal + "%" ) );

                predicates = cb.and( predicates, localPredicates );
            }

            Join<Local, Unidade> unidadeJoin = localJoin.join( "unidade" );
            predicates = SpecificationUtil.addLike( unidadeJoin, cb, predicates, "codigo", options.codigoUnidade() );
            predicates = SpecificationUtil.addLikeIgnoreCase( unidadeJoin, cb, predicates, "nome", options.nomeUnidade() );
            predicates = SpecificationUtil.addLike( unidadeJoin, cb, predicates, "campus", options.campus() );

            Join<Acao, Lotacao> lotacaoJoin = root.join( "lotacao" );
            predicates = SpecificationUtil.addLikeIgnoreCase( lotacaoJoin, cb, predicates, "sigla", options.siglaLotacao() );

            Join<Meta, Objetivo> objetivoJoin = root.join( "meta" ).join( "objetivo" );
            predicates = SpecificationUtil.addLike( objetivoJoin, cb, predicates, "codigo", options.codigoObjetivo() );

            return predicates;
        };

        return acaoRepository.findAll( spec )
                .stream()
                .map( acao -> new AcaoSearchDTO( acao.getId(), acao.getTitulo(), acao.getDataCadastro(), acao.getCodigoObjetivo(), acao.getMeta().getCodigo(), acao.getLocal().getNomePrincipal(), acao.getCoordenador().getNome(), acao.getLotacao().getSigla() ) )
                .toList();

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
