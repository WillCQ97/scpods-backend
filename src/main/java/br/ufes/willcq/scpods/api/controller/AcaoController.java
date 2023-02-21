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
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.AcaoDTO;
import br.ufes.willcq.scpods.api.dto.AcaoInputDTO;
import br.ufes.willcq.scpods.domain.model.Acao;
import br.ufes.willcq.scpods.domain.repository.AcaoRepository;
import br.ufes.willcq.scpods.domain.service.CadastroAcaoService;

@RestController
@RequestMapping( "/api/v0/acoes" )
public class AcaoController {

    @Autowired
    private CadastroAcaoService service;

    @Autowired
    private AcaoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Iterable<AcaoDTO> listar() {
        return this.mapAllToAcaoDTO( repository.findAll() );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<AcaoDTO> buscar( @PathVariable Long idAcao ) {

        var optAcao = repository.findById( idAcao );

        if( optAcao.isPresent() ) {
            return ResponseEntity.ok().body( this.mapToAcaoDTO( optAcao.get() ) );
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Acao> salvar( @RequestBody AcaoInputDTO inputAcao ) {
        return ResponseEntity.status( HttpStatus.CREATED ).body( service.salvar( this.mapToAcao( inputAcao ) ) );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Acao> atualizar( @PathVariable Long id, @RequestBody AcaoInputDTO inputAcao ) {

        if( !repository.existsById( id ) ) {
            return ResponseEntity.notFound().build();
        }

        var acao = this.mapToAcao( inputAcao );
        acao.setId( id );
        return ResponseEntity.ok( service.atualizar( acao ) );

    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Void> deletar( @PathVariable Long idAcao ) {

        if( !repository.existsById( idAcao ) ) {
            return ResponseEntity.notFound().build();
        }

        service.excluir( idAcao );
        return ResponseEntity.noContent().build();

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
