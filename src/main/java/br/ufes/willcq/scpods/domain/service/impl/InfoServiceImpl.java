package br.ufes.willcq.scpods.domain.service.impl;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.scpods.api.dto.response.CampusInfoDTO;
import br.ufes.willcq.scpods.api.dto.response.UnidadeInfoDTO;
import br.ufes.willcq.scpods.domain.exception.NegocioException;
import br.ufes.willcq.scpods.domain.model.Unidade;
import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;
import br.ufes.willcq.scpods.domain.repository.UnidadeRepository;
import br.ufes.willcq.scpods.domain.service.InfoService;

@Service
@Transactional
public class InfoServiceImpl implements InfoService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CampusInfoDTO obterContabilizacaoAcoes( String campus ) {
        var campusEnum = CampusEnum.obterEnum( campus );
        if( campusEnum == null ) {
            throw new NegocioException( "O campus informado não é válido!" );
        }

        var unidades = unidadeRepository.findByCampus( campusEnum );

        var campusInfo = new CampusInfoDTO();
        campusInfo.setCampus( campusEnum );
        campusInfo.setUnidades( unidades.stream().map( this::mapUnidadeToUnidadeInfo ).collect( Collectors.toList() ) );

        return campusInfo;
    }

    private UnidadeInfoDTO mapUnidadeToUnidadeInfo( Unidade unidade ) {
        return modelMapper.map( unidade, UnidadeInfoDTO.class );
    }

    private boolean isUnidadePossuiLocaisComProjetos( UnidadeInfoDTO unidadeInfo ) {
        unidadeInfo.getLocais().removeIf( local -> local.getQuantidadeProjetosTotais() <= 0 );
        return !unidadeInfo.getLocais().isEmpty();
    }
}
