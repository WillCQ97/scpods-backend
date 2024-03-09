package br.ufes.willcq.scpods.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.response.ObjetivoResponseDTO;
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
    public List<ObjetivoResponseDTO> listar() {
        return this.mapAllToObjetivoResponseDTO( repository.findAll() );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<ObjetivoResponseDTO> buscar( @PathVariable Long id ) {

        var optObjetivo = repository.findById( id );

        if( optObjetivo.isPresent() ) {
            return ResponseEntity.ok().body( modelMapper.map( optObjetivo.get(), ObjetivoResponseDTO.class ) );
        }
        return ResponseEntity.notFound().build();
    }

    private List<ObjetivoResponseDTO> mapAllToObjetivoResponseDTO( List<Objetivo> objetivos ) {
        return objetivos.stream().map( obj -> modelMapper.map( obj, ObjetivoResponseDTO.class ) ).collect( Collectors.toList() );
    }

}
