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
public class LocalAcaoResponseDTO {

    private Long id;
    private Long idd;
    private String nomePrincipal;
    private String nomeSecundario;
    private String nomeTerciario;

    private Point localizacao;

    private UnidadeAcaoReponseDTO unidade;

}
