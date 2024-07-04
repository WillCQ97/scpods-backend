package br.ufes.willcq.scpods.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.response.UnidadeInfoDTO;
import br.ufes.willcq.scpods.api.dto.response.UnidadeResponseDTO;
import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;
import br.ufes.willcq.scpods.domain.service.UnidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( "/unidades" )
@Tag( name = "Unidades da Universidade" )
public class UnidadeController {

    @Autowired
    private UnidadeService service;

    @GetMapping( "/opcoes-campus" )
    public ResponseEntity<List<CampusEnum>> listarOpcoesCampus() {
        return ResponseEntity.ok().body( service.listarOpcoesCampus() );
    }

    @GetMapping( "" )
    public ResponseEntity<List<UnidadeResponseDTO>> listarUnidades( @RequestParam( required = false ) String campus ) {
        return ResponseEntity.ok().body( service.listarUnidades( campus ) );
    }

    @GetMapping( "/info" )
    public ResponseEntity<List<UnidadeInfoDTO>> obterContabilizacaoCampus( @RequestParam( required = true ) String campus ) {
        return ResponseEntity.ok().body( service.obterContabilizacaoPorCampus( campus ) );
    }

    @GetMapping( "/{codigo}/info" )
    public ResponseEntity<UnidadeInfoDTO> obterContabilizacaoUnidade( @PathVariable String codigo ) {
        return ResponseEntity.ok().body( service.obterContabilizacaoParaUnidade( codigo ) );
    }

}
