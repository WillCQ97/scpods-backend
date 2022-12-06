package br.ufes.willcq.scpods.model.enums;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table( name = "tb_tipo_vinculo" )
public enum TipoVinculoEnum {

    PROF( 1L, "Professor" ), TECNICO_ADM( 2L, "Servidor técnico-administrativo" ), ALUNO_POS( 3L, "Aluno de pós-graduação" ), ALUNO_GRADUACAO( 4L, "Aluno de graduação" ), OUTRO( 5L, "Outro" );

    @Id
    private Long id;

    private String descricao;

    private TipoVinculoEnum( Long id, String descricao ) {
        this.id = id;
        this.descricao = descricao;
    }

}
