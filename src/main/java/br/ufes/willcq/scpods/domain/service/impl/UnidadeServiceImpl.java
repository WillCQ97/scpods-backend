package br.ufes.willcq.scpods.domain.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.willcq.scpods.api.dto.select.SelectModelString;
import br.ufes.willcq.scpods.domain.exception.BusinessException;
import br.ufes.willcq.scpods.domain.exception.EntityNotFoundException;
import br.ufes.willcq.scpods.domain.model.Unidade;
import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;
import br.ufes.willcq.scpods.domain.repository.UnidadeRepository;
import br.ufes.willcq.scpods.domain.service.UnidadeService;

@Service
public class UnidadeServiceImpl implements UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Override
    public List<SelectModelString> listarOpcoesCampus() {
        return Arrays.asList( CampusEnum.values() )
                .stream()
                .map( val -> new SelectModelString( val.name(), val.getDescricao() ) )
                .toList();
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
