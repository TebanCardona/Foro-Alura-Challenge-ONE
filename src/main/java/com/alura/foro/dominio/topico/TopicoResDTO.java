package com.alura.foro.dominio.topico;

import java.time.LocalDateTime;

import com.alura.foro.dominio.curso.Curso;
import com.alura.foro.dominio.usuario.Usuario;

public record TopicoResDTO(
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime fechaCreacion,
    StatusTopico status,
    Usuario usuario,
    Curso curso) {
  public TopicoResDTO(Topico topico) {
    this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(),
        topico.getAutor(), topico.getCurso());
  }
}