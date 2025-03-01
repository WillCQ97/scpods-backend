package com.github.willcq97.scpods.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.willcq97.scpods.api.dto.select.SelectModel;
import com.github.willcq97.scpods.domain.service.LotacaoService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping( "/lotacoes" )
@Tag( name = "Lotações das Ações" )
public class LotacaoController {

    @Autowired
    private LotacaoService service;

    @GetMapping( "/opcoes-lotacao" )
    public List<SelectModel<Long>> listarOpcoes() {
        return service.listarOpcoesLotacao();
    }

}
