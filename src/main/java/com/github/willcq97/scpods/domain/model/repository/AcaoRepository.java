package com.github.willcq97.scpods.domain.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import com.github.willcq97.scpods.domain.model.entity.Acao;

public interface AcaoRepository extends ListCrudRepository<Acao, Long>, JpaSpecificationExecutor<Acao> {

    Optional<Acao> findByTitulo( String titulo );

    boolean existsByTitulo( String titulo );

    List<Acao> findByAceito( Boolean aceito );

    @Modifying
    @Query( nativeQuery = true, value = "UPDATE tb_acoes SET fl_aceito = TRUE WHERE id = :idAcao" )
    void aceitarSubmissao( Long idAcao );

}
