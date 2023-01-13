package br.ufes.willcq.scpods.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table( name = "tb_objetivos" )
public class Objetivo {

    @Id
    private Long id;

    private String titulo;

    private String descricao;

    @OneToMany( mappedBy = "idObjetivo" )
    private List<Meta> metas;

}
