package br.ufes.willcq.scpods.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginUsuarioDTO {

    @NotBlank( message = "Não foi informado o nome de usuário" )
    private String username;

    @NotBlank( message = "Não foi informada a senha" )
    private String password;

}
