package br.ufes.willcq.scpods.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.annotation.Validated;

import br.ufes.willcq.scpods.domain.model.Acao;

@Validated
public interface AcaoService {

    public Optional<Acao> buscarPeloId( Long id );

    public List<Acao> listar( boolean aceito );

    public List<Acao> listarPorCampus( boolean aceito, String campus );

    public Acao atualizar( Acao acao );

    public Acao salvar( Acao acao );

    public void excluir( Long idAcao );

    public void aceitarSubmissao( Long idAcao );
}
