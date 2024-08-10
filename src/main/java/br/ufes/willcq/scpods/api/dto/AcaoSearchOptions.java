package br.ufes.willcq.scpods.api.dto;

import java.time.LocalDate;

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
    private String campus;

    private LocalDate dataInicial;
    private LocalDate dataFinal;

    private String nomeCoordenador;
    private String nomeLocal;
    private String nomeUnidade;

    private String siglaLotacao;
    private String codigoObjetivo;
    private String codigoUnidade;

}
