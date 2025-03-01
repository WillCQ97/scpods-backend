package com.github.willcq97.scpods.domain.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BusinessException( String mensagem ) {
        super( mensagem );
    }

}
