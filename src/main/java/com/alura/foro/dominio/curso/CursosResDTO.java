package com.alura.foro.dominio.curso;

public record CursosResDTO(Long id, String nombre, String categoria) {

  public CursosResDTO(Curso curso) {
    this(curso.getId(), curso.getNombre(), curso.getCategoria());
  }
}
