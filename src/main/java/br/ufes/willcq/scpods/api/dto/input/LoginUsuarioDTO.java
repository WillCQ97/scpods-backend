package br.ufes.willcq.scpods.api.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginUsuarioDTO {

    @NotNull( message = "Não foi informado o nome de usuário" )
    private String username;

    @NotNull( message = "Não foi informada a senha" )
    private String password;

}
