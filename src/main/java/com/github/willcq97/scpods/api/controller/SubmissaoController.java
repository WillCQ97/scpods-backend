package com.github.willcq97.scpods.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.willcq97.scpods.api.dto.response.SubmissaoResponseDTO;
import com.github.willcq97.scpods.api.dto.search.AcaoSearchDTO;
import com.github.willcq97.scpods.api.dto.search.AcaoSearchOptionsDTO;
import com.github.willcq97.scpods.domain.service.AcaoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping( "/submissoes" )
@Tag( name = "Submissões" )
@AllArgsConstructor
public class SubmissaoController {

    private final AcaoService acaoService;

    @GetMapping( "/{id}" )
    @ResponseStatus( HttpStatus.OK )
    @PreAuthorize( "hasRole('ADMIN')" )
    public SubmissaoResponseDTO findById( @PathVariable Long id ) {
        return SubmissaoResponseDTO.fromEntity( acaoService.findSubmissaoById( id ) );
    }

    @PostMapping( "/search" )
    @ResponseStatus( HttpStatus.OK )
    @PreAuthorize( "hasRole('ADMIN')" )
    public List<AcaoSearchDTO> search( @RequestBody AcaoSearchOptionsDTO options ) {
        return acaoService.search( options, false );
    }

    @DeleteMapping( "/rejeitar/{id}" )
    @ResponseStatus( HttpStatus.OK )
    @PreAuthorize( "hasRole('ADMIN')" )
    public void rejeitar( @PathVariable Long id ) {
        acaoService.excluirSubmissao( id );
    }

    @PatchMapping( "/aceitar/{id}" )
    @ResponseStatus( HttpStatus.OK )
    @PreAuthorize( "hasRole('ADMIN')" )
    public void aceitar( @PathVariable Long id ) {
        acaoService.aceitarSubmissao( id );
    }

}
