package com.alura.foro.helpers.statusTopico;

import org.springframework.stereotype.Component;

import com.alura.foro.dominio.topico.StatusTopico;
import com.alura.foro.utils.errors.ValidacionRechazada;

@Component
public class StatusNoExiste {
  public void validar(String statusNuevo) {
    boolean statusExists = false;
    for (StatusTopico status : StatusTopico.values()) {
      if (status.name().equals(statusNuevo)) {
        statusExists = true;
        return;
      }
    }
    if (!statusExists) {
      throw new ValidacionRechazada("No existe el Estatus de Tópico Seleccionado");
    }
  }
}