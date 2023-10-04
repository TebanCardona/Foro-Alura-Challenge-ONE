package com.alura.foro.dominio.curso;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private String categoria;

  public Curso(CursoPostDTO cursoRegistrarDTO) {
    this.nombre = cursoRegistrarDTO.nombre();
    this.categoria = cursoRegistrarDTO.categoria();
  }

  public void actualizarDatos(CursoPutDTO cursoActualizarDTO) {
    if (cursoActualizarDTO.categoria() != null) {
      this.categoria = cursoActualizarDTO.categoria();
    }
    if (cursoActualizarDTO.nombre() != null) {
      this.nombre = cursoActualizarDTO.nombre();
    }

  }
}