package br.ufes.willcq.scpods.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "tb_metas" )
public class Meta {

    @Id
    private String id;

    private String descricao;

    @ManyToOne
    @JoinColumn( name = "idObjetivo" )
    private Objetivo objetivo;

}
