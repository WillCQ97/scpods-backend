package br.ufes.willcq.scpods.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

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

    private String nome;

    @Enumerated( EnumType.STRING )
    private CampusEnum campus;

}
