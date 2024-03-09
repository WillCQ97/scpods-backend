package br.ufes.willcq.scpods.domain.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.locationtech.jts.geom.Point;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    private Long idd;

    private Long zona;

    private String filename;

    @NotBlank
    @Column( name = "nome_principal" )
    private String nomePrincipal;

    @Column( name = "nome_secundario" )
    private String nomeSecundario;

    @Column( name = "nome_terciario" )
    private String nomeTerciario;

    @NotNull
    private Point localizacao;

    @ManyToOne
    @JoinColumn( name = "id_unidade" )
    private Unidade unidade;

    @OneToMany( mappedBy = "local" )
    private List<Acao> acoes;

    @Transient
    private List<Acao> acoesAceitas;

    @Transient
    private List<Acao> acoesAtivas;

    @Transient
    private List<Acao> submissoes;

    public List<Acao> getAcoesAceitas() {
        if( acoesAceitas == null || acoesAceitas.isEmpty() ) {
            acoesAceitas = acoes.stream().filter( acao -> acao.getAceito() ).toList();
        }
        return acoesAceitas;
    }

    public List<Acao> getAcoesAtivas() {
        if( acoesAtivas == null || acoesAtivas.isEmpty() ) {
            acoesAtivas = this.getAcoesAceitas().stream().filter( acao -> acao.getDataEncerramento() == null ).toList();
        }
        return acoesAtivas;
    }

    public List<Acao> getSubmissoes() {
        if( submissoes == null || submissoes.isEmpty() ) {
            submissoes = acoes.stream().filter( acao -> !acao.getAceito() ).toList();
        }
        return submissoes;
    }

    public Long getQuantidadeProjetosTotais() {
        return Long.valueOf( this.getAcoesAceitas().size() );
    }

    public Long getQuantidadeProjetosAtivos() {
        return this.getAcoesAceitas().stream().filter( acao -> acao.getDataEncerramento() == null ).count();
    }

    public Long getQuantidadeObjetivosAtendidos() {
        return this.getAcoesAceitas().stream().map( acao -> acao.getIdObjetivo() ).distinct().count();
    }

    public Long getIdObjetivoMaisAtendido() {
        var contagemAcoes = this.getAcoesAceitas().stream().map( acao -> acao.getIdObjetivo() ).collect( Collectors.groupingBy( e -> e, Collectors.counting() ) );
        var idOdsMaisAtendido = contagemAcoes.entrySet().stream().max( Map.Entry.comparingByValue() );

        return idOdsMaisAtendido.isPresent() ? idOdsMaisAtendido.get().getKey() : null;
    }

}
