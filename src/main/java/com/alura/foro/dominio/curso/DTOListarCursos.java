package com.alura.foro.dominio.curso;

public record DTOListarCursos(Long id, String nombre, String categoria) {

  public DTOListarCursos(Curso curso) {
    this(curso.getId(), curso.getNombre(), curso.getCategoria());
  }
}
