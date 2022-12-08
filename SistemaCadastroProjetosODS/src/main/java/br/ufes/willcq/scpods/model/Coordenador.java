package br.ufes.willcq.scpods.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.ufes.willcq.scpods.model.enums.TipoVinculoEnum;
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
    private Long id;

    @Enumerated( EnumType.STRING )
    @Column( name = "tipo_vinculo" )
    private TipoVinculoEnum tipoVinculo;

    @Column( name = "ds_vinculo" )
    private String descricaoVinculo;

    private String nome;

    public String getDescricaoVinculo() {
        if( this.descricaoVinculo == null ) {
            return tipoVinculo.getDescricao();
        }
        return this.descricaoVinculo;
    }
}
