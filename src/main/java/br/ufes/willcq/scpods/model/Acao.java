package br.ufes.willcq.scpods.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table( name = "tb_acoes" )
public class Acao {

    @Id
    private Long id;

    private String titulo;

    private String descricao;

    private Point localizacao;

    @Column( name = "data_inicio" )
    private LocalDate dataInicio;

    @Column( name = "data_fim" )
    private LocalDate dataFim;

    @Column( name = "fl_aceito" )
    private Boolean aceito;

    @ManyToOne
    @JoinColumn( name = "id_meta" )
    private Meta meta;

    @OneToOne
    @JoinColumn( name = "id_coordenador" )
    private Coordenador coordenador;

    @ManyToOne
    @JoinColumn( name = "id_estrutura_organizacional" )
    private EstruturaOrganizacional estruturaOrganizacional;

}
