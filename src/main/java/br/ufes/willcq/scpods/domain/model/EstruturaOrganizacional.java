package br.ufes.willcq.scpods.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table( name = "tb_estruturas_organizacionais" )
public class EstruturaOrganizacional {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn( name = "id_centro" )
    private Centro centro;

    @ManyToOne
    @JoinColumn( name = "id_unidade" )
    private Unidade unidade;

    private String nome;

    private Point localizacao;

}
