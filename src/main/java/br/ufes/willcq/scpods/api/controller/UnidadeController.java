package br.ufes.willcq.scpods.api.controller;

import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.InfoAlegreDTO;
import br.ufes.willcq.scpods.domain.model.Acao;
import br.ufes.willcq.scpods.domain.repository.AcaoRepository;

@RestController
@RequestMapping( "/api/v0/unidades" )
public class UnidadeController {

    @Autowired
    private AcaoRepository acaoRepository;

    @GetMapping( "/alegre/info" )
    public ResponseEntity<InfoAlegreDTO> contabilizarAcoes() {
        var response = new InfoAlegreDTO();

        var acoes = this.acaoRepository.findAll();

        var acoesCentrosAlegre = StreamSupport.stream( acoes.spliterator(), false ).filter( this::isAcaoCentroAlegre );

        response.setRive( null );

        var total = 0;
        for( var acao : acoes ) {
            if( acao.getEstruturaOrganizacional().getCentro().getUnidade().getNome().equalsIgnoreCase( "Alegre" ) ) {
                total++;
            }
        }

        return ResponseEntity.ok().body( new InfoAlegreDTO() );
    }

    private boolean isAcaoCentroAlegre( Acao acao ) {
        return acao.getEstruturaOrganizacional().getCentro().getUnidade().getNome().equalsIgnoreCase( "Alegre" );
    }

    private boolean isAcaoUnidadeRive( Acao acao ) {
        return acao.getEstruturaOrganizacional().getUnidade().getNome().equalsIgnoreCase( "rive" );
    }
}
