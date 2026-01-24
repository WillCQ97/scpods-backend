package com.github.willcq97.scpods.domain.model.entity;

import java.util.List;

import com.github.willcq97.scpods.domain.model.enums.CampusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    private String codigo;

    @Enumerated( EnumType.STRING )
    private CampusEnum campus;

    @OneToMany( mappedBy = "unidade" )
    private List<Local> locais;

}
