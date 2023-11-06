package br.ufes.willcq.scpods.domain.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.locationtech.jts.geom.Point;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table( name = "tb_lotacoes" )
public class Lotacao {
    // TODO: NO MOMENTO SERÃO APENAS OS DEPARTAMENTOS, CADASTRAR OS DEPARTAMENTOS
    // PREVIAMENTE

    @NotBlank
    private String nome;

    @NotNull
    private Point localizacao;

    @ManyToOne
    @JoinColumn( name = "id_centro" )
    private Centro centro; // TODO: CADASTRAR OS CENTROS PREVIAMENTE

    @ManyToOne
    @JoinColumn( name = "id_unidade" )
    private Unidade unidade; // TODO: CADASTRAR AS UNIDADES PARA ALEGRE PREVIAMENTE

}
