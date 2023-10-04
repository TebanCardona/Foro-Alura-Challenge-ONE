package com.alura.foro.dominio.respuesta;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.alura.foro.dominio.topico.Topico;
import com.alura.foro.dominio.usuario.Usuario;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String mensaje;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "topico_id")
  private Topico topico;

  private LocalDateTime fechaCreacion = LocalDateTime.now();
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "autor_id")
  private Usuario autor;
  private Boolean solucion = false;

  public Respuesta(RespuestaPostDTO respuestaPostDTO, Topico topico, Usuario usuario) {
    this.mensaje = respuestaPostDTO.mensaje();
    this.topico = topico;
    this.autor = usuario;
  }

  public void actualizarDatos(RespuestaEditDTO respuestaEditDto) {
    if (respuestaEditDto.solucion() != this.solucion) {
      this.solucion = respuestaEditDto.solucion();
    }
    if (respuestaEditDto.mensaje() != null) {
      this.mensaje = respuestaEditDto.mensaje();
    }
  }
}