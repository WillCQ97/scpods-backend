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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table( name = "tb_lotacoes" )
public class Lotacao {

    @Id
    private Long id;

    @NotBlank
    private String descricao;

    @NotBlank
    private String sigla;

    @Enumerated( EnumType.STRING )
    private CampusEnum campus;

    @OneToMany( mappedBy = "lotacao" )
    private List<Acao> acoes;

}
