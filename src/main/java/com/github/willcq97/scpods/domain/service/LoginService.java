package com.github.willcq97.scpods.domain.service;

import org.springframework.validation.annotation.Validated;

import com.github.willcq97.scpods.api.dto.input.LoginUsuarioDTO;

@Validated
public interface LoginService {

    void validar( LoginUsuarioDTO usuario );

}
