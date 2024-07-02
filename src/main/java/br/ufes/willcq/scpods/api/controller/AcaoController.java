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
import br.ufes.willcq.scpods.api.dto.response.AcaoResponseDTO;
import br.ufes.willcq.scpods.domain.model.Acao;
import br.ufes.willcq.scpods.domain.repository.AcaoRepository;
import br.ufes.willcq.scpods.domain.service.AcaoService;

@RestController
@RequestMapping( "/acoes" )
public class AcaoController {

    @Autowired
    private AcaoRepository acaoRepository;

    @Autowired
    private AcaoService acaoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping( "/{id}" )
    public ResponseEntity<AcaoResponseDTO> findById( @PathVariable Long id ) {

        var optAcao = acaoService.findById( id );
        if( optAcao.isPresent() ) {
            return ResponseEntity.ok().body( this.mapToAcaoResponseDTO( optAcao.get() ) );
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping( "/search" )
    public ResponseEntity<List<AcaoGridDTO>> searchAcoes( @RequestBody AcaoGridOptions acaoGridOptions ) {
        return ResponseEntity.ok( acaoService.searchAcoes( acaoGridOptions ) );
    }

    @PostMapping( "/nova-submissao" )
    public ResponseEntity<Void> salvarSubmissao( @RequestBody SubmissaoInputDTO inputAcao ) {
        acaoService.inserirSubmissao( this.mapToAcao( inputAcao ) );
        return ResponseEntity.status( HttpStatus.CREATED ).build();
    }

    // TODO: Criar um findById para a submissão que retorna o e-mail do coordenador
    @PostMapping( "/search-submissoes" )
    // todo: ser acessado pelo admin apenas
    public ResponseEntity<List<AcaoGridDTO>> searchSubmissoes( @RequestBody AcaoGridOptions acaoGridOptions ) {
        return ResponseEntity.ok( null );
    }

    @DeleteMapping( "/{id}" )
    // todo: chamado apenas pelo admin
    public ResponseEntity<Void> rejeitarSubmissao( @PathVariable Long id ) {

        if( !acaoRepository.existsById( id ) ) {
            return ResponseEntity.notFound().build();
        }

        acaoService.excluirSubmissao( id );
        return ResponseEntity.noContent().build();

    }

    @PatchMapping( "/aceitar" )
    // todo: chamado apenas pelo admin
    public ResponseEntity<Void> aceitarSubmissao( @RequestParam( required = true ) Long id ) {

        if( !acaoRepository.existsById( id ) ) {
            return ResponseEntity.notFound().build();
        }

        acaoService.aceitarSubmissao( id );
        return ResponseEntity.ok().build();
    }

    private Acao mapToAcao( SubmissaoInputDTO dto ) {
        return modelMapper.map( dto, Acao.class );
    }

    private AcaoResponseDTO mapToAcaoResponseDTO( Acao acao ) {
        return modelMapper.map( acao, AcaoResponseDTO.class );
    }

}
