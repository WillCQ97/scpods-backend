package br.ufes.willcq.scpods.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufes.willcq.scpods.api.util.SearchCriteria;
import br.ufes.willcq.scpods.domain.model.Acao;

@Repository
public interface AcaoSearchRepository {

    public Iterable<Acao> searchAcao( final List<SearchCriteria> params );

}
