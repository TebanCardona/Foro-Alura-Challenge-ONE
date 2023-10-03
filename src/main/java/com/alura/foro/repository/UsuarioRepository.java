package com.alura.foro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.alura.foro.dominio.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  UserDetails findByNombre(String username);
}