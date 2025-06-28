package com.github.willcq97.scpods.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoordenadorAcaoResponseDTO {

    private Long id;
    private String nome;

    private String tipoVinculo;
    private String descricaoVinculo;

}
