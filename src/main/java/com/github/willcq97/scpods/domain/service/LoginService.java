package com.github.willcq97.scpods.domain.service;

import com.github.willcq97.scpods.api.dto.input.LoginUsuarioDTO;

public interface LoginService {

    void validarLogin( LoginUsuarioDTO usuario );

}
