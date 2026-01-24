package com.github.willcq97.scpods.domain.model.entity;

import com.github.willcq97.scpods.domain.model.enums.TipoVinculoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table( name = "tb_coordenadores" )
public class Coordenador {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String nome;

    private String email;

    @Enumerated( EnumType.STRING )
    @Column( name = "tipo_vinculo" )
    private TipoVinculoEnum tipoVinculo;

    @Column( name = "ds_vinculo" )
    private String descricaoVinculo;

    public String getDescricaoVinculo() {
        if( this.descricaoVinculo == null ) {
            return tipoVinculo.getDescricao();
        }
        return this.descricaoVinculo;
    }

    public void setDescricaoVinculo( String descricaoVinculo ) {
        if( TipoVinculoEnum.OUTRO.equals( this.tipoVinculo ) ) {
            this.descricaoVinculo = descricaoVinculo;
        }
        this.descricaoVinculo = null;
    }
}
