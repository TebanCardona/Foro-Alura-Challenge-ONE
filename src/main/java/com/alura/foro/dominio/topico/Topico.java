package com.alura.foro.dominio.topico;

import jakarta.persistence.*;
import lombok.*;
import com.alura.foro.dominio.usuario.Usuario;
import com.alura.foro.dominio.curso.Curso;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String titulo;
  private String mensaje;
  private LocalDateTime fechaCreacion = LocalDateTime.now();

  @Enumerated(EnumType.STRING)
  private StatusTopico status = StatusTopico.NO_RESPONDIDO;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "autor_id")
  private Usuario autor;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "curso_id")
  private Curso curso;

  public Topico(TopicoPostDTO topicoPostDto, Usuario autor, Curso curso) {
    this.titulo = topicoPostDto.titulo();
    this.mensaje = topicoPostDto.mensaje();
    this.autor = autor;
    this.curso = curso;
  }

  public void actualizaDatos(TopicoPutDTO topicoPutDto) {
    if (topicoPutDto.titulo() != null) {
      this.titulo = topicoPutDto.titulo();
    }
    if (topicoPutDto.status() != null) {
      this.status = StatusTopico.valueOf(topicoPutDto.status().name());
    }
    if (topicoPutDto.mensaje() != null) {
      this.mensaje = topicoPutDto.mensaje();
    }
  }
}