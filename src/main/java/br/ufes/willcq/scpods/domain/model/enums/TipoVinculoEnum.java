package br.ufes.willcq.scpods.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoVinculoEnum {

    PROFESSOR( "Professor(a)" ),
    TECNICO_ADM( "Servidor técnico-administrativo" ),
    ALUNO_POS( "Aluno(a) de pós-graduação" ),
    ALUNO_GRADUACAO( "Aluno(a) de graduação" ),
    OUTRO( "Outro" );

    private String descricao;

}
