package br.ufes.willcq.scpods.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import br.ufes.willcq.scpods.api.dto.select.SelectModel;
import br.ufes.willcq.scpods.domain.model.Lotacao;
import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;

public interface LotacaoRepository extends ListCrudRepository<Lotacao, Long> {

    List<Lotacao> findByCampus( CampusEnum campus );

    @Query( "SELECT lt.id as value, lt.descricao as description FROM Lotacao lt" )
    List<SelectModel<Long>> listarOpcoesLotacao();

}
