package com.github.willcq97.scpods.api.dto.search;

import java.time.LocalDate;

public record AcaoSearchOptionsDTO( String titulo, String campus, LocalDate dataInicial, LocalDate dataFinal, String nomeCoordenador, String nomeLocal, String nomeUnidade, String siglaLotacao, String codigoObjetivo, String codigoUnidade ) {

}
