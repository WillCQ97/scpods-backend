package com.github.willcq97.scpods.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @GetMapping( "/" )
    @ResponseStatus( HttpStatus.OK )
    public List<ObjetivoResponseDTO> listar() {
        return mapper.mapAllToResponse( objetivoService.listar() );
    }

    @GetMapping( "/{codigo}" )
    @ResponseStatus( HttpStatus.OK )
    public ObjetivoResponseDTO buscarObjetivo( @PathVariable String codigo ) {
        return mapper.mapToResponse( objetivoService.findObjetivoByCodigo( codigo ) );
    }

    @GetMapping( "/meta/{codigo}" )
    @ResponseStatus( HttpStatus.OK )
    public MetaResponseDTO buscarMeta( @PathVariable String codigo ) {
        return mapper.mapToResponse( objetivoService.findMetaByCodigo( codigo ) );
    }

}
