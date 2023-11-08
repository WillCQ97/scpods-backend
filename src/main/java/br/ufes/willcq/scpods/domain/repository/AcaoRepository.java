package br.ufes.willcq.scpods.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.ufes.willcq.scpods.domain.model.Acao;

public interface AcaoRepository extends CrudRepository<Acao, Long> {

    Optional<Acao> findByTitulo( String titulo );

}
