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

import br.ufes.willcq.scpods.api.dto.ObjetivoODSDTO;
import br.ufes.willcq.scpods.model.ObjetivoODS;
import br.ufes.willcq.scpods.service.ObjetivoODSService;

@RestController
@RequestMapping( "/api/v0/objetivos" )
public class ObjetivoODSController {

    @Autowired
    private ObjetivoODSService service;

    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public Iterable<ObjetivoODSDTO> listar() {

        return this.mapAll( service.listar() );

    }

    @GetMapping( "/{id}" )
    public ResponseEntity<ObjetivoODSDTO> buscar( @PathVariable Long id ) {

        var retorno = modelMapper.map( service.buscar( id ), ObjetivoODSDTO.class );
        return ResponseEntity.ok().body( retorno );

    }

    private Iterable<ObjetivoODSDTO> mapAll( Iterable<ObjetivoODS> objetivos ) {
        var spliterator = objetivos.spliterator();

        return StreamSupport.stream( spliterator, false ).map( objetivo -> modelMapper.map( objetivo, ObjetivoODSDTO.class ) ).collect( Collectors.toList() );
    }

}
