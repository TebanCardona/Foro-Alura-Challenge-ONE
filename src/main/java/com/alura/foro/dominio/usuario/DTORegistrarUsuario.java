package com.alura.foro.dominio.usuario;

import jakarta.validation.constraints.NotBlank;

public record DTORegistrarUsuario(
    @NotBlank String nombre,
    @NotBlank String email,
    @NotBlank String password) {
}