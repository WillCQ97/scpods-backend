package br.ufes.willcq.scpods.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.willcq.scpods.api.dto.select.SelectModel;
import br.ufes.willcq.scpods.domain.repository.LotacaoRepository;

@Service
public class LotacaoServiceImpl {

    @Autowired
    private LotacaoRepository lotacaoRepository;

    public List<SelectModel<Long>> listarOpcoesLotacao() {
        return lotacaoRepository.listarOpcoesLocatacao();
    }

}
