package br.ufes.willcq.scpods.api.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.AcaoGridDTO;
import br.ufes.willcq.scpods.api.dto.AcaoGridOptions;
import br.ufes.willcq.scpods.api.dto.input.SubmissaoInputDTO;
import br.ufes.willcq.scpods.api.dto.response.SubmissaoResponseDTO;
import br.ufes.willcq.scpods.domain.model.Acao;
import br.ufes.willcq.scpods.domain.service.AcaoService;

@RestController
@RequestMapping( "/submissoes" )
public class SubmissaoController {

    @Autowired
    private AcaoService acaoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Void> salvar( @RequestBody SubmissaoInputDTO submissao ) {
        acaoService.inserirSubmissao( this.mapToAcao( submissao ) );
        return ResponseEntity.status( HttpStatus.CREATED ).build();
    }

    @GetMapping( "/{id}" )
    // todo: ser acessado pelo admin apenas
    public ResponseEntity<SubmissaoResponseDTO> findById( @PathVariable Long id ) {

        var optAcao = acaoService.findSubmissaoById( id );
        if( optAcao.isPresent() ) {
            return ResponseEntity.ok().body( this.mapToSubmissaoResponseDTO( optAcao.get() ) );
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping( "/search" )
    // todo: ser acessado pelo admin apenas
    public ResponseEntity<List<AcaoGridDTO>> search( @RequestBody AcaoGridOptions acaoGridOptions ) {
        return ResponseEntity.ok( null );
    }

    @DeleteMapping( "/{id}" )
    // todo: chamado apenas pelo admin apenas
    public ResponseEntity<Void> rejeitar( @PathVariable Long id ) {

        if( !acaoService.existsById( id ) ) {
            return ResponseEntity.notFound().build();
        }

        acaoService.excluirSubmissao( id );
        return ResponseEntity.noContent().build();

    }

    @PatchMapping( "/aceitar" )
    // todo: chamado apenas pelo admin apenas
    public ResponseEntity<Void> aceitar( @RequestParam( required = true ) Long id ) {

        if( !acaoService.existsById( id ) ) {
            return ResponseEntity.notFound().build();
        }

        acaoService.aceitarSubmissao( id );
        return ResponseEntity.ok().build();
    }

    private Acao mapToAcao( SubmissaoInputDTO dto ) {
        return modelMapper.map( dto, Acao.class );
    }

    private SubmissaoResponseDTO mapToSubmissaoResponseDTO( Acao acao ) {
        return modelMapper.map( acao, SubmissaoResponseDTO.class );
    }
}
