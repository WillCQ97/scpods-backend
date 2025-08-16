package com.github.willcq97.scpods.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.github.willcq97.scpods.api.dto.response.MetaResponseDTO;
import com.github.willcq97.scpods.api.dto.response.ObjetivoResponseDTO;
import com.github.willcq97.scpods.domain.model.entity.Meta;
import com.github.willcq97.scpods.domain.model.entity.Objetivo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ObjetivoMapper {

    private ModelMapper modelMapper;

    public List<ObjetivoResponseDTO> mapAllToResponse( List<Objetivo> objetivos ) {
        return objetivos.stream().map( obj -> modelMapper.map( obj, ObjetivoResponseDTO.class ) ).toList();
    }

    public ObjetivoResponseDTO mapToResponse( Objetivo objetivo ) {
        return modelMapper.map( objetivo, ObjetivoResponseDTO.class );
    }

    public MetaResponseDTO mapToResponse( Meta meta ) {
        return modelMapper.map( meta, MetaResponseDTO.class );
    }
}
