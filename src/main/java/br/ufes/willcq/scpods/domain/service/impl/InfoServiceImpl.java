package br.ufes.willcq.scpods.domain.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.scpods.domain.service.InfoService;

@Service
@Transactional
public class InfoServiceImpl implements InfoService {

    private Long obterQuantidadeProjetosTotais() {
        return 0l;
    }

    private Long obterQuantidadeProjetosAtivos() {
        return 0l;
    }

    private Long obterQuantidadeObjetivosAtendidos() {
        return 0l;
    }

    private Long obterObjetivoMaisAtendido() {
        return 0l;
    }
}
