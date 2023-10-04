package com.alura.foro.dominio.respuesta;

import java.time.LocalDateTime;

public record RespuestaResDTO(String mensaje, String topico, String autor, LocalDateTime fechaCreacion) {
  public RespuestaResDTO(Respuesta respuesta) {
    this(
        respuesta.getMensaje(),
        respuesta.getTopico().getTitulo(),
        respuesta.getAutor().getNombre(),
        respuesta.getFechaCreacion());
  }
}
