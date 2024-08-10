package br.ufes.willcq.scpods.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AcaoSearchFilter {

    private String titulo;
    private String campus;

    private String nomeCoordenador;
    private String nomeLocal;
    private String nomeLotacao;
    private String nomeUnidade;

    private String codigoObjetivo;
    private String codigoUnidade;

}
