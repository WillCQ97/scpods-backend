package br.ufes.willcq.scpods.domain.model.enums;

public enum CampusEnum {

    ALEGRE, GOIABEIRAS, MARUIPE, SAO_MATEUS;

    public static CampusEnum obterEnum( String campus ) {
        for( CampusEnum e : CampusEnum.values() ) {
            if( e.name().equals( campus.toUpperCase() ) ) {
                return e;
            }
        }
        return null;
    }
}
