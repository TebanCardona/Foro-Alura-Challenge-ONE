package com.alura.foro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.foro.dominio.topico.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

  Page<Topico> findByActivoTrue(Pageable paginacion);
}