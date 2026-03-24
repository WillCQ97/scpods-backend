package com.github.willcq97.scpods.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUsuarioDTO {

    @NotBlank( message = "Não foi informado o nome de usuário" )
    private String username;

    @NotBlank( message = "Não foi informada a senha" )
    private String password;

}
