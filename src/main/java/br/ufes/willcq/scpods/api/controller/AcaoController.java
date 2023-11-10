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

import br.ufes.willcq.scpods.api.dto.input.AcaoInputDTO;
import br.ufes.willcq.scpods.api.dto.response.AcaoResponseDTO;
import br.ufes.willcq.scpods.domain.model.Acao;
import br.ufes.willcq.scpods.domain.repository.AcaoRepository;
import br.ufes.willcq.scpods.domain.service.CadastroAcaoService;

@RestController
@RequestMapping( "/api/acoes" )
public class AcaoController {

    @Autowired
    private AcaoRepository acaoRepository;

    @Autowired
    private CadastroAcaoService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Iterable<AcaoResponseDTO> listarAcoes( @RequestParam( required = true ) Boolean aceito, @RequestParam( required = false ) String campus ) {

        if( campus == null ) {
            return this.mapAllToAcaoDTO( service.listar( aceito ) );
        } else {
            return this.mapAllToAcaoDTO( service.listarPorCampus( aceito, campus ) );
        }

    }

    @GetMapping( "/{id}" )
    public ResponseEntity<AcaoResponseDTO> buscarPorId( @PathVariable Long id ) {

        var optAcao = service.buscarPeloId( id );

        if( optAcao.isPresent() ) {
            return ResponseEntity.ok().body( this.mapToAcaoDTO( optAcao.get() ) );
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AcaoResponseDTO> salvar( @RequestBody AcaoInputDTO inputAcao ) {
        service.salvar( this.mapToAcao( inputAcao ) );
        return ResponseEntity.status( HttpStatus.CREATED ).build();
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<AcaoResponseDTO> atualizar( @PathVariable Long id, @RequestBody AcaoInputDTO inputAcao ) {

        if( !acaoRepository.existsById( id ) ) {
            return ResponseEntity.notFound().build();
        }

        var acao = this.mapToAcao( inputAcao );
        acao.setId( id );
        service.atualizar( acao );
        return ResponseEntity.ok().build();

    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Void> deletar( @PathVariable Long id ) {

        if( !acaoRepository.existsById( id ) ) {
            return ResponseEntity.notFound().build();
        }

        service.excluir( id );
        return ResponseEntity.noContent().build();

    }

    private AcaoResponseDTO mapToAcaoDTO( Acao acao ) {
        return modelMapper.map( acao, AcaoResponseDTO.class );
    }

    private Acao mapToAcao( AcaoInputDTO dto ) {
        return modelMapper.map( dto, Acao.class );
    }

    private Iterable<AcaoResponseDTO> mapAllToAcaoDTO( Iterable<Acao> acoes ) {
        var spliterator = acoes.spliterator();
        return StreamSupport.stream( spliterator, false ).map( this::mapToAcaoDTO ).collect( Collectors.toList() );
    }
}
