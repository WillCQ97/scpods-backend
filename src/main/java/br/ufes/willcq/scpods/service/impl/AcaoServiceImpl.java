package br.ufes.willcq.scpods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.scpods.model.Acao;
import br.ufes.willcq.scpods.repository.AcaoRepository;
import br.ufes.willcq.scpods.service.AcaoService;

@Service
@Transactional
public class AcaoServiceImpl implements AcaoService {

    @Autowired
    private AcaoRepository repository;

    @Override
    public Iterable<Acao> listar() {
        return repository.findAll();
    }
}
