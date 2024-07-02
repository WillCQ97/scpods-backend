package br.ufes.willcq.scpods.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import br.ufes.willcq.scpods.api.dto.AcaoGridDTO;
import br.ufes.willcq.scpods.domain.model.Acao;
import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;

public interface AcaoRepository extends ListCrudRepository<Acao, Long> {

    Optional<Acao> findByTitulo( String titulo );

    List<Acao> findByAceito( Boolean aceito );

    @Query( nativeQuery = true, value = "UPDATE acao SET fl_aceito = TRUE WHERE id_acao = :idAcao" )
    void aceitarSubmissao( Long idAcao );

    @Query( value = """
            SELECT new br.ufes.willcq.scpods.api.dto.AcaoGridDTO(
                a.id as id,
                a.titulo as titulo,
                a.dataCadastro as dataCadastro,
                o.codigo as codigoObjetivo,
                m.codigo as codigoMeta,
                lc.nomePrincipal as nomePrincipal,
                c.nome as nomeCoordenador,
                lt.sigla as siglaLotacao )
            FROM Acao a
                JOIN a.coordenador c
                JOIN a.lotacao lt
                JOIN a.local lc
                JOIN lc.unidade un
                JOIN a.meta m
                JOIN m.objetivo o
            WHERE
                (lower(a.titulo) like concat('%', trim(lower(:titulo)),'%') or :titulo is null)
                and (lower(c.nome) like concat('%', trim(lower(:nomeCoordenador)),'%') or :nomeCoordenador is null)
                and (lower(lc.nomePrincipal) like concat('%', trim(lower(:lotacao)),'%') or :lotacao is null)
                and (lower(o.codigo) like trim(lower(:codigoObjetivo)) or :codigoObjetivo is null)
                and (lower(un.campus) like trim(lower(:campus)) or :campus is null)
                and (lower(un.nome) like concat('%', trim(lower(:nomeUnidade)),'%') or :nomeUnidade is null)
                and (a.aceito = :aceito)
            """ )
    List<AcaoGridDTO> search( String titulo, String nomeCoordenador, String lotacao, String codigoObjetivo, CampusEnum campus, String nomeUnidade, boolean aceito );

}
