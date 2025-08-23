package com.github.willcq97.scpods.domain.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import com.github.willcq97.scpods.domain.model.entity.Objetivo;

public interface ObjetivoRepository extends ListCrudRepository<Objetivo, Long> {

    @Query( "SELECT o FROM Objetivo o ORDER BY o.id" )
    List<Objetivo> findAllOrdered();

    Optional<Objetivo> findByCodigo( String codigo );

}
