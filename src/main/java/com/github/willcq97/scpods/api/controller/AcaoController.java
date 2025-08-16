package com.github.willcq97.scpods.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.willcq97.scpods.api.dto.input.SubmissaoInputDTO;
import com.github.willcq97.scpods.api.dto.response.AcaoResponseDTO;
import com.github.willcq97.scpods.api.dto.search.AcaoSearchDTO;
import com.github.willcq97.scpods.api.dto.search.AcaoSearchOptionsDTO;
import com.github.willcq97.scpods.api.mapper.AcaoMapper;
import com.github.willcq97.scpods.domain.service.AcaoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping( "/acoes" )
@Tag( name = "Ações/Projetos" )
@AllArgsConstructor
public class AcaoController {

    private final AcaoService acaoService;

    private final AcaoMapper mapper;

    @GetMapping( "/{id}" )
    public ResponseEntity<AcaoResponseDTO> findById( @PathVariable Long id ) {
        return ResponseEntity.ok().body( mapper.mapToResponse( acaoService.findAcaoById( id ) ) );
    }

    @PostMapping( "/search" )
    public ResponseEntity<List<AcaoSearchDTO>> search( @RequestBody AcaoSearchOptionsDTO options ) {
        return ResponseEntity.ok( acaoService.search( options, true ) );
    }

    @PostMapping( "/submeter" )
    public ResponseEntity<Void> salvarSubmissao( @Valid @RequestBody SubmissaoInputDTO submissao ) {
        acaoService.inserirSubmissao( mapper.mapToEntity( submissao ) );
        return ResponseEntity.status( HttpStatus.CREATED ).build();
    }

}
