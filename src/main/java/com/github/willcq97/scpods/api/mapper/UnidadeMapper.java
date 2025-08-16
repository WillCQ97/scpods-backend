package com.github.willcq97.scpods.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.github.willcq97.scpods.api.dto.response.UnidadeInfoDTO;
import com.github.willcq97.scpods.api.dto.response.UnidadeResponseDTO;
import com.github.willcq97.scpods.domain.model.entity.Unidade;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UnidadeMapper {

    private ModelMapper modelMapper;

    public UnidadeInfoDTO mapToUnidadeInfo( Unidade unidade ) {
        return modelMapper.map( unidade, UnidadeInfoDTO.class );
    }

    public UnidadeResponseDTO mapToResponse( Unidade unidade ) {
        return modelMapper.map( unidade, UnidadeResponseDTO.class );
    }

    public List<UnidadeResponseDTO> mapAllToResponse( List<Unidade> unidades ) {
        return unidades.stream().map( this::mapToResponse ).toList();
    }

    public List<UnidadeInfoDTO> mapAllToUnidadeInfo( List<Unidade> unidades ) {
        return unidades.stream().map( this::mapToUnidadeInfo ).toList();
    }

}
