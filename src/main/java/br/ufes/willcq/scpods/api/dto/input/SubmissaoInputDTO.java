package br.ufes.willcq.scpods.api.dto.input;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubmissaoInputDTO {

    @NotBlank( message = "Não foi informado o título da ação!" )
    private String titulo;

    @NotBlank( message = "Não foi informada a descrição da ação!" )
    private String descricao;

    private String urlEvidencia;

    @NotNull( message = "Não foi informada a data de início do ação!" )
    private LocalDate dataInicio;

    private LocalDate dataEncerramento;

    @NotNull( message = "Não foi informado o id da meta para a ação!" )
    private Long metaId;

    @NotNull( message = "Não foi informado o id do local para a ação!" )
    private Long localId;

    @NotNull( message = "Não foi informada o id da lotação para a ação!" )
    private Long lotacaoId;

    @NotNull( message = "Não foi informado o coordenador da ação!" )
    private CoordenadorInputDTO coordenador;

}
