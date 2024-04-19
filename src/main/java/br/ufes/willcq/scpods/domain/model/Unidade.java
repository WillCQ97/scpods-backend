package br.ufes.willcq.scpods.domain.model;

import java.util.List;

import br.ufes.willcq.scpods.domain.model.enums.CampusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Entity
@Table( name = "tb_unidades" )
public class Unidade {

    @Id
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String codigo;

    @NotNull
    @Enumerated( EnumType.STRING )
    private CampusEnum campus;

    @OneToMany( mappedBy = "unidade" )
    private List<Local> locais;

}
