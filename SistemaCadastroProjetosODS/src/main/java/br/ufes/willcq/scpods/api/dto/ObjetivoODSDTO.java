package br.ufes.willcq.scpods.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ObjetivoODSDTO {

    private Long id;
    private String titulo;
    private String codigoCor;
    private List<MetaODSDTO> metas;

}
