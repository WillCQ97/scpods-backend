package br.ufes.willcq.scpods.api.controller;

import java.util.List;

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
    private AcaoService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<AcaoResponseDTO> listarAcoes( @RequestParam( required = true ) Boolean aceito, @RequestParam( required = false ) String campus ) {

        if( campus == null ) {
            return this.mapAllToAcaoResponseDTO( service.listar( aceito ) );
        } else {
            return this.mapAllToAcaoResponseDTO( service.listarPorCampus( aceito, campus ) );
        }

    }

    @GetMapping( "/{id}" )
    public ResponseEntity<AcaoResponseDTO> buscarPorId( @PathVariable Long id ) {

        var optAcao = service.buscarPeloId( id );

        if( optAcao.isPresent() ) {
            return ResponseEntity.ok().body( this.mapToAcaoResponseDTO( optAcao.get() ) );
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

    @PostMapping( "/aceitar" )
    public ResponseEntity<Void> aceitarSubmissao( @RequestBody SubmissaoInputDTO inputSubmissao ) {

        if( !acaoRepository.existsById( inputSubmissao.getId() ) ) {
            return ResponseEntity.notFound().build();
        }

        service.aceitarSubmissao( inputSubmissao.getId() );
        return ResponseEntity.ok().build();
    }

    private Acao mapToAcao( AcaoInputDTO dto ) {
        return modelMapper.map( dto, Acao.class );
    }

    private AcaoResponseDTO mapToAcaoResponseDTO( Acao acao ) {
        return modelMapper.map( acao, AcaoResponseDTO.class );
    }

    private List<AcaoResponseDTO> mapAllToAcaoResponseDTO( List<Acao> acoes ) {
        return acoes.stream().map( this::mapToAcaoResponseDTO ).toList();
    }
}
