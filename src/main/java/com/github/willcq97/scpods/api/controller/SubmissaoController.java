package com.github.willcq97.scpods.api.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.willcq97.scpods.api.dto.response.SubmissaoResponseDTO;
import com.github.willcq97.scpods.api.dto.search.AcaoSearchDTO;
import com.github.willcq97.scpods.api.dto.search.AcaoSearchOptionsDTO;
import com.github.willcq97.scpods.domain.model.entity.Acao;
import com.github.willcq97.scpods.domain.service.AcaoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping( "/submissoes" )
@Tag( name = "Submissões" )
@AllArgsConstructor
public class SubmissaoController {

    private final AcaoService acaoService;

    private final ModelMapper modelMapper;

    @GetMapping( "/{id}" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<SubmissaoResponseDTO> findById( @PathVariable Long id ) {
        return ResponseEntity.ok().body( this.mapToSubmissaoResponseDTO( acaoService.findSubmissaoById( id ) ) );
    }

    @PostMapping( "/search" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<List<AcaoSearchDTO>> search( @RequestBody AcaoSearchOptionsDTO options ) {
        return ResponseEntity.ok( acaoService.search( options, false ) );
    }

    @DeleteMapping( "/rejeitar" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<Void> rejeitar( @RequestParam( required = true ) Long id ) {
        acaoService.excluirSubmissao( id );
        return ResponseEntity.noContent().build();
    }

    @PatchMapping( "/aceitar" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<Void> aceitar( @RequestParam( required = true ) Long id ) {
        acaoService.aceitarSubmissao( id );
        return ResponseEntity.ok().build();
    }

    private SubmissaoResponseDTO mapToSubmissaoResponseDTO( Acao acao ) {
        return modelMapper.map( acao, SubmissaoResponseDTO.class );
    }
}
