package br.ufes.willcq.scpods.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufes.willcq.scpods.model.MetaODS;
import br.ufes.willcq.scpods.repository.MetaODSRepository;
import br.ufes.willcq.scpods.service.MetaODSService;

@Service
@Transactional
public class MetaODSServiceImpl implements MetaODSService {

    @Autowired
    private MetaODSRepository metaODSRepository;

    @Override
    public Iterable<MetaODS> listar() {
        return metaODSRepository.findAll();
    }

}
