package br.ufes.willcq.scpods.domain.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table( name = "tb_objetivos" )
public class Objetivo {

    @Id
    private Long id;

    private String titulo;

    private String descricao;

    @OneToMany( mappedBy = "objetivo" )
    private List<Meta> metas;

}
