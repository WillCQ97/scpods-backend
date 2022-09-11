package br.ufes.willcq.scpods.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.model.MetaODS;
import br.ufes.willcq.scpods.service.MetaODSService;

@RestController
@RequestMapping( "/api/v0/metas" )
public class MetaODSController {

    @Autowired
    private MetaODSService metaODSService;

    @GetMapping
    public Iterable<MetaODS> listar() {
        return metaODSService.listar();
    }

}
