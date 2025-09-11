package com.unifucamp.gamearena.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.unifucamp.gamearena.role.domain.Roles;

public record CriacaoUsuarioDto(
                @NotBlank @Email String email,

                @NotBlank @Length(min = 6, max = 20) String password,

                @NotNull Roles role) {
}
