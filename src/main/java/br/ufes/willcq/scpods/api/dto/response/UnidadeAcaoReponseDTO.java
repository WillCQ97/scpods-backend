package br.ufes.willcq.scpods.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UnidadeAcaoReponseDTO {

    private Long id;
    private String nome;
    private String campus;
    private String codigo;

}
