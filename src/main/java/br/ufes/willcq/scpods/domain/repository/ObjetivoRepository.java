package br.ufes.willcq.scpods.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import br.ufes.willcq.scpods.domain.model.Objetivo;

public interface ObjetivoRepository extends ListCrudRepository<Objetivo, Long> {

    Optional<Objetivo> findByCodigo( String codigo );

}
