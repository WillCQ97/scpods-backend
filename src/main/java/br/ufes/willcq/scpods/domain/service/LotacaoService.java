package br.ufes.willcq.scpods.domain.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.api.dto.select.SelectModel;

@Validated
public interface LotacaoService {

    public List<SelectModel<Long>> listarOpcoesLotacao();

}
