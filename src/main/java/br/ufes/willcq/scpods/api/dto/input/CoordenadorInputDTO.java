package br.ufes.willcq.scpods.api.dto.input;

import br.ufes.willcq.scpods.domain.model.enums.TipoVinculoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoordenadorInputDTO {

    @NotBlank( message = "Não foi informado o nome do coordenador" )
    private String nome;

    @NotBlank( message = "Não foi informado o email do coordenador" )
    private String email;

    @NotNull( message = "Não foi informado o tipo do vínculo do coordenador" )
    private TipoVinculoEnum tipoVinculo;

    private String descricaoVinculo;

}
