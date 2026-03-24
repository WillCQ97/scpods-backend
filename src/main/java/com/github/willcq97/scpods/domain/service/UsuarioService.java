package com.github.willcq97.scpods.domain.service;

import org.springframework.stereotype.Service;

import com.github.willcq97.scpods.api.dto.input.LoginUsuarioDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final LoginService loginService;

    public void validarLoginUfes( LoginUsuarioDTO usuario ) {
        loginService.validar( usuario );
    }

}
