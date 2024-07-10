package br.ufes.willcq.scpods.domain.service.impl;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.willcq.scpods.api.dto.response.UnidadeInfoDTO;
import br.ufes.willcq.scpods.api.dto.response.UnidadeResponseDTO;
import br.ufes.willcq.scpods.domain.exception.NegocioException;
import br.ufes.willcq.scpods.domain.model.Unidade;
import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;
import br.ufes.willcq.scpods.domain.repository.UnidadeRepository;
import br.ufes.willcq.scpods.domain.service.UnidadeService;

@Service
public class UnidadeServiceImpl implements UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CampusEnum> listarOpcoesCampus() {
        return Arrays.asList( CampusEnum.values() );
    }

    @Override
    public List<UnidadeResponseDTO> listarUnidades( String campus ) {
        if( campus == null ) {
            return unidadeRepository.findAll().stream().map( this::mapUnidadeToUnidadeResponseDTO ).toList();
        }
        return unidadeRepository.findByCampus( this.obterCampusEnum( campus ) ).stream().map( this::mapUnidadeToUnidadeResponseDTO ).toList();
    }

    @Override
    public List<UnidadeInfoDTO> obterContabilizacaoPorCampus( String campus ) {
        return unidadeRepository.findByCampus( this.obterCampusEnum( campus ) ).stream().map( this::mapUnidadeToUnidadeInfo ).toList();
    }

    @Override
    public UnidadeInfoDTO obterContabilizacaoParaUnidade( String codigo ) {

        var result = unidadeRepository.findByCodigo( codigo.toUpperCase() );
        if( result.isPresent() ) {
            return this.mapUnidadeToUnidadeInfo( result.get() );
        }

        throw new NegocioException( "O código informado para a unidade não existe!" );
    }

    private CampusEnum obterCampusEnum( String campus ) {

        var campusEnum = CampusEnum.obterEnum( campus );

        if( campusEnum == null ) {
            throw new NegocioException( "O campus informado não é válido!" );
        }
        return campusEnum;
    }

    private UnidadeInfoDTO mapUnidadeToUnidadeInfo( Unidade unidade ) {
        return modelMapper.map( unidade, UnidadeInfoDTO.class );
    }

    private UnidadeResponseDTO mapUnidadeToUnidadeResponseDTO( Unidade unidade ) {
        return modelMapper.map( unidade, UnidadeResponseDTO.class );
    }

}
