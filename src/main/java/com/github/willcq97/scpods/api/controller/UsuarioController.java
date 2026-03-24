package com.github.willcq97.scpods.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.willcq97.scpods.api.dto.input.LoginUsuarioDTO;
import com.github.willcq97.scpods.domain.service.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping( "/v1/usuarios" )
@Tag( name = "Usuários" )
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping( "/validar-admin" )
    @ResponseStatus( HttpStatus.OK )
    @PreAuthorize( "hasRole('ADMIN')" )
    public String validarAdmin() {
        return "Administrador validado";
    }

    @PostMapping( "/validar-login-ufes" )
    @ResponseStatus( HttpStatus.OK )
    public String validarLoginUfes( @Valid @RequestBody LoginUsuarioDTO loginDTO ) {
        usuarioService.validarLoginUfes( loginDTO );
        return "Login Ufes Validado";
    }
}
