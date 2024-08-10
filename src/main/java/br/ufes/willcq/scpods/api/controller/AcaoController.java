package br.ufes.willcq.scpods.api.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.AcaoSearchDTO;
import br.ufes.willcq.scpods.api.dto.AcaoSearchOptions;
import br.ufes.willcq.scpods.api.dto.input.SubmissaoInputDTO;
import br.ufes.willcq.scpods.api.dto.response.AcaoResponseDTO;
import br.ufes.willcq.scpods.domain.model.Acao;
import br.ufes.willcq.scpods.domain.service.AcaoService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( "/acoes" )
@Tag( name = "Ações/Projetos" )
public class AcaoController {

    @Autowired
    private AcaoService acaoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping( "/{id}" )
    public ResponseEntity<AcaoResponseDTO> findById( @PathVariable Long id ) {

        var optAcao = acaoService.findAcaoById( id );
        if( optAcao.isPresent() ) {
            return ResponseEntity.ok().body( this.mapToAcaoResponseDTO( optAcao.get() ) );
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping( "/search" )
    public ResponseEntity<List<AcaoSearchDTO>> search( @RequestBody AcaoSearchOptions options ) {
        return ResponseEntity.ok( acaoService.searchAcoes( options ) );
    }

    @PostMapping( "/submeter" )
    // TODO: Adicionar alguma validação
    public ResponseEntity<Void> salvarSubmissao( @RequestBody SubmissaoInputDTO submissao ) {
        acaoService.inserirSubmissao( this.mapToAcao( submissao ) );
        return ResponseEntity.status( HttpStatus.CREATED ).build();
    }

    private Acao mapToAcao( SubmissaoInputDTO dto ) {
        return modelMapper.map( dto, Acao.class );
    }

    private AcaoResponseDTO mapToAcaoResponseDTO( Acao acao ) {
        return modelMapper.map( acao, AcaoResponseDTO.class );
    }

}
