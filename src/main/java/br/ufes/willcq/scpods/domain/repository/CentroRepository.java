package br.ufes.willcq.scpods.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufes.willcq.scpods.domain.model.Centro;
import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;

@Deprecated
public interface CentroRepository extends CrudRepository<Centro, Long> {

    List<Centro> findByCampus( CampusEnum campus );

}
