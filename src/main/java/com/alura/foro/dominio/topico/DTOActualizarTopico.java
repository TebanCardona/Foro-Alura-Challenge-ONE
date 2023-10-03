package com.alura.foro.dominio.topico;

import com.alura.foro.dominio.curso.Curso;
import com.alura.foro.dominio.usuario.Usuario;

public record DTOActualizarTopico(Long id, String titulo, String mensaje, Usuario usuario, Curso curso) {
}