package com.github.willcq97.scpods.api.dto.input;

import com.github.willcq97.scpods.domain.model.enums.TipoVinculoEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CoordenadorInputDTO {

    @NotBlank( message = "Não foi informado o nome do coordenador" )
    private String nome;

    @NotBlank( message = "Não foi informado o email do coordenador" )
    private String email;

    @NotNull( message = "Não foi informado o tipo de vínculo do coordenador" )
    private TipoVinculoEnum tipoVinculo;

    private String descricaoVinculo;

}
