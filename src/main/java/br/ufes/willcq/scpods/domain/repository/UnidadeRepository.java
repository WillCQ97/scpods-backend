package br.ufes.willcq.scpods.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufes.willcq.scpods.domain.model.Unidade;
import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;

public interface UnidadeRepository extends CrudRepository<Unidade, Long> {

    List<Unidade> findByCampus( CampusEnum campus );
}
