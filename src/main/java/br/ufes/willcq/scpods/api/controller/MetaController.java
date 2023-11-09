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

import br.ufes.willcq.scpods.api.dto.response.MetaResponseDTO;
import br.ufes.willcq.scpods.domain.model.Meta;
import br.ufes.willcq.scpods.domain.repository.MetaRepository;

@RestController
@RequestMapping( "/api/metas" )
public class MetaController {

    @Autowired
    private MetaRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Iterable<MetaResponseDTO> listar() {
        return this.mapAll( repository.findAll() );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<MetaResponseDTO> buscar( @PathVariable String id ) {

        var optMeta = repository.findById( id );

        if( optMeta.isPresent() ) {
            return ResponseEntity.ok().body( modelMapper.map( optMeta.get(), MetaResponseDTO.class ) );
        }
        return ResponseEntity.notFound().build();
    }

    private Iterable<MetaResponseDTO> mapAll( Iterable<Meta> metas ) {

        var spliterator = metas.spliterator();
        return StreamSupport.stream( spliterator, false ).map( meta -> modelMapper.map( meta, MetaResponseDTO.class ) ).collect( Collectors.toList() );
    }

}
