package br.ufes.willcq.scpods.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CampusEnum {

    ALEGRE( "Alegre" ),
    GOIABEIRAS( "Goiabeiras" ),
    MARUIPE( "Maruípe" ),
    SAO_MATEUS( "São Mateus" );

    private String descricao;

    public static CampusEnum obterEnum( String campus ) {

        if( campus == null || campus.isBlank() ) {
            return null;
        }

        for( CampusEnum e : CampusEnum.values() ) {
            if( e.name().equals( campus.toUpperCase() ) ) {
                return e;
            }
        }

        return null;
    }
}
