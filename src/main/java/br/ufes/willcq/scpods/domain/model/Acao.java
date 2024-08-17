package br.ufes.willcq.scpods.domain.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table( name = "tb_acoes" )
public class Acao {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank( message = "Não foi informado o título da ação!" )
    private String titulo;

    @NotBlank( message = "Não foi informada a descrição da ação!" )
    private String descricao;

    @Column( name = "url_evidencia" )
    private String urlEvidencia;

    @Column( name = "dt_cadastro" )
    @NotNull
    private LocalDate dataCadastro;

    @Column( name = "dt_inicio" )
    @NotNull( message = "Não foi informada a data de início do ação!" )
    private LocalDate dataInicio;

    @Column( name = "dt_encerramento" )
    private LocalDate dataEncerramento;

    @Column( name = "fl_aceito" )
    private Boolean aceito;

    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "id_coordenador" )
    @NotNull( message = "Não foi informado o coordenador da ação!" )
    private Coordenador coordenador;

    @ManyToOne
    @JoinColumn( name = "id_meta" )
    @NotNull( message = "Não foi informada uma meta para a ação!" )
    private Meta meta;

    @ManyToOne
    @JoinColumn( name = "id_local" )
    @NotNull( message = "Não foi informado um local para a ação!" )
    private Local local;

    @ManyToOne
    @JoinColumn( name = "id_lotacao" )
    @NotNull( message = "Não foi informada uma lotação para a ação!" )
    private Lotacao lotacao;

    public Long getIdObjetivo() {
        return this.getMeta().getObjetivo().getId();
    }

    public String getCodigoObjetivo() {
        return this.getMeta().getObjetivo().getCodigo();
    }
}
