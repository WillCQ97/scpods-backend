package com.github.willcq97.scpods.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.willcq97.scpods.api.dto.response.UnidadeInfoDTO;
import com.github.willcq97.scpods.api.dto.response.UnidadeResponseDTO;
import com.github.willcq97.scpods.api.dto.select.SelectModel;
import com.github.willcq97.scpods.api.dto.select.SelectModelString;
import com.github.willcq97.scpods.api.mapper.UnidadeMapper;
import com.github.willcq97.scpods.domain.service.UnidadeService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping( "/unidades" )
@Tag( name = "Campus, Locais e Unidades da Universidade" )
@AllArgsConstructor
public class UnidadeController {

    private final UnidadeService service;
    private final UnidadeMapper mapper;

    @GetMapping( "/" )
    @ResponseStatus( HttpStatus.OK )
    public List<UnidadeResponseDTO> listarUnidades( @RequestParam( required = false ) String campus ) {
        return mapper.mapAllToResponse( service.listarUnidades( campus ) );
    }

    @GetMapping( "/opcoes-campus" )
    @ResponseStatus( HttpStatus.OK )
    public List<SelectModelString> listarOpcoesCampus() {
        return service.listarOpcoesCampus();
    }

    @GetMapping( "/opcoes-unidade" )
    @ResponseStatus( HttpStatus.OK )
    public List<SelectModel<String>> listarOpcoesUnidades() {
        return service.listarOpcoesUnidades();
    }

    @GetMapping( "/info" )
    @ResponseStatus( HttpStatus.OK )
    public List<UnidadeInfoDTO> obterContabilizacaoCampus( @RequestParam( required = true ) String campus ) {
        return mapper.mapAllToUnidadeInfo( service.obterContabilizacaoPorCampus( campus ) );
    }

    @GetMapping( "/info/{codigo}" )
    @ResponseStatus( HttpStatus.OK )
    public UnidadeInfoDTO obterContabilizacaoUnidade( @PathVariable String codigo ) {
        return mapper.mapToUnidadeInfo( service.obterContabilizacaoParaUnidade( codigo ) );
    }

}
