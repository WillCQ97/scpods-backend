package com.github.willcq97.scpods.domain.model.entity;

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

    private String titulo;

    private String descricao;

    @Column( name = "url_evidencia" )
    private String urlEvidencia;

    @Column( name = "dt_cadastro", updatable = false )
    private LocalDate dataCadastro;

    @Column( name = "dt_inicio" )
    private LocalDate dataInicio;

    @Column( name = "dt_encerramento" )
    private LocalDate dataEncerramento;

    @Column( name = "fl_aceito" )
    private Boolean aceito;

    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "id_coordenador" )
    private Coordenador coordenador;

    @ManyToOne
    @JoinColumn( name = "id_meta" )
    private Meta meta;

    @ManyToOne
    @JoinColumn( name = "id_local" )
    private Local local;

    @ManyToOne
    @JoinColumn( name = "id_lotacao" )
    private Lotacao lotacao;

    public Long getIdObjetivo() {
        return this.getMeta().getObjetivo().getId();
    }

    public String getCodigoObjetivo() {
        return this.getMeta().getObjetivo().getCodigo();
    }
}
