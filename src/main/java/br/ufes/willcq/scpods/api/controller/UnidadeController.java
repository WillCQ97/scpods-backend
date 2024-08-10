package br.ufes.willcq.scpods.api.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.response.UnidadeInfoDTO;
import br.ufes.willcq.scpods.api.dto.response.UnidadeResponseDTO;
import br.ufes.willcq.scpods.api.dto.select.SelectModel;
import br.ufes.willcq.scpods.api.dto.select.SelectModelString;
import br.ufes.willcq.scpods.domain.model.Unidade;
import br.ufes.willcq.scpods.domain.service.UnidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( "/unidades" )
@Tag( name = "Campus, Locais e Unidades da Universidade" )
public class UnidadeController {

    @Autowired
    private UnidadeService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UnidadeResponseDTO>> listarUnidades( @RequestParam( required = false ) String campus ) {
        return ResponseEntity.ok().body( service.listarUnidades( campus ).stream().map( this::mapUnidadeToUnidadeResponseDTO ).toList() );
    }

    @GetMapping( "/opcoes-campus" )
    public ResponseEntity<List<SelectModelString>> listarOpcoesCampus() {
        return ResponseEntity.ok().body( service.listarOpcoesCampus() );
    }

    @GetMapping( "/opcoes-unidade" )
    public ResponseEntity<List<SelectModel<String>>> listarOpcoesUnidades() {
        return ResponseEntity.ok().body( service.listarOpcoesUnidades() );
    }

    @GetMapping( "/info" )
    public ResponseEntity<List<UnidadeInfoDTO>> obterContabilizacaoCampus( @RequestParam( required = true ) String campus ) {
        return ResponseEntity.ok().body( service.obterContabilizacaoPorCampus( campus ).stream().map( this::mapUnidadeToUnidadeInfo ).toList() );
    }

    @GetMapping( "/info/{codigo}" )
    public ResponseEntity<UnidadeInfoDTO> obterContabilizacaoUnidade( @PathVariable String codigo ) {
        return ResponseEntity.ok().body( this.mapUnidadeToUnidadeInfo( service.obterContabilizacaoParaUnidade( codigo ) ) );
    }

    private UnidadeInfoDTO mapUnidadeToUnidadeInfo( Unidade unidade ) {
        return modelMapper.map( unidade, UnidadeInfoDTO.class );
    }

    private UnidadeResponseDTO mapUnidadeToUnidadeResponseDTO( Unidade unidade ) {
        return modelMapper.map( unidade, UnidadeResponseDTO.class );
    }

}
