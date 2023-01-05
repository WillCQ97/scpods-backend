package br.ufes.willcq.scpods.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.scpods.domain.repository.AcaoRepository;
import br.ufes.willcq.scpods.domain.service.CadastroAcaoService;

@Service
@Transactional
public class CadastroAcaoServiceImpl implements CadastroAcaoService {

    @Autowired
    private AcaoRepository repository;

}
