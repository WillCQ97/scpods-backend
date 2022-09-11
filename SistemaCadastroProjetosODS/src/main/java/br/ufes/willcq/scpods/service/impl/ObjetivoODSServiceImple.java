package br.ufes.willcq.scpods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufes.willcq.scpods.model.ObjetivoODS;
import br.ufes.willcq.scpods.repository.ObjetivoODSRepository;
import br.ufes.willcq.scpods.service.ObjetivoODSService;

@Service
@Transactional
public class ObjetivoODSServiceImple implements ObjetivoODSService {

    // TODO: verificar o que faz essa anotação
    @Autowired
    private ObjetivoODSRepository objetivoODSRepository;

    @Override
    public Iterable<ObjetivoODS> listar() {
        return objetivoODSRepository.findAll();
    }

    @Override
    public ObjetivoODS buscar( Long id ) {
        var objetivoOptional = objetivoODSRepository.findById( id );

        if( objetivoOptional.isPresent() ) {
            return objetivoOptional.get();
        } else {
            // TODO: implementar uma exception própria
            throw new RuntimeException( "Não foi encontrado um objetivo com o id informado!" );
        }
    }

}
