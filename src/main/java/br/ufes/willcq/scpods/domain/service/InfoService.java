package br.ufes.willcq.scpods.domain.service;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.api.dto.response.CampusInfoDTO;

@Validated
public interface InfoService {

    public CampusInfoDTO obterContabilizacaoAcoes( String campus );

}
