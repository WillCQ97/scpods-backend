package com.github.willcq97.scpods.domain.exception;

import java.io.Serial;

public class EntityNotFoundException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException( String mensagem ) {
        super( mensagem );
    }

}
