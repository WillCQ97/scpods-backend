package br.ufes.willcq.scpods.domain.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.locationtech.jts.geom.Point;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "tb_locais" )
public class Local {

    @Id
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private Point localizacao;

    @ManyToOne
    @JoinColumn( name = "id_centro" )
    private Centro centro;

    @ManyToOne
    @JoinColumn( name = "id_unidade" )
    private Unidade unidade;

    @OneToMany( mappedBy = "local" )
    private List<Acao> acoes;

    public Long getQuantidadeProjetosTotais() {
        return Long.valueOf( acoes.size() );
    }

    public Long getQuantidadeProjetosAtivos() {
        return acoes.stream().filter( acao -> acao.getDataCadastro() != null ).count();
    }

    public Long getQuantidadeObjetivosAtendidos() {
        return acoes.stream().map( acao -> acao.getIdObjetivo() ).distinct().count();
    }

    public Long getObjetivoMaisAtendido() {
        var contagemAcoes = acoes.stream().map( acao -> acao.getIdObjetivo() ).collect( Collectors.groupingBy( e -> e, Collectors.counting() ) );
        var idOdsMaisAtendido = contagemAcoes.entrySet().stream().max( Map.Entry.comparingByValue() );

        return idOdsMaisAtendido.isPresent() ? idOdsMaisAtendido.get().getKey() : null;
    }

}
