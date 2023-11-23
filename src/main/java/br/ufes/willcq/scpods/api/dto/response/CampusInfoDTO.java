package br.ufes.willcq.scpods.api.dto.response;

import java.util.List;

import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CampusInfoDTO {

    private CampusEnum campus;
    private List<UnidadeInfoDTO> unidades;

}
