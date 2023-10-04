package com.alura.foro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alura.foro.dominio.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  Usuario findByEmail(String subject);
}