package br.ufes.willcq.scpods.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @OneToOne
    @JoinColumn( name = "id_coordenador" )
    @NotNull( message = "Não foi informado o coordenador da ação!" )
    private Coordenador coordenador;

    @ManyToOne
    @JoinColumn( name = "id_meta" )
    @NotNull( message = "Não foi informada uma meta para a ação!" )
    private Meta meta;

    @ManyToOne
    @JoinColumn( name = "id_local" )
    private Local local;

    public Long getIdObjetivo() {
        return this.getMeta().getObjetivo().getId();
    }
}
