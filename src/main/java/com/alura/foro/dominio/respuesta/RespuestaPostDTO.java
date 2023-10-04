package com.alura.foro.dominio.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespuestaPostDTO(@NotNull @NotBlank String mensaje,
    @NotNull Long topico_id,
    @NotNull Long autor_id) {
}