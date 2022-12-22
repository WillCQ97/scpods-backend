package br.ufes.willcq.scpods.model;

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
@Table( name = "tb_centros" )
public class Centro {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn( name = "id_unidade" )
    private Unidade unidade;

    private String nome;

    private String sigla;

}
