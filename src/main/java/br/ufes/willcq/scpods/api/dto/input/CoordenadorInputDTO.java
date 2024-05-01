package br.ufes.willcq.scpods.api.dto.input;

import br.ufes.willcq.scpods.domain.model.enums.TipoVinculoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoordenadorInputDTO {

    private String nome;
    private String email;
    private TipoVinculoEnum tipoVinculo;

}
