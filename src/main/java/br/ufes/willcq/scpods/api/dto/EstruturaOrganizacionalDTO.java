package br.ufes.willcq.scpods.api.dto;

import org.locationtech.jts.geom.Point;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstruturaOrganizacionalDTO {

    private Long id;
    private CentroDTO centro;
    private UnidadeDTO unidade;

    private Point localizacao;
}
