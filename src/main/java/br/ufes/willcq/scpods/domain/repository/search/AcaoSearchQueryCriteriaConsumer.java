package br.ufes.willcq.scpods.domain.repository.search;

import java.util.function.Consumer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.ufes.willcq.scpods.api.util.SearchCriteria;
import br.ufes.willcq.scpods.domain.model.Acao;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AcaoSearchQueryCriteriaConsumer implements Consumer<SearchCriteria> {

    private Predicate predicate;
    private CriteriaBuilder builder;
    private Root<Acao> root;

    @Override
    public void accept( SearchCriteria param ) {

        if( param.getOperation().equalsIgnoreCase( ">" ) ) {
            predicate = builder.and( predicate, builder.greaterThanOrEqualTo( root.get( param.getKey() ), param.getValue().toString() ) );
        } else if( param.getOperation().equalsIgnoreCase( "<" ) ) {
            predicate = builder.and( predicate, builder.lessThanOrEqualTo( root.get( param.getKey() ), param.getValue().toString() ) );
        } else if( param.getOperation().equalsIgnoreCase( ":" ) ) {
            if( root.get( param.getKey() ).getJavaType() == String.class ) {
                predicate = builder.and( predicate, builder.like( builder.lower( root.get( param.getKey() ) ), "%" + param.getValue().toLowerCase() + "%" ) );
            } else {
                predicate = builder.and( predicate, builder.equal( root.get( param.getKey() ), param.getValue() ) );
            }
        }
    }

}
