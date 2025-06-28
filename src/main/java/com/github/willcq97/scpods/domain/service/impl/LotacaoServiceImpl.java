package com.github.willcq97.scpods.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.willcq97.scpods.api.dto.select.SelectModel;
import com.github.willcq97.scpods.domain.repository.LotacaoRepository;
import com.github.willcq97.scpods.domain.service.LotacaoService;

@Service
public class LotacaoServiceImpl implements LotacaoService {

    @Autowired
    private LotacaoRepository lotacaoRepository;

    public List<SelectModel<Long>> listarOpcoesLotacao() {
        return lotacaoRepository.listarOpcoesLotacao();
    }

}
