package com.alura.foro.dominio.curso;

import jakarta.validation.constraints.NotBlank;

public record CursoPostDTO(

    @NotBlank String nombre,
    @NotBlank String categoria) {
}
