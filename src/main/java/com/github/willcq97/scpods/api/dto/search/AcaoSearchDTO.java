package com.github.willcq97.scpods.api.dto.search;

import java.time.LocalDate;

public record AcaoSearchDTO( Long id, String titulo, LocalDate dataCadastro, String codigoObjetivo, String codigoMeta, String nomeLocal, String nomeCoordenador, String siglaLotacao ) {
}
