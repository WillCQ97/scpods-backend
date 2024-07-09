package br.ufes.willcq.scpods.api.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.response.MetaResponseDTO;
import br.ufes.willcq.scpods.api.dto.response.ObjetivoResponseDTO;
import br.ufes.willcq.scpods.domain.model.Objetivo;
import br.ufes.willcq.scpods.domain.repository.MetaRepository;
import br.ufes.willcq.scpods.domain.repository.ObjetivoRepository;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( "/objetivos" )
@Tag( name = "Objetivos e Metas de Desenvolvimento Sustentável" )
public class ObjetivoController {

    @Autowired
    private ObjetivoRepository objetivoRepository;

    @Autowired
    private MetaRepository metaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ObjetivoResponseDTO> listar() {
        return this.mapAllToObjetivoResponseDTO( objetivoRepository.findAll() );
    }

    @GetMapping( "/{codigo}" )
    public ResponseEntity<ObjetivoResponseDTO> buscarObjetivo( @PathVariable String codigo ) {

        var optObjetivo = objetivoRepository.findByCodigo( codigo );

        if( optObjetivo.isPresent() ) {
            return ResponseEntity.ok().body( modelMapper.map( optObjetivo.get(), ObjetivoResponseDTO.class ) );
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping( "/metas/{codigo}" )
    public ResponseEntity<MetaResponseDTO> buscarMeta( @PathVariable String codigo ) {

        var optMeta = metaRepository.findByCodigo( codigo );

        if( optMeta.isPresent() ) {
            return ResponseEntity.ok().body( modelMapper.map( optMeta.get(), MetaResponseDTO.class ) );
        }
        return ResponseEntity.notFound().build();
    }

    private List<ObjetivoResponseDTO> mapAllToObjetivoResponseDTO( List<Objetivo> objetivos ) {
        return objetivos.stream().map( obj -> modelMapper.map( obj, ObjetivoResponseDTO.class ) ).toList();
    }

}
