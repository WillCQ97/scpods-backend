package com.github.willcq97.scpods.domain.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import com.github.willcq97.scpods.api.dto.select.SelectModel;
import com.github.willcq97.scpods.domain.model.entity.Lotacao;
import com.github.willcq97.scpods.domain.model.enums.CampusEnum;

public interface LotacaoRepository extends ListCrudRepository<Lotacao, Long> {

    List<Lotacao> findByCampus( CampusEnum campus );

    @Query( "SELECT lt.id as value, lt.sigla || ' - ' || lt.descricao as description FROM Lotacao lt" )
    List<SelectModel<Long>> listarOpcoesLotacao();

}
