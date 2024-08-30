package br.ufes.willcq.scpods.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.willcq.scpods.api.dto.input.LoginUsuarioDTO;

@Service
public class UsuarioService {

    @Autowired
    private LoginUfesService loginUfesService;

    public void validarLoginUfes( LoginUsuarioDTO usuario ) {
        loginUfesService.validarLoginUfes( usuario );
    }

}
