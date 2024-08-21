package br.ufes.willcq.scpods.api.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import br.ufes.willcq.scpods.api.dto.AcaoSearchDTO;
import br.ufes.willcq.scpods.api.dto.AcaoSearchOptions;
import br.ufes.willcq.scpods.api.dto.response.SubmissaoResponseDTO;
import br.ufes.willcq.scpods.domain.model.Acao;
import br.ufes.willcq.scpods.domain.service.AcaoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( "/submissoes" )
@Tag( name = "Submissões" )
public class SubmissaoController {

    @Autowired
    private AcaoService acaoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping( "/{id}" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<SubmissaoResponseDTO> findById( @PathVariable Long id ) {
        return ResponseEntity.ok().body( this.mapToSubmissaoResponseDTO( acaoService.findSubmissaoById( id ) ) );
    }

    @PostMapping( "/search" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<List<AcaoSearchDTO>> search( @RequestBody AcaoSearchOptions options ) {
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
