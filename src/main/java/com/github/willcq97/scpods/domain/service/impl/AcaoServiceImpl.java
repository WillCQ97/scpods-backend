package com.github.willcq97.scpods.domain.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
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
import com.github.willcq97.scpods.domain.repository.AcaoRepository;
import com.github.willcq97.scpods.domain.repository.LocalRepository;
import com.github.willcq97.scpods.domain.repository.LotacaoRepository;
import com.github.willcq97.scpods.domain.repository.MetaRepository;
import com.github.willcq97.scpods.domain.repository.UnidadeRepository;
import com.github.willcq97.scpods.domain.service.AcaoService;
import com.github.willcq97.scpods.utils.SpecificationUtil;
import com.github.willcq97.scpods.utils.ValidatorsUtil;

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
        return acaoRepository.existsById( requireNonNullId( id ) );
    }

    @Override
    public Acao findById( Long id ) {
        return acaoRepository.findById( requireNonNullId( id ) )
                .orElseThrow( () -> new EntityNotFoundException( "A ação informada não foi encontrada!" ) );
    }

    @Override
    public Acao findAcaoById( Long id ) {
        return acaoRepository
                .findById( requireNonNullId( id ) )
                .filter( Acao::isAceito )
                .orElseThrow( () -> new EntityNotFoundException( "A ação informada não foi encontrada!" ) );
    }

    @Override
    public Acao findSubmissaoById( Long id ) {
        return acaoRepository
                .findById( requireNonNullId( id ) )
                .filter( Acao::isSubmissao )
                .orElseThrow( () -> new EntityNotFoundException( "A submissão informada não foi encontrada!" ) );
    }

    @Override
    public List<Acao> listar( boolean aceito ) {
        return acaoRepository.findByAceito( aceito );
    }

    @Override
    public List<Acao> listarPorCampus( boolean aceito, String campus ) {

        var campusEnum = requireNonNull( CampusEnum.parse( campus ), "O valor informado para o campus não é válido!" );
        var unidades = unidadeRepository.findByCampus( campusEnum );

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
            ValidatorsUtil.validateCampus( options.campus() );
        }

        Specification<Acao> spec = ( root, query, cb ) -> {
            var predicates = cb.conjunction();

            predicates = cb.equal( root.<Boolean>get( "aceito" ), aceito );

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
                .map( AcaoSearchDTO::fromEntity )
                .toList();

    }

    @Override
    @Transactional
    public void inserir( Acao acao ) {

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

        Long acaoId = requireNonNull( acao.getId(), "Não foi informado um id para a ação" );

        var optAcaoAtual = acaoRepository.findById( acaoId );
        if( optAcaoAtual.isEmpty() ) {
            throw new EntityNotFoundException( "A ação que se deseja atualizar não foi encontrada!" );
        }

        this.validar( acao );

        var optAcao = acaoRepository.findByTitulo( acao.getTitulo() );
        if( optAcao.isPresent() && !acaoId.equals( optAcao.get().getId() ) ) {
            throw new BusinessException( "Já existe uma ação diferente cadastrada com esse título!" );
        }

        acao.setAceito( optAcaoAtual.get().getAceito() );

        return acaoRepository.save( acao );
    }

    @Override
    @Transactional
    public void excluir( Long idAcao ) {

        Long id = requireNonNullId( idAcao );
        var acao = findById( id );

        if( acao.isAceito() ) {
            throw new BusinessException( "A ação informada já foi aceita e não pode ser apagada." );
        }

        acaoRepository.deleteById( id );
    }

    @Override
    @Transactional
    public void aceitar( Long idAcao ) {

        var acao = findById( idAcao );
        if( acao.isAceito() ) {
            throw new BusinessException( "A ação informada já foi aceita!" );
        }
        acaoRepository.aceitar( idAcao );
    }

    private void validar( Acao acao ) {

        Long metaId = requireNonNull( acao.getMeta().getId(), "Não foi informada a meta!" );
        if( !metaRepository.existsById( metaId ) ) {
            throw new BusinessException( "A meta informada não foi encontrada!" );
        }

        Long localId = requireNonNull( acao.getLocal().getId(), "Não foi informado o local!" );
        if( !localRepository.existsById( localId ) ) {
            throw new BusinessException( "O local informado não foi encontrado!" );
        }

        Long lotacaoId = requireNonNull( acao.getLotacao().getId(), "Não foi informada a lotação!" );
        if( !lotacaoRepository.existsById( lotacaoId ) ) {
            throw new BusinessException( "A lotação informada não foi encontrada!" );
        }
    }

    private @NonNull Long requireNonNullId( Long value ) {
        return ValidatorsUtil.requireNonNullId( value );
    }

    private @NonNull <T> T requireNonNull( T value, String message ) {
        return ValidatorsUtil.requireNonNull( value, message );
    }
}
