package br.ufes.willcq.scpods.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.model.Coordenador;
import br.ufes.willcq.scpods.service.CoordenadorService;

/**
 * @deprecated (when: 07-12-2022, why: Não será utilizado diretamente)
 */
@Deprecated
@RestController
@RequestMapping( "/api/v0/coordenadores" )
public class CoordenadorController {

    @Autowired
    private CoordenadorService service;

    @GetMapping
    public Coordenador listar() {

        return service.buscar( 1L );

    }

}
