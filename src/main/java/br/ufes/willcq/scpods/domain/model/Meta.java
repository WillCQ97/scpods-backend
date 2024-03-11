package br.ufes.willcq.scpods.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table( name = "tb_metas" )
public class Meta {

    @Id
    private String id;

    private String descricao;

    @ManyToOne
    @JoinColumn( name = "idObjetivo" )
    private Objetivo objetivo;

}
