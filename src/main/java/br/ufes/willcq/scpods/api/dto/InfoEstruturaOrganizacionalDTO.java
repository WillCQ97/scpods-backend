package br.ufes.willcq.scpods.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InfoEstruturaOrganizacionalDTO {

    private EstruturaOrganizacionalDTO local;
    private int quantidadeProjetosTotais;
    private int quantidadeProjetosAtivos;
    private int quantidadeODS;
    private int idOdsPrincipal;

}
