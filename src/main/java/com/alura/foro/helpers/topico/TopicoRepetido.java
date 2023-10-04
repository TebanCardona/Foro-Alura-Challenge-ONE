package com.alura.foro.helpers.topico;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import com.alura.foro.dominio.topico.TopicoPostDTO;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.utils.errors.ValidacionRechazada;

@Component
public class TopicoRepetido {
  public final TopicoRepository topicoRepository;

  public TopicoRepetido(TopicoRepository topicoRepository) {
    this.topicoRepository = topicoRepository;
  }

  public void validar(@NotNull TopicoPostDTO datos) {
    Boolean topicoRepetido = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());

    if (topicoRepetido) {
      throw new ValidacionRechazada("Ya existe un tópico con ese título y mensaje.");
    }
  }
}