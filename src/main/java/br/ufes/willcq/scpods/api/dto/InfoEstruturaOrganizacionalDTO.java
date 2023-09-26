package br.ufes.willcq.scpods.api.dto;

import br.ufes.willcq.scpods.domain.model.EstruturaOrganizacional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InfoEstruturaOrganizacionalDTO {

    private EstruturaOrganizacional local; // trocar para dto
    private long quantidadeProjetosTotais;
    private long quantidadeProjetosAtivos;
    private long quantidadeODS;
    private long idOdsPrincipal; //pode voltar o dto mesmo

}
