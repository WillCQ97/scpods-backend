package br.ufes.willcq.scpods.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.response.CampusInfoDTO;
import br.ufes.willcq.scpods.domain.service.CampusService;

@RestController
@RequestMapping( "/campus" )
public class CampusController {

    @Autowired
    private CampusService service;

    @GetMapping( "/info" )
    public ResponseEntity<CampusInfoDTO> obterContabilizacaoAcoes( @RequestParam( required = true ) String nome ) {
        var campusInfo = service.obterContabilizacaoAcoes( nome );
        return ResponseEntity.ok().body( campusInfo );
    }

}
