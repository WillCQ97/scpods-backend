package br.ufes.willcq.scpods.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "tb_objetivos" )
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Objetivo {

    @Id
    private Long id;

    private String titulo;

    private String descricao;

    @OneToMany( mappedBy = "idObjetivo" )
    private List<Meta> metas;

}
