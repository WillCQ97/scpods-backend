package br.ufes.willcq.scpods.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AcaoSearchOptions {

    private String titulo;
    private String nomeCoordenador;
    private String nomeLotacao;
    private String codigoObjetivo;
    private String campus;
    private String nomeUnidade;

}
