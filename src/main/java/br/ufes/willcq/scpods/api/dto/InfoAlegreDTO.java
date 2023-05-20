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
public class InfoAlegreDTO {

    private List<InfoEstruturaOrganizacionalDTO> sede;

    private List<InfoEstruturaOrganizacionalDTO> jeronimo;

    private List<InfoEstruturaOrganizacionalDTO> rive;

}
