package br.ufes.willcq.scpods.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CentroDTO {

    private Long id;
    private UnidadeDTO unidade;
    private String nome;
    private String sigla;

}
