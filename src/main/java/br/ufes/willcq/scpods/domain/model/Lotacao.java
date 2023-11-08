package br.ufes.willcq.scpods.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Entity
@Table( name = "tb_lotacoes" )
public class Lotacao {

    @Id
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private Point localizacao;

    @ManyToOne
    @JoinColumn( name = "id_centro" )
    private Centro centro;

    @ManyToOne
    @JoinColumn( name = "id_unidade" )
    private Unidade unidade;

    @OneToMany( mappedBy = "lotacao" )
    private List<Acao> acoes;

}
