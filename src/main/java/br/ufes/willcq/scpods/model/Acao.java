package br.ufes.willcq.scpods.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table( name = "tb_acoes" )
public class Acao {

    @Id
    private Long id;

    private String titulo;

    private String descricao;

    // private Point localizacao;

    @Column( name = "data_inicio" )
    private LocalDate dataInicio;

    @Column( name = "data_fim" )
    private LocalDate dataFim;

    @Column( name = "fl_aceito" )
    private Boolean aceito;

}
