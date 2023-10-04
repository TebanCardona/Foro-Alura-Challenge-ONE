package com.alura.foro.dominio.topico;

import java.time.LocalDateTime;

public record TopicoGetTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, String status,
    String usuario, String curso) {

  public TopicoGetTopico(Topico topico) {
    this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
        topico.getStatus().toString(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
  }
}