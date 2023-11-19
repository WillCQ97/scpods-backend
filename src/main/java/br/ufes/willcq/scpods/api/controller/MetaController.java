package br.ufes.willcq.scpods.api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.MetaResponseDTO;
import br.ufes.willcq.scpods.domain.repository.MetaRepository;

@RestController
@RequestMapping( "/api/metas" )
public class MetaController {

    @Autowired
    private MetaRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping( "/{id}" )
    public ResponseEntity<MetaResponseDTO> buscar( @PathVariable String id ) {

        var optMeta = repository.findById( id );

        if( optMeta.isPresent() ) {
            return ResponseEntity.ok().body( modelMapper.map( optMeta.get(), MetaResponseDTO.class ) );
        }
        return ResponseEntity.notFound().build();
    }

}
