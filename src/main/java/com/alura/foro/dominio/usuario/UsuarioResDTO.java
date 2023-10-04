package com.alura.foro.dominio.usuario;

public record UsuarioResDTO(Long id, String nombre, String email) {
  public UsuarioResDTO(Usuario usuario) {
    this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
  }
}