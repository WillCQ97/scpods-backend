package br.ufes.willcq.scpods.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import br.ufes.willcq.scpods.api.dto.select.SelectModel;
import br.ufes.willcq.scpods.domain.model.Unidade;
import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;

public interface UnidadeRepository extends ListCrudRepository<Unidade, Long> {

    List<Unidade> findByCampus( CampusEnum campus );

    Optional<Unidade> findByCodigo( String codigo );

    @Query( "SELECT u.codigo as value, u.nome as description FROM Unidade u" )
    List<SelectModel<String>> listarOpcoesUnidades();

}
