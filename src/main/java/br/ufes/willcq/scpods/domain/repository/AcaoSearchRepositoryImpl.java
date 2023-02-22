package br.ufes.willcq.scpods.domain.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.ufes.willcq.scpods.api.util.SearchCriteria;
import br.ufes.willcq.scpods.domain.model.Acao;
import br.ufes.willcq.scpods.domain.repository.search.AcaoSearchQueryCriteriaConsumer;

@Repository
public class AcaoSearchRepositoryImpl implements AcaoSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Iterable<Acao> searchAcao( List<SearchCriteria> params ) {

        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Acao> query = builder.createQuery( Acao.class );
        final Root<Acao> root = query.from( Acao.class );

        Predicate predicate = builder.conjunction();
        var searchConsumer = new AcaoSearchQueryCriteriaConsumer( predicate, builder, root );

        params.stream().forEach( searchConsumer );
        predicate = searchConsumer.getPredicate();
        query.where( predicate );

        return entityManager.createQuery( query ).getResultList();

    }

}
