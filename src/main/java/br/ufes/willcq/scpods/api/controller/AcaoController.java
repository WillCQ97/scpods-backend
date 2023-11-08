package br.ufes.willcq.scpods.api.controller;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.AcaoDTO;
import br.ufes.willcq.scpods.api.dto.input.AcaoInputDTO;
import br.ufes.willcq.scpods.api.util.AcaoSearchCriteriaHandler;
import br.ufes.willcq.scpods.domain.model.Acao;
import br.ufes.willcq.scpods.domain.repository.AcaoRepository;
import br.ufes.willcq.scpods.domain.service.CadastroAcaoService;

@RestController
@RequestMapping( "/api/v0/acoes" )
public class AcaoController {

    // @Autowired
    // private CadastroAcaoService service;

    @Autowired
    private AcaoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AcaoSearchCriteriaHandler searchHandler;

    @GetMapping( "/{id}" )
    public ResponseEntity<AcaoDTO> buscarPorId( @PathVariable Long id ) {

        var optAcao = repository.findById( id );

        if( optAcao.isPresent() ) {
            return ResponseEntity.ok().body( this.mapToAcaoDTO( optAcao.get() ) );
        }
        return ResponseEntity.notFound().build();
    }

    // TODO: Trocar o retorno para AcaoDTO
    @PostMapping
    public ResponseEntity<Acao> salvar( @RequestBody AcaoInputDTO inputAcao ) {
        // service.salvar( this.mapToAcao( inputAcao )
        return ResponseEntity.status( HttpStatus.CREATED ).body( null );
    }

    // TODO: Trocar o retorno para AcaoDTO
    @PutMapping( "/{id}" )
    public ResponseEntity<Acao> atualizar( @PathVariable Long id, @RequestBody AcaoInputDTO inputAcao ) {

        if( !repository.existsById( id ) ) {
            return ResponseEntity.notFound().build();
        }

        var acao = this.mapToAcao( inputAcao );
        acao.setId( id );
        // service.atualizar( acao )
        return ResponseEntity.ok( null );

    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Void> deletar( @PathVariable Long id ) {

        if( !repository.existsById( id ) ) {
            return ResponseEntity.notFound().build();
        }

        // service.excluir( id );
        return ResponseEntity.noContent().build();

    }

    @GetMapping( )
    public Iterable<AcaoDTO> buscar( @RequestParam( value = "search", required = false ) String search ) {
        return this.mapAllToAcaoDTO( repository.searchAcao( this.searchHandler.handle( search ) ) );
    }

    private AcaoDTO mapToAcaoDTO( Acao acao ) {
        return modelMapper.map( acao, AcaoDTO.class );
    }

    private Acao mapToAcao( AcaoInputDTO dto ) {
        return modelMapper.map( dto, Acao.class );
    }

    private Iterable<AcaoDTO> mapAllToAcaoDTO( Iterable<Acao> acoes ) {
        var spliterator = acoes.spliterator();
        return StreamSupport.stream( spliterator, false ).map( this::mapToAcaoDTO ).collect( Collectors.toList() );
    }
}
