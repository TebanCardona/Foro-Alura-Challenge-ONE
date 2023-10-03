package com.alura.foro.dominio.usuario;

public record DTOListarUsuarios(Long id, String nombre, String email) {

  public DTOListarUsuarios(Usuario usuario) {
    this(usuario.getId(), usuario.getUsername(), usuario.getEmail());
  }
}