package br.ufes.willcq.scpods.api.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.response.MetaResponseDTO;
import br.ufes.willcq.scpods.api.dto.response.ObjetivoResponseDTO;
import br.ufes.willcq.scpods.domain.model.Objetivo;
import br.ufes.willcq.scpods.domain.service.ObjetivoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( "/objetivos" )
@Tag( name = "Objetivos e Metas de Desenvolvimento Sustentável" )
public class ObjetivoController {

    @Autowired
    private ObjetivoService objetivoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ObjetivoResponseDTO> listar() {
        return this.mapAllToObjetivoResponseDTO( objetivoService.listar() );
    }

    @GetMapping( "/{codigo}" )
    public ResponseEntity<ObjetivoResponseDTO> buscarObjetivo( @PathVariable String codigo ) {

        var optObjetivo = objetivoService.findObjetivoByCodigo( codigo );

        if( optObjetivo.isPresent() ) {
            return ResponseEntity.ok().body( modelMapper.map( optObjetivo.get(), ObjetivoResponseDTO.class ) );
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping( "/meta/{codigo}" )
    public ResponseEntity<MetaResponseDTO> buscarMeta( @PathVariable String codigo ) {

        var optMeta = objetivoService.findMetaByCodigo( codigo );

        if( optMeta.isPresent() ) {
            return ResponseEntity.ok().body( modelMapper.map( optMeta.get(), MetaResponseDTO.class ) );
        }
        return ResponseEntity.notFound().build();
    }

    private List<ObjetivoResponseDTO> mapAllToObjetivoResponseDTO( List<Objetivo> objetivos ) {
        return objetivos.stream().map( obj -> modelMapper.map( obj, ObjetivoResponseDTO.class ) ).toList();
    }

}
