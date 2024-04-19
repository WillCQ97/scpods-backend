package br.ufes.willcq.scpods.domain.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.scpods.api.dto.response.CampusInfoDTO;
import br.ufes.willcq.scpods.api.dto.response.CampusResponseDTO;
import br.ufes.willcq.scpods.api.dto.response.UnidadeInfoDTO;
import br.ufes.willcq.scpods.api.dto.response.UnidadeResponseDTO;
import br.ufes.willcq.scpods.domain.exception.NegocioException;
import br.ufes.willcq.scpods.domain.model.Unidade;
import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;
import br.ufes.willcq.scpods.domain.repository.UnidadeRepository;
import br.ufes.willcq.scpods.domain.service.UnidadeService;

@Service
@Transactional
public class UnidadeServiceImpl implements UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UnidadeResponseDTO> obterUnidades( String nomeCampus ) {
        if( nomeCampus == null ) {
            return unidadeRepository.findAll().stream().map( this::mapUnidadeToUnidadeResponseDTO ).toList();
        }
        return unidadeRepository.findByCampus( obterCampusEnum( nomeCampus ) ).stream().map( this::mapUnidadeToUnidadeResponseDTO ).toList();
    }

    @Override
    public CampusInfoDTO obterContabilizacaoAcoes( String nomeCampus ) {

        var campusEnum = obterCampusEnum( nomeCampus );
        var unidades = unidadeRepository.findByCampus( campusEnum );

        var campusInfoDTO = new CampusInfoDTO();
        campusInfoDTO.setCampus( campusEnum );
        campusInfoDTO.setUnidades( unidades.stream().map( this::mapUnidadeToUnidadeInfo ).toList() );

        return campusInfoDTO;
    }

    @Override
    public CampusResponseDTO obterLocaisPorUnidade( String nomeCampus ) {

        var campusEnum = obterCampusEnum( nomeCampus );
        var unidades = unidadeRepository.findByCampus( campusEnum );

        var campusResponseDTO = new CampusResponseDTO();
        campusResponseDTO.setCampus( campusEnum );
        campusResponseDTO.setUnidades( unidades.stream().map( this::mapUnidadeToUnidadeResponseDTO ).toList() );

        return campusResponseDTO;
    }

    private CampusEnum obterCampusEnum( String nomeCampus ) {

        var campusEnum = CampusEnum.obterEnum( nomeCampus );
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

    private boolean isUnidadePossuiLocaisComProjetos( UnidadeInfoDTO unidadeInfo ) {
        unidadeInfo.getLocais().removeIf( local -> local.getProjetosTotais() <= 0 );
        return !unidadeInfo.getLocais().isEmpty();
    }

}
