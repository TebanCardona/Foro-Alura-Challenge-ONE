package com.alura.foro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.foro.dominio.curso.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}