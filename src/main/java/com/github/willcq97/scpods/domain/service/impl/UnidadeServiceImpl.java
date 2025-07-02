package com.github.willcq97.scpods.domain.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.willcq97.scpods.api.dto.select.SelectModel;
import com.github.willcq97.scpods.api.dto.select.SelectModelString;
import com.github.willcq97.scpods.domain.exception.BusinessException;
import com.github.willcq97.scpods.domain.model.Unidade;
import com.github.willcq97.scpods.domain.model.enums.CampusEnum;
import com.github.willcq97.scpods.domain.repository.UnidadeRepository;
import com.github.willcq97.scpods.domain.service.UnidadeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UnidadeServiceImpl implements UnidadeService {

    private final UnidadeRepository unidadeRepository;

    @Override
    public List<SelectModelString> listarOpcoesCampus() {
        return Arrays.asList( CampusEnum.values() )
                .stream()
                .map( val -> new SelectModelString( val.name(), val.getDescricao() ) )
                .toList();
    }

    @Override
    public List<SelectModel<String>> listarOpcoesUnidades() {
        return unidadeRepository.listarOpcoesUnidades();
    }

    @Override
    public List<Unidade> listarUnidades( String campus ) {
        if( campus == null ) {
            return unidadeRepository.findAll();
        }
        return unidadeRepository.findByCampus( this.obterCampusEnum( campus ) );
    }

    @Override
    public List<Unidade> obterContabilizacaoPorCampus( String campus ) {
        return unidadeRepository.findByCampus( this.obterCampusEnum( campus ) );
    }

    @Override
    public Unidade obterContabilizacaoParaUnidade( String codigo ) {

        var result = unidadeRepository.findByCodigo( codigo.toUpperCase() );
        if( result.isPresent() ) {
            return result.get();
        }

        throw new EntityNotFoundException( "Não foi encontrada uma unidade para o código informado!" );
    }

    private CampusEnum obterCampusEnum( String campus ) {

        var campusEnum = CampusEnum.obterEnum( campus );
        if( campusEnum == null ) {
            throw new BusinessException( "O campus informado não é válido!" );
        }
        return campusEnum;
    }

}
