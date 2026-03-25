package com.github.willcq97.scpods.utils;

import com.github.willcq97.scpods.domain.exception.BusinessException;
import com.github.willcq97.scpods.domain.model.enums.CampusEnum;

import org.jspecify.annotations.NonNull;

public class ValidatorsUtil {

    private ValidatorsUtil() {
        throw new IllegalStateException( "Utility class" );
    }

    public static @NonNull <T> T requireNonNull( T value, String message ) {
        if( value == null ) {
            throw new BusinessException( message );
        }
        return value;
    }

    public static @NonNull Long requireNonNullId( Long id ) {
        if( id == null || id <= 0 ) {
            throw new BusinessException( "O id informado é inválido!" );
        }
        return id;
    }

    public static void validateCampus( String campus ) {
        var campusEnum = CampusEnum.parse( campus );
        if( campusEnum == null ) {
            throw new BusinessException( "O valor informado para o campus não é válido!" );
        }
    }
}
