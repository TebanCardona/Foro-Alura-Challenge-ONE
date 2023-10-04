package com.alura.foro.dominio.usuario;

public record UsuarioGetDTO(Long id, String nombre, String email) {

  public UsuarioGetDTO(Usuario usuario) {
    this(usuario.getId(), usuario.getUsername(), usuario.getEmail());
  }
}