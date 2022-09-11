package br.ufes.willcq.scpods.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.model.ObjetivoODS;
import br.ufes.willcq.scpods.service.ObjetivoODSService;

@RestController
@RequestMapping( "/api/v0/objetivos" )
public class ObjetivoODSController {

    @Autowired
    private ObjetivoODSService objetivoODSService;

    @GetMapping
    public Iterable<ObjetivoODS> listar() {
        return objetivoODSService.listar();
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<ObjetivoODS> buscar( @PathVariable Long id ) {
        return ResponseEntity.ok().body( objetivoODSService.buscar( id ) );
    }
}
