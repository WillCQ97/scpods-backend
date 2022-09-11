package br.ufes.willcq.scpods.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "metas_ods" )
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MetaODS {

    @Id
    @Column( name = "id_meta" )
    private String id;

    private Long idObjetivo;

    private String descricao;

}
