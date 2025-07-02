package com.github.willcq97.scpods.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.willcq97.scpods.api.dto.select.SelectModel;
import com.github.willcq97.scpods.domain.repository.LotacaoRepository;
import com.github.willcq97.scpods.domain.service.LotacaoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LotacaoServiceImpl implements LotacaoService {

    private final LotacaoRepository lotacaoRepository;

    public List<SelectModel<Long>> listarOpcoesLotacao() {
        return lotacaoRepository.listarOpcoesLotacao();
    }

}
