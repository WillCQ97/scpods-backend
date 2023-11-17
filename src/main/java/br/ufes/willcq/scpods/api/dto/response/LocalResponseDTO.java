package br.ufes.willcq.scpods.api.dto.response;

import org.locationtech.jts.geom.Point;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocalResponseDTO {

    private Long id;
    private String nome;
    private Point coordenadas;

    private CentroResponseDTO centro;
    private UnidadeReponseDTO unidade;

}
