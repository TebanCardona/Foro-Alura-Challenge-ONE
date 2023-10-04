package com.alura.foro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.foro.dominio.topico.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

  Boolean existsByTituloAndMensaje(String titulo, String mensaje);
}