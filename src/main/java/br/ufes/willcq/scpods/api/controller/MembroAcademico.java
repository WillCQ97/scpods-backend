package br.ufes.willcq.scpods.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.input.LoginUsuarioDTO;
import br.ufes.willcq.scpods.domain.service.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping( "/membro-academico" )
@Tag( name = "Membro Comunidade Acadêmica" )
public class MembroAcademico {

    @Autowired
    private LoginService loginService;

    @PostMapping( "/validar-login" )
    public ResponseEntity<?> validarLogin( @RequestBody @Valid LoginUsuarioDTO loginDTO ) {
        loginService.validarLogin( loginDTO );
        return ResponseEntity.ok().build();
    }

}
