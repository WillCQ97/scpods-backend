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

import br.ufes.willcq.scpods.api.dto.ObjetivoResponseDTO;
import br.ufes.willcq.scpods.domain.model.Objetivo;
import br.ufes.willcq.scpods.domain.repository.ObjetivoRepository;

@RestController
@RequestMapping( "/objetivos" )
public class ObjetivoController {

    @Autowired
    private ObjetivoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Iterable<ObjetivoResponseDTO> listar() {
        return this.mapAll( repository.findAll() );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<ObjetivoResponseDTO> buscar( @PathVariable Long id ) {

        var optObjetivo = repository.findById( id );

        if( optObjetivo.isPresent() ) {
            return ResponseEntity.ok().body( modelMapper.map( optObjetivo.get(), ObjetivoResponseDTO.class ) );
        }
        return ResponseEntity.notFound().build();
    }

    private Iterable<ObjetivoResponseDTO> mapAll( Iterable<Objetivo> objetivos ) {

        var spliterator = objetivos.spliterator();
        return StreamSupport.stream( spliterator, false ).map( objetivo -> modelMapper.map( objetivo, ObjetivoResponseDTO.class ) ).collect( Collectors.toList() );
    }

}
