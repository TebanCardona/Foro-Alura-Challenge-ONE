package com.alura.foro.dominio.topico;

import java.time.LocalDateTime;

import com.alura.foro.dominio.curso.Curso;
import com.alura.foro.dominio.usuario.Usuario;

public record DTORespuestaTopico(
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime fechaCreacion,
    StatusTopico status,
    Usuario usuario,
    Curso curso) {
}