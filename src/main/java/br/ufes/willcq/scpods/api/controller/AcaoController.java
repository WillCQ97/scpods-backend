package br.ufes.willcq.scpods.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public Iterable<Acao> listar() {
        return repository.findAll();
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<Acao> buscar( @PathVariable Long id ) {

        var optAcao = repository.findById( id );

        if( optAcao.isPresent() ) {
            return ResponseEntity.ok().body( optAcao.get() );
        }
        return ResponseEntity.notFound().build();
    }

}
