package com.github.willcq97.scpods.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.willcq97.scpods.api.dto.select.SelectModel;
import com.github.willcq97.scpods.domain.service.LotacaoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping( "/lotacoes" )
@Tag( name = "Lotações das Ações" )
@AllArgsConstructor
public class LotacaoController {

    private final LotacaoService service;

    @GetMapping( "/opcoes-lotacao" )
    @ResponseStatus( HttpStatus.OK )
    public List<SelectModel<Long>> listarOpcoes() {
        return service.listarOpcoesLotacao();
    }

}
