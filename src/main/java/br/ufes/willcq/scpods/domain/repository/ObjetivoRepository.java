package br.ufes.willcq.scpods.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import br.ufes.willcq.scpods.domain.model.Objetivo;

public interface ObjetivoRepository extends ListCrudRepository<Objetivo, Long> {

    @Query( "SELECT o FROM Objetivo o ORDER BY o.id" )
    List<Objetivo> findAllOrdered();

    Optional<Objetivo> findByCodigo( String codigo );

}
