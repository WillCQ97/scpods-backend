package br.ufes.willcq.scpods.domain.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import br.ufes.willcq.scpods.domain.model.Lotacao;
import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;

public interface LotacaoRepository extends ListCrudRepository<Lotacao, Long> {

    List<Lotacao> findByCampus( CampusEnum campus );

}
