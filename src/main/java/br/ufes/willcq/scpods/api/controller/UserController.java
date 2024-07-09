package br.ufes.willcq.scpods.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( "/usuarios" )
@Tag( name = "Usuários" )
public class UserController {

    @GetMapping( "/validar-admin" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<Void> validarAdmin() {
        // TODO: maneira mais simples para validar as credenciais usando BasicAuth
        return ResponseEntity.ok().build();
    }
}
