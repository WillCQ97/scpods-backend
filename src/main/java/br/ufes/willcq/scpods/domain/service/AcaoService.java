package br.ufes.willcq.scpods.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.api.dto.AcaoSearchDTO;
import br.ufes.willcq.scpods.api.dto.AcaoSearchFilter;
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

    public List<AcaoSearchDTO> searchAcoes( AcaoSearchFilter filter );

    public List<AcaoSearchDTO> searchSubmissoes( AcaoSearchFilter filter );

    public Acao atualizar( Acao acao );

    public void inserirSubmissao( Acao acao );

    public void excluirSubmissao( Long idAcao );

    public void aceitarSubmissao( Long idAcao );
}
