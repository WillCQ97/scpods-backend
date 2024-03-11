package br.ufes.willcq.scpods.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import br.ufes.willcq.scpods.domain.model.Acao;

public interface AcaoRepository extends ListCrudRepository<Acao, Long> {

    Optional<Acao> findByTitulo( String titulo );

    List<Acao> findByAceito( Boolean aceito );

}
