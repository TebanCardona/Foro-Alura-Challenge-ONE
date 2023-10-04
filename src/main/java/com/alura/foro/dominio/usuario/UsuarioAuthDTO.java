package com.alura.foro.dominio.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioAuthDTO(@NotNull @NotBlank @Email String email,
    @NotNull @NotBlank String password) {
}