package com.github.willcq97.scpods.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.github.willcq97.scpods.domain.model.Meta;

public interface MetaRepository extends ListCrudRepository<Meta, Long> {

    Optional<Meta> findByCodigo( String codigo );

}
