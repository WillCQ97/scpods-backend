package br.ufes.willcq.scpods.api.controller;

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
    public Iterable<Acao> listar() {
        return repository.findAll();
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<AcaoDTO> buscar( @PathVariable Long id ) {

        var optAcao = repository.findById( id );

        if( optAcao.isPresent() ) {
            return ResponseEntity.ok().body( modelMapper.map( optAcao.get(), AcaoDTO.class ) );
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Acao> salvar( @RequestBody AcaoInputDTO acao ) {
        return ResponseEntity.status( HttpStatus.CREATED ).body( service.salvar( modelMapper.map( acao, Acao.class ) ) );
    }
}
