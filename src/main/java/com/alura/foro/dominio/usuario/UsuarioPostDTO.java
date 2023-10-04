package com.alura.foro.dominio.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioPostDTO(
    @NotBlank String nombre,
    @NotBlank String email,
    @NotBlank String password) {
}