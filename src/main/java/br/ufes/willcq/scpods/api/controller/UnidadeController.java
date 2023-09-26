package br.ufes.willcq.scpods.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.willcq.scpods.api.dto.InfoAlegreDTO;
import br.ufes.willcq.scpods.api.dto.InfoEstruturaOrganizacionalDTO;
import br.ufes.willcq.scpods.domain.model.Acao;
import br.ufes.willcq.scpods.domain.model.EstruturaOrganizacional;
import br.ufes.willcq.scpods.domain.repository.AcaoRepository;

@RestController
@RequestMapping( "/api/v0/unidades" )
public class UnidadeController {

    @Autowired
    private AcaoRepository acaoRepository;

    @GetMapping( "/alegre/info" )
    public ResponseEntity<InfoAlegreDTO> contabilizarAcoes() {
        
        var acoes = this.acaoRepository.findAll();
        var acoesPorDepartamento = new HashMap<EstruturaOrganizacional, List<Acao>>();
        
        StreamSupport.stream( acoes.spliterator(), false )
            .filter( this::isAcaoCentroAlegre )
            .forEach(acao -> {
                if(acoesPorDepartamento.containsKey(acao.getEstruturaOrganizacional())){
                    acoesPorDepartamento.get(acao.getEstruturaOrganizacional()).add(acao);
                }else{
                    var array = new ArrayList<Acao>();
                    array.add(acao);
                    acoesPorDepartamento.put(acao.getEstruturaOrganizacional(), array);
                }
            });

        var infoDepartamento = new ArrayList<InfoEstruturaOrganizacionalDTO>();

        for(var entry: acoesPorDepartamento.entrySet()){
            var key = entry.getKey();
            var value = entry.getValue();

            var info = new InfoEstruturaOrganizacionalDTO();
            info.setLocal(key);
            info.setQuantidadeProjetosTotais(value.size());
            info.setQuantidadeProjetosAtivos(value.stream().filter(acao -> acao.getDataFim() != null).count());
            infoDepartamento.add(info);
        }

/*
 * 
 var total = 0;
 var totalAtivos = 0;
 var objetivosImplantados = new HashMap<Long, Long>();
 
 for( var acao : acoes ) {
     if( this.isAcaoCentroAlegre(acao) ) {
         total++; //quantidadeProjetosTotais
         
         if(acao.getDataFim() == null){ //projeto ativo
            totalAtivos++;
                }
                
                if(objetivosImplantados.containsKey(acao.getMeta().getIdObjetivo())){
                    var valorAtual = objetivosImplantados.get(acao.getMeta().getIdObjetivo());
                    objetivosImplantados.replace(acao.getMeta().getIdObjetivo(), valorAtual+1);
                }
            }
        }
        
        */
        return ResponseEntity.ok().body( new InfoAlegreDTO(infoDepartamento, null, null) );
    }

    private boolean isAcaoCentroAlegre( Acao acao ) {
        return acao.getEstruturaOrganizacional().getUnidade().getNome().equalsIgnoreCase( "Alegre" );
    }

    private boolean isAcaoUnidadeRive( Acao acao ) {
        return acao.getEstruturaOrganizacional().getUnidade().getNome().equalsIgnoreCase( "rive" );
    }
}
