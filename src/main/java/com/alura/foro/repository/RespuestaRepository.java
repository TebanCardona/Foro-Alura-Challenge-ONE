package com.alura.foro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.foro.dominio.respuesta.Respuesta;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
}