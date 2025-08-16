package com.github.willcq97.scpods.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.willcq97.scpods.api.dto.response.MetaResponseDTO;
import com.github.willcq97.scpods.api.dto.response.ObjetivoResponseDTO;
import com.github.willcq97.scpods.api.mapper.ObjetivoMapper;
import com.github.willcq97.scpods.domain.service.ObjetivoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping( "/objetivos" )
@Tag( name = "Objetivos e Metas de Desenvolvimento Sustentável" )
@AllArgsConstructor
public class ObjetivoController {

    private final ObjetivoService objetivoService;

    private final ObjetivoMapper mapper;

    @GetMapping
    public List<ObjetivoResponseDTO> listar() {
        return mapper.mapAllToResponse( objetivoService.listar() );
    }

    @GetMapping( "/{codigo}" )
    public ResponseEntity<ObjetivoResponseDTO> buscarObjetivo( @PathVariable String codigo ) {

        var optObjetivo = objetivoService.findObjetivoByCodigo( codigo );

        if( optObjetivo.isPresent() ) {
            return ResponseEntity.ok().body( mapper.mapToResponse( optObjetivo.get() ) );
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping( "/meta/{codigo}" )
    public ResponseEntity<MetaResponseDTO> buscarMeta( @PathVariable String codigo ) {

        var optMeta = objetivoService.findMetaByCodigo( codigo );

        if( optMeta.isPresent() ) {
            return ResponseEntity.ok().body( mapper.mapToResponse( optMeta.get() ) );
        }
        return ResponseEntity.notFound().build();
    }

}
