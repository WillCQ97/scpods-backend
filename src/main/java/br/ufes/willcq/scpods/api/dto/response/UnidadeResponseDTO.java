package br.ufes.willcq.scpods.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UnidadeResponseDTO {

    private Long id;
    private String nome;
    private String codigo;
    private String campus;

    private List<LocalResponseDTO> locais;

}
