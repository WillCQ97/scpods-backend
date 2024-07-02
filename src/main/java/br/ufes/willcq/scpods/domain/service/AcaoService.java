package br.ufes.willcq.scpods.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.api.dto.AcaoGridDTO;
import br.ufes.willcq.scpods.api.dto.AcaoGridOptions;
import br.ufes.willcq.scpods.domain.model.Acao;

@Validated
public interface AcaoService {

    public boolean existsById( Long idAcao );

    public List<Acao> listar( boolean aceito );

    public List<Acao> listarPorCampus( boolean aceito, String campus );

    public List<Acao> listarPorUnidade( boolean aceito, String codigoUnidade );

    public Acao findById( Long id );

    public Optional<Acao> findAcaoById( Long id );

    public Optional<Acao> findSubmissaoById( Long id );

    public List<AcaoGridDTO> searchAcoes( AcaoGridOptions options );

    public List<AcaoGridDTO> searchSubmissoes( AcaoGridOptions options );

    public Acao atualizar( Acao acao );

    public void inserirSubmissao( Acao acao );

    public void excluirSubmissao( Long idAcao );

    public void aceitarSubmissao( Long idAcao );
}
