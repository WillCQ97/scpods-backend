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
        return this.mapAll( repository.findAll() );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<AcaoDTO> buscar( @PathVariable Long idAcao ) {

        var optAcao = repository.findById( idAcao );

        if( optAcao.isPresent() ) {
            return ResponseEntity.ok().body( modelMapper.map( optAcao.get(), AcaoDTO.class ) );
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Acao> salvar( @RequestBody AcaoInputDTO inputAcao ) {
        return ResponseEntity.status( HttpStatus.CREATED ).body( service.salvar( modelMapper.map( inputAcao, Acao.class ) ) );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Acao> atualizar( @PathVariable Long id, @RequestBody AcaoInputDTO inputAcao ) {

        if( !repository.existsById( id ) ) {
            return ResponseEntity.notFound().build();
        }

        var acao = modelMapper.map( inputAcao, Acao.class );
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

    private Iterable<AcaoDTO> mapAll( Iterable<Acao> acoes ) {
        var spliterator = acoes.spliterator();
        return StreamSupport.stream( spliterator, false ).map( acao -> modelMapper.map( acao, AcaoDTO.class ) ).collect( Collectors.toList() );
    }
}
