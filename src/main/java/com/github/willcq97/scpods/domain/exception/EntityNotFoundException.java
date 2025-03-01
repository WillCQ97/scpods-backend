package com.github.willcq97.scpods.domain.exception;

public class EntityNotFoundException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException( String mensagem ) {
        super( mensagem );
    }

}
