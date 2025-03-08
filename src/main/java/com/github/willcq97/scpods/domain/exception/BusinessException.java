package com.github.willcq97.scpods.domain.exception;

import java.io.Serial;

public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BusinessException( String mensagem ) {
        super( mensagem );
    }

}
