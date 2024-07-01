package br.ufes.willcq.scpods.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AcaoGridDTO {

    private Long id;
    private String titulo;
    private String codigoObjetivo;
    private String codigoMeta;
    private String nomePrincipalLocal;
    private String nomeCoordenador;
    private String siglaLotacao;

}
