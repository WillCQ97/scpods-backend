package br.ufes.willcq.scpods.domain.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.api.dto.response.CampusInfoDTO;
import br.ufes.willcq.scpods.api.dto.response.CampusResponseDTO;
import br.ufes.willcq.scpods.api.dto.response.UnidadeResponseDTO;
import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;

@Validated
public interface UnidadeService {

    public List<CampusEnum> listarOpcoesCampus();

    public List<UnidadeResponseDTO> listarUnidades( String campus );

    public CampusInfoDTO obterContabilizacaoAcoes( String nomeCampus );

    public CampusResponseDTO obterLocaisPorUnidade( String nomeCampus );

}
