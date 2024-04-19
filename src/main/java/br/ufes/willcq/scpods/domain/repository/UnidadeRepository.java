package br.ufes.willcq.scpods.domain.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import br.ufes.willcq.scpods.domain.model.Unidade;
import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;

public interface UnidadeRepository extends ListCrudRepository<Unidade, Long> {

    List<Unidade> findByCampus( CampusEnum campus );

    List<Unidade> findByCodigo( String codigo );
}
