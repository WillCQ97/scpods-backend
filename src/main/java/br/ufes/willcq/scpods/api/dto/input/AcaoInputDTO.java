package br.ufes.willcq.scpods.api.dto.input;

import java.time.LocalDate;

import org.locationtech.jts.geom.Point;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AcaoInputDTO {

    private String titulo;
    private String descricao;
    private Point localizacao;

    private LocalDate dataCadastro;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    private Boolean aceito;

    private MetaInputDTO meta;
    private EstruturaOrganizacionalInputDTO estruturaOrganizacional;

    private CoordenadorInputDTO coordenador;

}
