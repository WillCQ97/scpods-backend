package com.github.willcq97.scpods.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.github.willcq97.scpods.api.dto.input.SubmissaoInputDTO;
import com.github.willcq97.scpods.domain.model.entity.Acao;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AcaoMapper {

    private final ModelMapper modelMapper;

    public Acao mapToEntity( SubmissaoInputDTO dto ) {
        return modelMapper.map( dto, Acao.class );
    }

}
