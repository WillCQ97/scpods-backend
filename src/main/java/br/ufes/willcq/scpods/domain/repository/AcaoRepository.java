package br.ufes.willcq.scpods.domain.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import br.ufes.willcq.scpods.api.dto.AcaoSearchDTO;
import br.ufes.willcq.scpods.domain.model.Acao;

public interface AcaoRepository extends ListCrudRepository<Acao, Long> {

    Optional<Acao> findByTitulo( String titulo );

    List<Acao> findByAceito( Boolean aceito );

    @Modifying
    @Query( nativeQuery = true, value = "UPDATE tb_acoes SET fl_aceito = TRUE WHERE id = :idAcao" )
    void aceitarSubmissao( Long idAcao );

    @Query( value = """
            SELECT new br.ufes.willcq.scpods.api.dto.AcaoSearchDTO(
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
                and (a.dataCadastro >= cast(:dataInicial as date) or cast(:dataInicial as date) is null)
                and (a.dataCadastro <= cast(:dataFinal as date) or cast(:dataFinal as date) is null)
                and (
                    lower(lc.nomePrincipal) like concat('%', trim(lower(:nomeLocal)),'%')
                    or lower(lc.nomeSecundario) like concat('%', trim(lower(:nomeLocal)),'%')
                    or lower(lc.nomeTerciario) like concat('%', trim(lower(:nomeLocal)),'%')
                    or :nomeLocal is null
                )
                and (lower(lt.sigla) like concat('%', trim(lower(:siglaLotacao)),'%') or :siglaLotacao is null)
                and (lower(o.codigo) like trim(lower(:codigoObjetivo)) or :codigoObjetivo is null)
                and (lower(un.codigo) like trim(lower(:codigoUnidade)) or :codigoUnidade is null)
                and (lower(un.nome) like concat('%', trim(lower(:nomeUnidade)),'%') or :nomeUnidade is null)
                and (lower(un.campus) like trim(lower(:campus)) or :campus is null)
                and (a.aceito = :aceito)
            """ )
    List<AcaoSearchDTO> search( String titulo, String campus, String nomeCoordenador, String nomeLocal, String siglaLotacao, String nomeUnidade, String codigoObjetivo, String codigoUnidade, LocalDate dataInicial, LocalDate dataFinal, boolean aceito );

}
