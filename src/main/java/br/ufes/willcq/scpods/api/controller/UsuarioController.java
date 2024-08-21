package br.ufes.willcq.scpods.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.input.LoginUsuarioDTO;
import br.ufes.willcq.scpods.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping( "/usuarios" )
@Tag( name = "Usuários" )
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping( "/validar-admin" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<?> validarAdmin() {
        // TODO: maneira mais simples para validar as credenciais usando BasicAuth
        return ResponseEntity.ok().build();
    }

    @PostMapping( "/validar-login" )
    public ResponseEntity<?> validarLoginUfes( @Valid @RequestBody LoginUsuarioDTO loginDTO ) {
        usuarioService.validarLoginUfes( loginDTO );
        return ResponseEntity.ok().build();
    }
}
