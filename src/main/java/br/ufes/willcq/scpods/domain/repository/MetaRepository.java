package br.ufes.willcq.scpods.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import br.ufes.willcq.scpods.domain.model.Meta;

public interface MetaRepository extends ListCrudRepository<Meta, Long> {

    Optional<Meta> findByCodigo( String codigo );

}
