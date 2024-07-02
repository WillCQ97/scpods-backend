package br.ufes.willcq.scpods.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.api.dto.AcaoGridDTO;
import br.ufes.willcq.scpods.api.dto.AcaoGridOptions;
import br.ufes.willcq.scpods.domain.model.Acao;

@Validated
public interface AcaoService {

    public List<Acao> listar( boolean aceito );

    public List<Acao> listarPorCampus( boolean aceito, String campus );

    public List<Acao> listarPorUnidade( boolean aceito, String codigoUnidade );

    public Optional<Acao> findById( Long id );

    public List<AcaoGridDTO> searchAcoes( AcaoGridOptions options );

    public Acao atualizar( Acao acao );

    public Acao inserirSubmissao( Acao acao );

    public void excluirSubmissao( Long idAcao );

    public void aceitarSubmissao( Long idAcao );
}
