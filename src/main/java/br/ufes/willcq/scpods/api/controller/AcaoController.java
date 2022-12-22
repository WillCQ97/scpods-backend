package br.ufes.willcq.scpods.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.model.Acao;
import br.ufes.willcq.scpods.service.AcaoService;

@RestController
@RequestMapping( "/api/v0/acoes" )
public class AcaoController {

    @Autowired
    private AcaoService service;

    @GetMapping
    public Iterable<Acao> listar() {
        return service.listar();
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Acao> buscar( @PathVariable Long id ) {
        return ResponseEntity.ok().body( service.buscar( id ) );
    }
}
