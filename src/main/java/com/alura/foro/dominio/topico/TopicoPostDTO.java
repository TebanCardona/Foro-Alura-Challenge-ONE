package com.alura.foro.dominio.topico;

import com.alura.foro.dominio.curso.Curso;
import com.alura.foro.dominio.usuario.Usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoPostDTO(
    @NotBlank String titulo,
    @NotBlank String mensaje,
    @NotNull @Valid Usuario usuario,
    @NotNull @Valid Curso curso) {
}