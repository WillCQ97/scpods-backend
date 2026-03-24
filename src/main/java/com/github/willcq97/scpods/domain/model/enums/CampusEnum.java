package com.github.willcq97.scpods.domain.model.enums;

import com.github.willcq97.scpods.domain.exception.BusinessException;

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

    public static CampusEnum parse( String campus ) {

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

    public static CampusEnum validatedParse( String campus ) {

        var campusEnum = CampusEnum.parse( campus );
        if( campusEnum == null ) {
            throw new BusinessException( "O campus informado não é válido!" );
        }
        return campusEnum;
    }
}
