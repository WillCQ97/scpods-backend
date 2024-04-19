package br.ufes.willcq.scpods.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.response.CampusInfoDTO;
import br.ufes.willcq.scpods.api.dto.response.CampusResponseDTO;
import br.ufes.willcq.scpods.api.dto.response.UnidadeResponseDTO;
import br.ufes.willcq.scpods.domain.service.UnidadeService;

@RestController
@RequestMapping( "/unidade" )
public class UnidadeController {

    @Autowired
    private UnidadeService service;

    @GetMapping( "" )
    public ResponseEntity<List<UnidadeResponseDTO>> getUnidades( @RequestParam( required = false ) String campus ) {
        return ResponseEntity.ok().body( service.obterUnidades( campus ) );
    }

    @GetMapping( "/info" )
    public ResponseEntity<CampusInfoDTO> obterContabilizacaoAcoes( @RequestParam( required = true ) String campus ) {
        var campusInfo = service.obterContabilizacaoAcoes( campus );
        return ResponseEntity.ok().body( campusInfo );
    }

    @GetMapping( "/locais" )
    public ResponseEntity<CampusResponseDTO> obterLocaisPorUnidade( @RequestParam( required = true ) String campus ) {
        var campusResponse = service.obterLocaisPorUnidade( campus );
        return ResponseEntity.ok().body( campusResponse );
    }

}
