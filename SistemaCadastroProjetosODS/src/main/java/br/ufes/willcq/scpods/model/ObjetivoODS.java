package br.ufes.willcq.scpods.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "tb_objetivos_ods" )
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ObjetivoODS {

    @Id
    private Long id;

    private String titulo;

    private String descricao;

    @Column( name = "cod_cor" )
    private String codigoCor;

    @OneToMany( mappedBy = "idObjetivo" )
    private List<MetaODS> metas;
}
