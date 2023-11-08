package br.ufes.willcq.scpods.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "tb_unidades" )
public class Unidade {

    @Id
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Enumerated( EnumType.STRING )
    private CampusEnum campus;

    @OneToMany( mappedBy = "unidade" )
    private List<Lotacao> lotacoes;

}
