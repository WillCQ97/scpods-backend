package com.github.willcq97.scpods.domain.service;

import org.springframework.stereotype.Service;

import com.github.willcq97.scpods.api.dto.input.LoginUsuarioDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final LoginService loginService;

    public void validarLoginUfes( LoginUsuarioDTO usuario ) {
        loginService.validarLogin( usuario );
    }

}
