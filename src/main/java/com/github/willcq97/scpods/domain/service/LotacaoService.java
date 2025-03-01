package com.github.willcq97.scpods.domain.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.github.willcq97.scpods.api.dto.select.SelectModel;

@Validated
public interface LotacaoService {

    public List<SelectModel<Long>> listarOpcoesLotacao();

}
