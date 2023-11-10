package br.ufes.willcq.scpods.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.response.CampusInfoDTO;
import br.ufes.willcq.scpods.domain.service.InfoService;

@RestController
@RequestMapping( "/api/info" )
public class InfoController {

    @Autowired
    private InfoService service;

    @GetMapping( "/{campus}" )
    public ResponseEntity<CampusInfoDTO> obterContabilizacaoAcoes( @PathVariable String campus ) {
        // TODO: CHAMA A SERVICE E FAZ ALGO
        return ResponseEntity.badRequest().build();
    }

}
