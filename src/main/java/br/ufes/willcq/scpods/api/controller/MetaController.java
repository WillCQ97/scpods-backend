package br.ufes.willcq.scpods.api.controller;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.MetaDTO;
import br.ufes.willcq.scpods.service.MetaService;

@RestController
@RequestMapping( "/api/v0/metas" )
public class MetaController {

    @Autowired
    private MetaService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Iterable<MetaDTO> listar() {
        var metas = service.listar();
        return StreamSupport.stream( metas.spliterator(), false ).map( meta -> modelMapper.map( meta, MetaDTO.class ) ).collect( Collectors.toList() );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<MetaDTO> buscar( @PathVariable String id ) {
        return ResponseEntity.ok().body( modelMapper.map( service.buscar( id ), MetaDTO.class ) );
    }

}
