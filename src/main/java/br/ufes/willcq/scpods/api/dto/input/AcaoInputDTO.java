package br.ufes.willcq.scpods.api.dto.input;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AcaoInputDTO {

    private String titulo;
    private String descricao;

    private LocalDate dataCadastro;
    private LocalDate dataInicio;
    private LocalDate dataEncerramento;

    private MetaInputDTO meta;
    private LocalInputDTO local;

    private CoordenadorInputDTO coordenador;

}
